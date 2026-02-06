package com.jfs.operations.lite.controller.api.accounting.collections.impl;

import com.jfs.operations.lite.controller.api.accounting.collections.CollectionsControllerApi;
import com.jfs.operations.lite.service.collections.CollectionService;
import com.jfs.operations.lite.service.collections.dto.TableEntryCollectionDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CollectionsController implements CollectionsControllerApi {
    private final CollectionService collectionService;

    @Override
    public ResponseEntity<List<TableEntryCollectionDataDto>> findAllNotCollectedEntries(LocalDateTime from, LocalDateTime to, String grouping) {
        return ResponseEntity.ok(collectionService.findAllNotCollectedEntriesGroupedByMonths(from, to));
    }

    @Override
    public ResponseEntity<Long> getCountOfNotCollectedEntries() {
        return ResponseEntity.ok(collectionService.getCountOfNotCollectedEntries());
    }

    @PostMapping("/web/app/collections/filter")
    public String getFilteredEntries(
            @RequestParam("from_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam("grouping") String grouping,
            Model model
    ) {
        List<TableEntryCollectionDataDto> entries = collectionService.findAllNotCollectedEntries(
                from.atStartOfDay(), to.atTime(23, 59, 59), grouping
        );
        Map<String, List<TableEntryCollectionDataDto>> groupedMap = entries.stream()
                .collect(Collectors.groupingBy(dto -> {
                    LocalDate localDate = dto.getDateFrom().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();

                    if ("year".equalsIgnoreCase(grouping)) {
                        return String.valueOf(localDate.getYear());
                    } else if ("month".equalsIgnoreCase(grouping)) {
                        return localDate.getYear() + "-" + String.format("%02d", localDate.getMonthValue());
                    }
                    return "No Grouping";
                }));
        model.addAttribute("groupedNotCollectedEntries", groupedMap);
        return "notCollectedEntries"; // <- nazwa widoku JSP
    }
}
