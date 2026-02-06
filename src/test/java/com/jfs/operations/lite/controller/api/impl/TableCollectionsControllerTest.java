package com.jfs.operations.lite.controller.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfs.operations.lite.controller.web.accounting.collections.TableCollectionsController;
import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.controller.web.utils.Utils;
import com.jfs.operations.lite.dto.schedule.TableEntryCollectionDataDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.mockito.*;

@ExtendWith(MockitoExtension.class)
class TableCollectionsControllerTest {

    @Mock
    RestClient restClient;
    @Mock
    Utils utils;
    @Mock
    HttpServletRequest httpServletRequest;

    TableCollectionsController controller;

    @BeforeEach
    void setUp() {
        // we pass a real ObjectMapper just because the controller has one,
        // but it isn't used by initNotCollectedEntriesForm()
        controller = new TableCollectionsController(
                restClient,
                httpServletRequest,
                utils,
                new ObjectMapper()
        );
    }

    @Test
    void allOngoingPayments_groupsByMonth_andSortAsc() {
        // — arrange —
        String fromStr = "2025-01-01", toStr = "2025-01-31";
        String grouping = "month", sort = "asc";

        LocalDate fromDate = LocalDate.of(2025, 1, 1);
        LocalDate toDate = LocalDate.of(2025, 1, 31);
        // stub utils.initializeDates(...)
        when(utils.initializeDates(fromStr, toStr))
                .thenReturn(new LocalDate[]{fromDate, toDate});

        // prepare two dtos out‐of‐order
        Calendar c1 = Calendar.getInstance();
        c1.set(2025, Calendar.JANUARY, 1, 0, 0, 0);
        c1.set(Calendar.MILLISECOND, 0);

        Calendar c2 = Calendar.getInstance();
        c2.set(2025, Calendar.JANUARY, 15, 0, 0, 0);
        c2.set(Calendar.MILLISECOND, 0);

        TableEntryCollectionDataDto dtoEarly = new TableEntryCollectionDataDto();
        dtoEarly.setDateFrom(c1);
        dtoEarly.setDateTo(c1);

        TableEntryCollectionDataDto dtoLate = new TableEntryCollectionDataDto();
        dtoLate.setDateFrom(c2);
        dtoLate.setDateTo(c2);

        List<TableEntryCollectionDataDto> fetched = Arrays.asList(dtoLate, dtoEarly);

        // compute the exact URL we expect buildNotCollectedEntriesUrl(...) to produce:
        String expectedUrl = String.format(
                "/api/collections/not-collected-entries-by-months?from=%s&to=%s&grouping=%s",
                LocalDateTime.of(fromDate, LocalTime.MIN).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.of(toDate, LocalTime.MAX).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                grouping
        );

        // stub restClient.getSyncWithType(...)
        when(restClient.getSyncWithType(
                eq(expectedUrl),
                any(ParameterizedTypeReference.class),
                eq(httpServletRequest))
        ).thenReturn(fetched);

        // — act —
        Model model = new ExtendedModelMap();
        String view = controller.allOngoingPayments(
                model, fromStr, toStr, grouping, sort
        );

        // — assert view name —
        assertEquals("table-not-collected-entries-form", view);

        // — verify restClient was called with the correct URL —
        verify(restClient).getSyncWithType(
                eq(expectedUrl),
                any(ParameterizedTypeReference.class),
                eq(httpServletRequest)
        );

        // — verify parseDates & populateModel results in the model —
        assertEquals(fromDate, model.getAttribute("fromDate"));
        assertEquals(toDate, model.getAttribute("toDate"));
        assertEquals(grouping, model.getAttribute("grouping"));
        assertEquals(sort, model.getAttribute("sort"));

        // — verify the grouping key & sort order —
        @SuppressWarnings("unchecked")
        Map<String, List<TableEntryCollectionDataDto>> grouped =
                (Map<String, List<TableEntryCollectionDataDto>>) model.getAttribute("groupedNotCollectedEntries");

        // for month=2025-01, there should be exactly one group
        assertTrue(grouped.containsKey("2025-01"));
        List<TableEntryCollectionDataDto> bucket = grouped.get("2025-01");

        // sort=asc → earliest dto should come first
        assertEquals(dtoEarly, bucket.get(0));
        assertEquals(dtoLate, bucket.get(1));
    }

    @Test
    void allOngoingPayments_groupsByYear_andSortDesc() {
        // — arrange —
        String fromStr = "2024-01-01", toStr = "2025-12-31";
        String grouping = "year", sort = "desc";

        LocalDate fromDate = LocalDate.of(2024, 1, 1);
        LocalDate toDate = LocalDate.of(2025, 12, 31);
        when(utils.initializeDates(fromStr, toStr))
                .thenReturn(new LocalDate[]{fromDate, toDate});

        // two dtos in different years
        Calendar c2024 = Calendar.getInstance();
        c2024.set(2024, Calendar.JULY, 1, 0, 0, 0);
        c2024.set(Calendar.MILLISECOND, 0);

        Calendar c2025 = Calendar.getInstance();
        c2025.set(2025, Calendar.FEBRUARY, 1, 0, 0, 0);
        c2025.set(Calendar.MILLISECOND, 0);

        TableEntryCollectionDataDto dto2024 = new TableEntryCollectionDataDto();
        dto2024.setDateFrom(c2024);
        dto2024.setDateTo(c2024);

        TableEntryCollectionDataDto dto2025 = new TableEntryCollectionDataDto();
        dto2025.setDateFrom(c2025);
        dto2025.setDateTo(c2025);

        List<TableEntryCollectionDataDto> fetched = Arrays.asList(dto2024, dto2025);

        String expectedUrl = String.format(
                "/api/collections/not-collected-entries-by-months?from=%s&to=%s&grouping=%s",
                LocalDateTime.of(fromDate, LocalTime.MIN).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.of(toDate, LocalTime.MAX).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                grouping
        );
        when(restClient.getSyncWithType(eq(expectedUrl), any(), eq(httpServletRequest)))
                .thenReturn(fetched);

        // — act —
        Model model = new ExtendedModelMap();
        controller.allOngoingPayments(model, fromStr, toStr, grouping, sort);

        // — assert groupingKey & sort desc —
        @SuppressWarnings("unchecked")
        Map<String, List<TableEntryCollectionDataDto>> grouped =
                (Map<String, List<TableEntryCollectionDataDto>>) model.getAttribute("groupedNotCollectedEntries");

        // keys should be sorted desc: “2025” first, then “2024”
        List<String> keys = new ArrayList<>(grouped.keySet());
        assertEquals(Arrays.asList("2025", "2024"), keys);

        // within each group there’s just one element
        assertEquals(dto2025, grouped.get("2025").get(0));
        assertEquals(dto2024, grouped.get("2024").get(0));
    }
}
