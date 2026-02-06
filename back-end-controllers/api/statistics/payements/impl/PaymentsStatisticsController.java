package com.jfs.operations.lite.controller.api.statistics.payements.impl;

import com.jfs.operations.lite.controller.api.statistics.payements.PaymentsStatisticsControllerApi;
import com.jfs.operations.lite.service.accounting.dto.DonutChartEarnedUnearnedStatisticsDto;
import com.jfs.operations.lite.service.accounting.dto.ChartEarnedUnearnedStatisticsDto;
import com.jfs.operations.lite.service.accounting.AccountingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequiredArgsConstructor
public class PaymentsStatisticsController implements PaymentsStatisticsControllerApi {
    private final AccountingService accountingService;
    @Override
    public ResponseEntity<ChartEarnedUnearnedStatisticsDto> buildChartEarnedUnearnedStatisticsWeekly(LocalDateTime from, LocalDateTime to, Integer noOfWeeks){
        return ResponseEntity.ok(accountingService.getChartEarnedUnearnedStatistics(from, to, noOfWeeks,
                (weeksBack) -> LocalDateTime.of(to.minusWeeks(weeksBack).with(DayOfWeek.MONDAY).toLocalDate(), LocalTime.MIN),
                (weeksBack) -> LocalDateTime.of(to.minusWeeks(weeksBack).with(DayOfWeek.SUNDAY).toLocalDate(), LocalTime.MAX),
                "dd MMM"));
    }

    public ResponseEntity<ChartEarnedUnearnedStatisticsDto> buildChartEarnedUnearnedStatisticsMonthly(LocalDateTime from, LocalDateTime to, Integer noOfMonths){
        return ResponseEntity.ok(accountingService.getChartEarnedUnearnedStatistics(from, to, noOfMonths,
                (monthsBack) -> LocalDateTime.of(to.minusMonths(monthsBack).withDayOfMonth(1).toLocalDate(), LocalTime.MIN),
                (monthsBack) -> LocalDateTime.of(to.minusMonths(monthsBack).withDayOfMonth(
                        to.minusMonths(monthsBack).getMonth().length(to.minusMonths(monthsBack).toLocalDate().isLeapYear())).toLocalDate(), LocalTime.MAX),
                "MMM/yyyy"));
    }

    public ResponseEntity<DonutChartEarnedUnearnedStatisticsDto> buildChartDonutEarnedUnearnedStatisticsMonthly(LocalDateTime from, LocalDateTime to){
        return ResponseEntity.ok(accountingService.getChartDonutEarnedUnearnedStatistics(from, to));
    }

    public ResponseEntity<DonutChartEarnedUnearnedStatisticsDto> buildChartDonutEarnedUnearnedStatisticsPaymentTypeMonthly(LocalDateTime from, LocalDateTime to){
        return ResponseEntity.ok(accountingService.getChartDonutEarnedUnearnedStatisticsByPaymentType(from, to));
    }
}
