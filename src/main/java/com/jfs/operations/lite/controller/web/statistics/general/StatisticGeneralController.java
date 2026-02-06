package com.jfs.operations.lite.controller.web.statistics.general;

import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.controller.web.utils.Utils;
import com.jfs.operations.lite.dto.accounting.ChartAccumulativeSumStatisticsDto;
import com.jfs.operations.lite.dto.accounting.ChartQuickStatisticsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web/app/statistics/general")
@RequiredArgsConstructor
@Log
public class StatisticGeneralController {
    private final RestClient restClient;
    private final HttpServletRequest httpServletRequest;
    private final Utils utils;

    @GetMapping
    public String statisticsGeneral(Model model, @RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        /* chart-general-statistics-weekly */
        initChartQuickStatistics(model, "weekly", "Weeks", from, to);
        /* chart-general-statistics-monthly */
        initChartQuickStatistics(model, "monthly", "Months", from, to);
        /* chart-accumulative-sum-weekly */
        /* chart-efficiency-weekly */
        initChartAccumulativeSumStatistics(model, "weekly", "Weeks", from, to); //TODO: consider weekly&monthly
        /* chart-accumulative-sum-monthly */
        /* chart-efficiency-monthly */
        initChartAccumulativeSumStatistics(model, "monthly", "Months", from, to); //TODO: consider weekly&monthly
        return "statistics-general-form";
    }

    @PostMapping("/filter")
    public String statisticsClient(@RequestParam(required = false) String from_date, @RequestParam(required = false) String to_date) {
        return "redirect:/web/app/statistics/general?from=" + from_date + "&to=" + to_date;
    }

    private void initChartQuickStatistics(Model model, String chartType, String periodName, String from, String to) {
        LocalDate[] dates = utils.initializeDates(from, to);
        LocalDate fromDate = dates[0], toDate = dates[1];
        Integer noOfPeriods = calculateFullPeriodsBetween(fromDate, toDate, periodName);

        ChartQuickStatisticsDto chartQuickStatisticsDto = (ChartQuickStatisticsDto) restClient.getSync(
                MessageFormat.format("/api/statistics/quick/chart-{0}"
                                + "?from=" + LocalDateTime.of(fromDate, LocalTime.MIN).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                                + "&to=" + LocalDateTime.of(toDate, LocalTime.MAX).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                                + "&noOf{1}={2}", chartType, periodName,
                        noOfPeriods.toString()),
                ChartQuickStatisticsDto.class, httpServletRequest);
        String chartPeriodLabels = "[" + String.join(", ", chartQuickStatisticsDto.getChartPeriodLabels()) + "]",
                chartRevenueValues = "[" + Arrays.stream(chartQuickStatisticsDto.getChartRevenueValues()).map(e -> ((double) e) / 100.0).map(String::valueOf).collect(Collectors.joining(", ")) + "]", //TODO: change scale for revenue
                chartHourValues = "[" + Arrays.stream(chartQuickStatisticsDto.getChartHourValues()).map(String::valueOf).collect(Collectors.joining(", ")) + "]";

        model.addAttribute("chart" + chartType + "DateFrom", chartQuickStatisticsDto.getDateFrom());
        model.addAttribute("chart" + chartType + "DateTo", chartQuickStatisticsDto.getDateTo());
        model.addAttribute("chart" + chartType + "PeriodLabels", chartPeriodLabels);
        model.addAttribute("chart" + chartType + "RevenueValues", chartRevenueValues);
        model.addAttribute("chart" + chartType + "HourValues", chartHourValues);
        model.addAttribute("chart" + chartType + "TitleLabel", StringUtils.capitalize(chartType));
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
    }

    private void initChartAccumulativeSumStatistics(Model model, String chartType, String periodName, String from, String to) {
        LocalDate[] dates = utils.initializeDates(from, to);
        LocalDate fromDate = dates[0], toDate = dates[1];
        Integer noOfPeriods = calculateFullPeriodsBetween(fromDate, toDate, periodName);

        ChartAccumulativeSumStatisticsDto chartAccumulativeSumStatisticsDto = (ChartAccumulativeSumStatisticsDto) restClient.getSync(
                MessageFormat.format("/api/statistics/quick/chart-accumulative-sum-{0}"
                                + "?from=" + LocalDateTime.of(fromDate, LocalTime.MIN).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                                + "&to=" + LocalDateTime.of(toDate, LocalTime.MAX).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                                + "&noOf{1}={2}", chartType, periodName,
                        noOfPeriods.toString()),
                ChartAccumulativeSumStatisticsDto.class, httpServletRequest);
        String chartPeriodLabels = "[" + String.join(", ", chartAccumulativeSumStatisticsDto.getChartPeriodLabels()) + "]",
                chartAccumulativeSumValues = "[" + Arrays.stream(chartAccumulativeSumStatisticsDto.getChartSumValues()).map(e -> ((double) e) / 100.0).map(String::valueOf).collect(Collectors.joining(", ")) + "]",
                chartEfficiencyValues = "[" + Arrays.stream(chartAccumulativeSumStatisticsDto.getChartEfficiencyValues()).map(e -> ((double) e) / 100.0).map(String::valueOf).collect(Collectors.joining(", ")) + "]";

        model.addAttribute("chartAccumulativeSum" + chartType + "DateFrom", chartAccumulativeSumStatisticsDto.getDateFrom());
        model.addAttribute("chartAccumulativeSum" + chartType + "DateTo", chartAccumulativeSumStatisticsDto.getDateTo());
        model.addAttribute("chartAccumulativeSum" + chartType + "PeriodLabels", chartPeriodLabels);
        model.addAttribute("chartAccumulativeSum" + chartType + "RevenueValues", chartAccumulativeSumValues);
        model.addAttribute("chartAccumulativeSum" + chartType + "TitleLabel", StringUtils.capitalize(chartType));

        // date from and date to are the same as for accumulative sum chart
        model.addAttribute("chartEfficiency" + chartType + "RevenueValues", chartEfficiencyValues);
        model.addAttribute("chartEfficiency" + chartType + "TitleLabel", StringUtils.capitalize(chartType));
    }

    private int calculateFullPeriodsBetween(LocalDate fromDate, LocalDate toDate, String periodType) {
        switch (periodType.toLowerCase()) {
            case "months":
                LocalDate nextMonth = fromDate.getDayOfMonth() == 1
                        ? fromDate
                        : fromDate.withDayOfMonth(1).plusMonths(1);
                LocalDate toDateNextMonth = toDate.withDayOfMonth(1);

                return (toDate.isEqual(toDateNextMonth) && fromDate.isEqual(toDateNextMonth.minusMonths(1)))
                        ? 1
                        : (int) ChronoUnit.MONTHS.between(nextMonth, toDateNextMonth);

            case "weeks":
                LocalDate startOfWeek = fromDate.getDayOfWeek().getValue() == 1
                        ? fromDate
                        : fromDate.plusDays(8 - fromDate.getDayOfWeek().getValue());
                LocalDate startOfNextWeek = toDate.minusDays(toDate.getDayOfWeek().getValue() - 1);

                return (!startOfWeek.isBefore(toDate) && !startOfWeek.isEqual(toDate))
                        ? 0
                        : (int) ChronoUnit.WEEKS.between(startOfWeek, startOfNextWeek);

            default:
                throw new IllegalArgumentException("Invalid period type. Please specify 'Weeks' or 'Months'.");
        }
    }

}
