package com.jfs.operations.lite.controller.web.accounting.collections;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.controller.web.utils.Utils;
import com.jfs.operations.lite.dto.schedule.TableEntryCollectionDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;


@Controller
@RequestMapping("/web/app/collections")
@RequiredArgsConstructor
public class TableCollectionsController {
    private final RestClient restClient;
    private final HttpServletRequest httpServletRequest;
    private final Utils utils;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String allOngoingPayments(
            Model model,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) String grouping,
            @RequestParam(required = false) String sort) {

        initNotCollectedEntriesForm(model, from, to, grouping, sort);
        initAdditionalDetails(model);
        return "table-not-collected-entries-form";
    }

    @PostMapping("/filter")
    public String allOngoingPayments(@RequestParam(required = false) String from_date, @RequestParam(required = false) String to_date) {
        return "redirect:/web/app/collections?from=" + from_date + "&to=" + to_date;
    }

    private void initNotCollectedEntriesForm(Model model, String from, String to, String grouping, String sort) {
        LocalDate[] dates = utils.initializeDates(from, to);
        LocalDate fromDate = dates[0];
        LocalDate toDate = dates[1];
        String url = buildNotCollectedEntriesUrl(fromDate, toDate, grouping);
        List<TableEntryCollectionDataDto> dtos = fetchNotCollectedEntries(url);
        Map<String, List<TableEntryCollectionDataDto>> grouped = groupAndSort(dtos, grouping, sort);
        populateModel(model, grouped, fromDate, toDate, grouping, sort);
    }

    private LocalDate[] parseDates(String from, String to) {
        return utils.initializeDates(from, to);
    }

    private String buildNotCollectedEntriesUrl(LocalDate fromDate, LocalDate toDate, String grouping) {
        String groupBy = (grouping == null || grouping.isBlank()) ? "none" : grouping;
        String fromTs = LocalDateTime.of(fromDate, LocalTime.MIN)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String toTs = LocalDateTime.of(toDate, LocalTime.MAX)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return String.format(
                "/api/collections/not-collected-entries-by-months?from=%s&to=%s&grouping=%s",
                fromTs, toTs, groupBy);
    }

    private List<TableEntryCollectionDataDto> fetchNotCollectedEntries(String url) {
        return restClient.getSyncWithType(url, new ParameterizedTypeReference<>() {
                },
                httpServletRequest
        );
    }

    private Map<String, List<TableEntryCollectionDataDto>> groupAndSort(
            List<TableEntryCollectionDataDto> dtos,
            String grouping,
            String sort
    ) {
        Comparator<String> keyComp = "asc".equals(sort)
                ? Comparator.naturalOrder()
                : Comparator.reverseOrder();
        final Comparator<TableEntryCollectionDataDto> dateComp =
                "asc".equals(sort)
                        ? Comparator.comparing(TableEntryCollectionDataDto::getDateFrom)
                        : Comparator.comparing(TableEntryCollectionDataDto::getDateFrom).reversed();
        return dtos.stream()
                .collect(Collectors.groupingBy(dto -> groupingKey(dto, grouping)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey(keyComp))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .sorted(dateComp)
                                .collect(Collectors.toList()),
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }

    private String groupingKey(TableEntryCollectionDataDto dto, String grouping) {
        LocalDate date = dto.getDateFrom()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        if ("year".equalsIgnoreCase(grouping)) {
            return String.valueOf(date.getYear());
        } else if ("month".equalsIgnoreCase(grouping)) {
            return date.getYear() + "-" + String.format("%02d", date.getMonthValue());
        } else {
            return "No Grouping";
        }
    }

    private void populateModel(
            Model model,
            Map<String, List<TableEntryCollectionDataDto>> grouped,
            LocalDate fromDate,
            LocalDate toDate,
            String grouping,
            String sort
    ) {
        model.addAttribute("groupedNotCollectedEntries", grouped);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("grouping", grouping);
        model.addAttribute("sort", sort);
    }

    private void initAdditionalDetails(Model model) {
        Long count = (Long) restClient.getSync("/api/collections/not-collected-entries-count", Long.class, httpServletRequest);
        model.addAttribute("notCollectedEntriesCount", count);
    }
}