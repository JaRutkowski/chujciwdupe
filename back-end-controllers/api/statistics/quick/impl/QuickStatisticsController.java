package com.jfs.operations.lite.controller.api.statistics.quick.impl;

import com.jfs.operations.lite.controller.api.statistics.quick.QuickStatisticsControllerApi;
import com.jfs.operations.lite.model.schedule.domain.ScheduleEventStatus;
import com.jfs.operations.lite.service.accounting.AccountingService;
import com.jfs.operations.lite.service.accounting.dto.ChartAccumulativeSumStatisticsDto;
import com.jfs.operations.lite.service.accounting.dto.ChartQuickStatisticsDto;
import com.jfs.operations.lite.service.accounting.dto.QuickStatisticsDto;
import com.jfs.operations.lite.service.client.dto.ClientDynamicDataDto;
import com.jfs.operations.lite.service.collections.CollectionService;
import com.jfs.operations.lite.service.collections.dto.GeneralCollectionDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequiredArgsConstructor
public class QuickStatisticsController implements QuickStatisticsControllerApi {
    private final AccountingService accountingService;
    private final CollectionService collectionService;

    @Override
    public ResponseEntity<ChartQuickStatisticsDto> buildChartQuickWeeklyStatistics(LocalDateTime from, LocalDateTime to, Integer noOfWeeks) {
        return ResponseEntity.ok(accountingService.getChartQuickPeriodStatistics(from, to, noOfWeeks,
                (weeksBack) -> LocalDateTime.of(to.minusWeeks(weeksBack).with(DayOfWeek.MONDAY).toLocalDate(), LocalTime.MIN),
                (weeksBack) -> LocalDateTime.of(to.minusWeeks(weeksBack).with(DayOfWeek.SUNDAY).toLocalDate(), LocalTime.MAX),
                "dd MMM"));
    }

    @Override
    public ResponseEntity<ChartQuickStatisticsDto> buildChartQuickMonthlyStatistics(LocalDateTime from, LocalDateTime to, Integer noOfMonths) {
        return ResponseEntity.ok(accountingService.getChartQuickPeriodStatistics(from, to, noOfMonths,
                (monthsBack) -> LocalDateTime.of(to.minusMonths(monthsBack).withDayOfMonth(1).toLocalDate(), LocalTime.MIN),
                (monthsBack) -> LocalDateTime.of(to.minusMonths(monthsBack).withDayOfMonth(
                        to.minusMonths(monthsBack).getMonth().length(to.minusMonths(monthsBack).toLocalDate().isLeapYear())).toLocalDate(), LocalTime.MAX),
                "MMM/yyyy"));
    }

    @Override
    public ResponseEntity<ChartAccumulativeSumStatisticsDto> buildChartAccumulativeSumWeeklyStatistics(LocalDateTime from, LocalDateTime to, Integer noOfWeeks) {
        return ResponseEntity.ok(accountingService.getChartAccumulativeSumStatisticsDto(from, to, noOfWeeks,
                (weeksBack) -> LocalDateTime.of(to.minusWeeks(weeksBack).with(DayOfWeek.MONDAY).toLocalDate(), LocalTime.MIN),
                (weeksBack) -> LocalDateTime.of(to.minusWeeks(weeksBack).with(DayOfWeek.SUNDAY).toLocalDate(), LocalTime.MAX),
                "dd MMM yy"));
    }

    @Override
    public ResponseEntity<ChartAccumulativeSumStatisticsDto> buildChartAccumulativeSumMonthlyStatistics(LocalDateTime from, LocalDateTime to, Integer noOfMonths) {
        return ResponseEntity.ok(accountingService.getChartAccumulativeSumStatisticsDto(from, to, noOfMonths,
                (monthsBack) -> LocalDateTime.of(to.minusMonths(monthsBack).withDayOfMonth(1).toLocalDate(), LocalTime.MIN),
                (monthsBack) -> LocalDateTime.of(to.minusMonths(monthsBack).withDayOfMonth(
                        to.minusMonths(monthsBack).getMonth().length(to.minusMonths(monthsBack).toLocalDate().isLeapYear())).toLocalDate(), LocalTime.MAX),
                "MMM/yyyy"));
    }

    @Override
    public ResponseEntity<QuickStatisticsDto> buildQuickStatistics() {
        return ResponseEntity.ok(accountingService.getQuickStatistics());
    }

    @Override
    public ResponseEntity<BigDecimal> yearlyNetIncome(@PathVariable Integer year) {
        return ResponseEntity.ok(accountingService.yearlyNetIncome(year));
    }

    @Override
    public ResponseEntity<BigDecimal> netIncomeBetween(LocalDateTime from, LocalDateTime to) {
        return ResponseEntity.ok(accountingService.netIncomeBetween(from, to, new String[]{ScheduleEventStatus.DONE.getName()}));
    }

    @Override
    public ResponseEntity<ClientDynamicDataDto> yearlyClientDynamic(LocalDateTime from, LocalDateTime to) {
        return ResponseEntity.ok(accountingService.yearlyClientDynamicDataBetween(from, to));
    }

    @Override
    public ResponseEntity<GeneralCollectionDataDto> buildGeneralCollectionData(LocalDateTime from, LocalDateTime to) {
        return ResponseEntity.ok(collectionService.getGeneralCollectionData(from, to));
    }
}
