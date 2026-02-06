package com.jfs.operations.lite.controller.web.statistics.client;

import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.controller.web.utils.Utils;
import com.jfs.operations.lite.dto.client.ChartIncomeTimeLengthStatisticsDto;
import com.jfs.operations.lite.dto.client.ChartYearlyNetRevenuePerClientStatisticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/web/app/statistics/client")
@RequiredArgsConstructor
public class StatisticClientController {
    private final RestClient restClient;
    private final HttpServletRequest httpServletRequest;
    private final Utils utils;

    @GetMapping
    public String statisticsClient(Model model, @RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        initChartMonthlyNetRevenuePerClientStatistics(model, from, to);
        initChartIncomeTimeLengthPerClientStatistics(model, from, to);
        return "statistics-client-form";
    }

    @PostMapping("/filter")
    public String statisticsClient(@RequestParam(required = false) String from_date, @RequestParam(required = false) String to_date) {
        return "redirect:/web/app/statistics/client?from=" + from_date + "&to=" + to_date;
    }

    private void initChartMonthlyNetRevenuePerClientStatistics(Model model, String from, String to) {
        LocalDate[] dates = utils.initializeDates(from, to);
        LocalDate fromDate = dates[0], toDate = dates[1];

        ChartYearlyNetRevenuePerClientStatisticsDto chartMonthlyNetRevenuePerClientStatisticsDto =
                (ChartYearlyNetRevenuePerClientStatisticsDto) restClient.getSync("/api/statistics/client/chart-yearly-net-revenue-per-client"
                        + "?from=" + LocalDateTime.of(fromDate, LocalTime.MIN).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        + "&to=" + LocalDateTime.of(toDate, LocalTime.MAX).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                        ChartYearlyNetRevenuePerClientStatisticsDto.class, httpServletRequest);

        StringBuilder chartRevenueValues = new StringBuilder("[");
        int index = 1;
        for (BigDecimal revenue : chartMonthlyNetRevenuePerClientStatisticsDto.getChartYearlyNetRevenuePerClientValues()) {
            chartRevenueValues.append(revenue.doubleValue())
                    .append(index != chartMonthlyNetRevenuePerClientStatisticsDto.getChartYearlyNetRevenuePerClientValues().length ? "," : "");
            index++;
        }
        chartRevenueValues.append("]");

        StringBuilder chartClientLabels = new StringBuilder("[");
        index = 1;
        for (String client : chartMonthlyNetRevenuePerClientStatisticsDto.getChartClientLabels()) {
            chartClientLabels.append("'").append(client.replace("'", "")).append("'")
                    .append(index != chartMonthlyNetRevenuePerClientStatisticsDto.getChartClientLabels().length ? "," : "");
            index++;
        }
        chartClientLabels.append("]");

        model.addAttribute("chartYearlyNetRevenuePerClientClientLabels", chartClientLabels);
        model.addAttribute("chartYearlyNetRevenuePerClientRevenueValues", chartRevenueValues);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
    }

    private void initChartIncomeTimeLengthPerClientStatistics(Model model, String from, String to) {
        LocalDate[] dates = utils.initializeDates(from, to);
        LocalDate fromDate = dates[0], toDate = dates[1];

        ChartIncomeTimeLengthStatisticsDto chartIncomeTimeLengthStatisticsDto =
                (ChartIncomeTimeLengthStatisticsDto) restClient.getSync("/api/statistics/client/chart-income-time-length-per-client"
                        + "?from=" + LocalDateTime.of(fromDate, LocalTime.MIN).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        + "&to=" + LocalDateTime.of(toDate, LocalTime.MAX).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                        ChartIncomeTimeLengthStatisticsDto.class, httpServletRequest);

        StringBuilder chartRevenueValues = new StringBuilder("[");
        int index = 1;
        for (BigDecimal revenue : chartIncomeTimeLengthStatisticsDto.getChartYearlyNetRevenuePerClientValues()) {
            chartRevenueValues.append(revenue.doubleValue() / 1000.0)
                    .append(index != chartIncomeTimeLengthStatisticsDto.getChartYearlyNetRevenuePerClientValues().size() ? "," : "");
            index++;
        }
        chartRevenueValues.append("]");

        StringBuilder chartAvgWeeklyTimeAllocationValues = new StringBuilder("[");
        index = 1;
        for (BigDecimal avgWeeklyTime : chartIncomeTimeLengthStatisticsDto.getChartAvgWeeklyTimeAllocationValues()) {
            chartAvgWeeklyTimeAllocationValues.append(avgWeeklyTime.doubleValue())
                    .append(index != chartIncomeTimeLengthStatisticsDto.getChartAvgWeeklyTimeAllocationValues().size() ? "," : "");
            index++;
        }
        chartAvgWeeklyTimeAllocationValues.append("]");

        StringBuilder chartTotalCooperationTimeValues = new StringBuilder("[");
        index = 1;
        for (Long totalCooperationTime : chartIncomeTimeLengthStatisticsDto.getChartTotalCooperationTimeValues()) {
            chartTotalCooperationTimeValues.append((Double.valueOf(totalCooperationTime) / 365.0) * 12.0)
                    //chartTotalCooperationTimeValues.append(totalCooperationTime)
                    .append(index != chartIncomeTimeLengthStatisticsDto.getChartTotalCooperationTimeValues().size() ? "," : "");
            index++;
        }
        chartTotalCooperationTimeValues.append("]");

        StringBuilder chartClientLabels = new StringBuilder("[");
        index = 1;
        for (String client : chartIncomeTimeLengthStatisticsDto.getChartClientLabels()) {
            chartClientLabels.append("'").append(client.replace("'", "")).append("'")
                    .append(index != chartIncomeTimeLengthStatisticsDto.getChartClientLabels().length ? "," : "");
            index++;
        }
        chartClientLabels.append("]");

        model.addAttribute("chartYearlyNetRevenuePerClientClientLabels", chartClientLabels);
        model.addAttribute("chartYearlyNetRevenuePerClientRevenueValues", chartRevenueValues);
        model.addAttribute("chartYearlyNetRevenuePerClientAvgWeeklyTimeAllocationValues1", chartAvgWeeklyTimeAllocationValues);
        model.addAttribute("chartYearlyNetRevenuePerClientTotalCooperationTimeValues1", chartTotalCooperationTimeValues);
    }
}
