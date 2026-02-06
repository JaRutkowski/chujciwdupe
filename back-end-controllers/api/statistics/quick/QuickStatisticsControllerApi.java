package com.jfs.operations.lite.controller.api.statistics.quick;

import com.jfs.operations.lite.service.accounting.dto.ChartAccumulativeSumStatisticsDto;
import com.jfs.operations.lite.service.accounting.dto.ChartQuickStatisticsDto;
import com.jfs.operations.lite.service.accounting.dto.QuickStatisticsDto;
import com.jfs.operations.lite.service.client.dto.ClientDynamicDataDto;
import com.jfs.operations.lite.service.collections.dto.GeneralCollectionDataDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequestMapping(value = "/api/statistics/quick", produces = MediaType.APPLICATION_JSON_VALUE)
public interface QuickStatisticsControllerApi {
    @GetMapping("/chart-weekly")
    ResponseEntity<ChartQuickStatisticsDto> buildChartQuickWeeklyStatistics(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                                                            @RequestParam Integer noOfWeeks);

    @GetMapping("/chart-monthly")
    ResponseEntity<ChartQuickStatisticsDto> buildChartQuickMonthlyStatistics(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                                                             @RequestParam Integer noOfMonths);

    @GetMapping("/chart-accumulative-sum-weekly")
    ResponseEntity<ChartAccumulativeSumStatisticsDto> buildChartAccumulativeSumWeeklyStatistics(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                                                                                @RequestParam Integer noOfWeeks);

    @GetMapping("/chart-accumulative-sum-monthly")
    ResponseEntity<ChartAccumulativeSumStatisticsDto> buildChartAccumulativeSumMonthlyStatistics(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                                                                                 @RequestParam Integer noOfMonths);

    @GetMapping
    ResponseEntity<QuickStatisticsDto> buildQuickStatistics();

    @GetMapping("/yearly-net-income/{year}")
    ResponseEntity<BigDecimal> yearlyNetIncome(@PathVariable Integer year);

    @GetMapping("/net-income")
    ResponseEntity<BigDecimal> netIncomeBetween(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to);

    @GetMapping("/yearly-client-dynamic")
    ResponseEntity<ClientDynamicDataDto> yearlyClientDynamic(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to);

    @GetMapping("/general-collection-data")
    ResponseEntity<GeneralCollectionDataDto> buildGeneralCollectionData(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to);
}
