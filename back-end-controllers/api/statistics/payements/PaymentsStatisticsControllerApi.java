package com.jfs.operations.lite.controller.api.statistics.payements;

import com.jfs.operations.lite.service.accounting.dto.DonutChartEarnedUnearnedStatisticsDto;
import com.jfs.operations.lite.service.accounting.dto.ChartEarnedUnearnedStatisticsDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@RequestMapping(value = "/api/statistics/payments", produces = MediaType.APPLICATION_JSON_VALUE)
public interface PaymentsStatisticsControllerApi {
    @GetMapping("/chart-earned-unearned-weekly")
    ResponseEntity<ChartEarnedUnearnedStatisticsDto> buildChartEarnedUnearnedStatisticsWeekly(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                                                                        @RequestParam Integer noOfWeeks);

    @GetMapping("/chart-earned-unearned-monthly")
    ResponseEntity<ChartEarnedUnearnedStatisticsDto> buildChartEarnedUnearnedStatisticsMonthly(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                                                                              @RequestParam Integer noOfWeeks);

    @GetMapping("/donut-chart-earned-unearned")
    ResponseEntity<DonutChartEarnedUnearnedStatisticsDto> buildChartDonutEarnedUnearnedStatisticsMonthly(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to);

    @GetMapping("/donut-chart-earned-unearned-payment-type")
    ResponseEntity<DonutChartEarnedUnearnedStatisticsDto> buildChartDonutEarnedUnearnedStatisticsPaymentTypeMonthly(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to);
}
