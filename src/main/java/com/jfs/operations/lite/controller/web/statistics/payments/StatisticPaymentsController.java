package com.jfs.operations.lite.controller.web.statistics.payments;

import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.dto.accounting.ChartEarnedUnearnedStatisticsDto;
import com.jfs.operations.lite.controller.web.utils.Utils;
import com.jfs.operations.lite.dto.accounting.DonutChartEarnedUnearnedStatisticsDto;
import lombok.RequiredArgsConstructor;
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
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web/app/statistics/payments")
@RequiredArgsConstructor
public class StatisticPaymentsController {
    private final RestClient restClient;
    private final HttpServletRequest httpServletRequest;
    private final Utils utils;

    @GetMapping
    public String statisticsPayments(Model model, @RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        initChartEarnedUnearnedStatistics(model, "weekly", "Weeks", from, to);
        initChartEarnedUnearnedStatistics(model,"monthly","Months", from, to);
        initDonutEarnedUnearnedStatistics(model, from, to);
        initDonutEarnedUnearnedStatisticsPaymentType(model, from, to);
        return "statistics-payments-form";
    }

    @PostMapping("/filter")
    public String statisticsPayments(@RequestParam(required = false) String from_date, @RequestParam(required = false) String to_date) {
        return "redirect:/web/app/statistics/payments?from=" + from_date + "&to=" + to_date;
    }

    private void initChartEarnedUnearnedStatistics(Model model, String chartType, String periodName, String from, String to) {
        LocalDate[] dates = utils.initializeDates(from, to);
        LocalDate fromDate = dates[0], toDate = dates[1];
        Integer noOfPeriods = calculateFullPeriodsBetween(fromDate, toDate, periodName);

        ChartEarnedUnearnedStatisticsDto chartEarnedUnearnedStatisticsDto = (ChartEarnedUnearnedStatisticsDto) restClient.getSync(
                MessageFormat.format("/api/statistics/payments/chart-earned-unearned-{0}"
                                + "?from=" + LocalDateTime.of(fromDate, LocalTime.MIN).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                                + "&to=" + LocalDateTime.of(toDate, LocalTime.MAX).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                                + "&noOf{1}={2}", chartType, periodName,
                        noOfPeriods.toString()),
                ChartEarnedUnearnedStatisticsDto.class, httpServletRequest);
        String chartPeriodLabels = "[" + String.join(", ", chartEarnedUnearnedStatisticsDto.getChartPeriodLabels()) + "]",
                chartEarnedValues = "[" + Arrays.stream(chartEarnedUnearnedStatisticsDto.getChartEarnedValues()).map(e -> ((double) e) / 100.0).map(String::valueOf).collect(Collectors.joining(", ")) + "]", //TODO: change scale for revenue
                chartUnearnedValues = "[" + Arrays.stream(chartEarnedUnearnedStatisticsDto.getChartUnearnedValues()).map(e -> ((double) e) / 100.0).map(String::valueOf).collect(Collectors.joining(", ")) + "]",
                chartSumValues = "[" + Arrays.stream(chartEarnedUnearnedStatisticsDto.getChartSumValues()).map(e -> ((double) e) / 100.0).map(String::valueOf).collect(Collectors.joining(", ")) + "]";

        model.addAttribute("chart" + chartType + "DateFrom", chartEarnedUnearnedStatisticsDto.getDateFrom());
        model.addAttribute("chart" + chartType + "DateTo", chartEarnedUnearnedStatisticsDto.getDateTo());
        model.addAttribute("chart" + chartType + "PeriodLabels", chartPeriodLabels);
        model.addAttribute("chart" + chartType + "EarnedValues", chartEarnedValues);
        model.addAttribute("chart" + chartType + "UnearnedValues", chartUnearnedValues);
        model.addAttribute("chart" + chartType + "SumValues", chartSumValues);
        model.addAttribute("chart" + chartType + "TitleLabel", StringUtils.capitalize(chartType));
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
    }

    private void initDonutEarnedUnearnedStatistics(Model model, String from, String to) {
        LocalDate[] dates = utils.initializeDates(from, to);
        LocalDate fromDate = dates[0], toDate = dates[1];

        DonutChartEarnedUnearnedStatisticsDto donutChartEarnedUnearnedStatisticsDto = (DonutChartEarnedUnearnedStatisticsDto) restClient.getSync(
                "/api/statistics/payments/donut-chart-earned-unearned"
                                + "?from=" + LocalDateTime.of(fromDate, LocalTime.MIN).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                                + "&to=" + LocalDateTime.of(toDate, LocalTime.MAX).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                DonutChartEarnedUnearnedStatisticsDto.class, httpServletRequest);
        String donutChartLabels = "[" + String.join(", ", donutChartEarnedUnearnedStatisticsDto.getDonutChartLabels()) + "]",
                donutChartEarnedColors = "[\"" + String.join("\", \"", donutChartEarnedUnearnedStatisticsDto.getDonutChartEarnedColors()) + "\"]",
                donutChartUnearnedColors = "[\"" + String.join("\", \"", donutChartEarnedUnearnedStatisticsDto.getDonutChartUnearnedColors()) + "\"]",
                donutChartSumColors = "[\"" + String.join("\", \"", donutChartEarnedUnearnedStatisticsDto.getDonutChartSumColors()) + "\"]",
                donutChartEarnedNames = "[\"" + Arrays.stream(donutChartEarnedUnearnedStatisticsDto.getDonutChartEarnedNames())
                        .map(name -> name
                                .replace("\\", "\\\\")
                                .replace("\"", "\\\"")
                                .replace("'", "\\'")
                                .replace("\n", "\\n")
                                .replace("\r", "\\r")
                                .replace("\t", "\\t")
                        )
                        .collect(Collectors.joining("\", \"")) + "\"]",
                donutChartUnearnedNames = "[\"" + Arrays.stream(donutChartEarnedUnearnedStatisticsDto.getDonutChartUnearnedNames())
                        .map(name -> name
                                .replace("\\", "\\\\")
                                .replace("\"", "\\\"")
                                .replace("'", "\\'")
                                .replace("\n", "\\n")
                                .replace("\r", "\\r")
                                .replace("\t", "\\t")
                        )
                        .collect(Collectors.joining("\", \"")) + "\"]",
                donutChartSumNames = "[\"" + Arrays.stream(donutChartEarnedUnearnedStatisticsDto.getDonutChartSumNames())
                        .map(name -> name
                                .replace("\\", "\\\\")
                                .replace("\"", "\\\"")
                                .replace("'", "\\'")
                                .replace("\n", "\\n")
                                .replace("\r", "\\r")
                                .replace("\t", "\\t")
                        )
                        .collect(Collectors.joining("\", \"")) + "\"]",
                donutChartEarnedValues = "[" + Arrays.stream(donutChartEarnedUnearnedStatisticsDto.getDonutChartEarnedValues()).map(e -> ((double) e) / 100.0).map(String::valueOf).collect(Collectors.joining(", ")) + "]", //TODO: change scale for revenue
                donutChartUnearnedValues = "[" + Arrays.stream(donutChartEarnedUnearnedStatisticsDto.getDonutChartUnearnedValues()).map(e -> ((double) e) / 100.0).map(String::valueOf).collect(Collectors.joining(", ")) + "]",
                donutChartSumValues = "[" + Arrays.stream(donutChartEarnedUnearnedStatisticsDto.getDonutChartSumValues()).map(e -> ((double) e) / 100.0).map(String::valueOf).collect(Collectors.joining(", ")) + "]";

        model.addAttribute("donutChartDateFrom", donutChartEarnedUnearnedStatisticsDto.getDateFrom());
        model.addAttribute("donutChartDateTo", donutChartEarnedUnearnedStatisticsDto.getDateTo());
        model.addAttribute("donutChartLabels", donutChartLabels);
        model.addAttribute("donutChartEarnedValues", donutChartEarnedValues);
        model.addAttribute("donutChartUnearnedValues", donutChartUnearnedValues);
        model.addAttribute("donutChartSumValues", donutChartSumValues);
        model.addAttribute("donutChartEarnedColors", donutChartEarnedColors);
        model.addAttribute("donutChartUnearnedColors", donutChartUnearnedColors);
        model.addAttribute("donutChartSumColors", donutChartSumColors);
        model.addAttribute("donutChartEarnedNames", donutChartEarnedNames);
        model.addAttribute("donutChartUnearnedNames", donutChartUnearnedNames);
        model.addAttribute("donutChartSumNames", donutChartSumNames);
    }

    private void initDonutEarnedUnearnedStatisticsPaymentType(Model model, String from, String to) {
        LocalDate[] dates = utils.initializeDates(from, to);
        LocalDate fromDate = dates[0], toDate = dates[1];

        String url = String.format("/api/statistics/payments/donut-chart-earned-unearned-payment-type?from=%s&to=%s",
                LocalDateTime.of(fromDate, LocalTime.MIN).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.of(toDate, LocalTime.MAX).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        DonutChartEarnedUnearnedStatisticsDto statisticsDto = (DonutChartEarnedUnearnedStatisticsDto) restClient.getSync(
                url, DonutChartEarnedUnearnedStatisticsDto.class, httpServletRequest);

        Function<String, String> escapeSpecialCharacters = name -> name
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("'", "\\'")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");

        String paymentTypeChartLabels = formatChartValues(statisticsDto.getDonutChartLabels(), escapeSpecialCharacters);
        String paymentTypeChartColors = formatChartValues(statisticsDto.getDonutChartEarnedColors(), Function.identity());
        String paymentTypeChartNames = formatChartValues(statisticsDto.getDonutChartEarnedNames(), escapeSpecialCharacters);
        String paymentTypeChartEarnedValues = formatNumericValues(statisticsDto.getDonutChartEarnedValues());
        String paymentTypeChartUnearnedValues = formatNumericValues(statisticsDto.getDonutChartUnearnedValues());
        String paymentTypeChartSumValues = formatNumericValues(statisticsDto.getDonutChartSumValues());

        model.addAttribute("paymentTypeChartDateFrom", statisticsDto.getDateFrom());
        model.addAttribute("paymentTypeChartDateTo", statisticsDto.getDateTo());
        model.addAttribute("paymentTypeChartLabels", paymentTypeChartLabels);
        model.addAttribute("paymentTypeChartEarnedValues", paymentTypeChartEarnedValues);
        model.addAttribute("paymentTypeChartUnearnedValues", paymentTypeChartUnearnedValues);
        model.addAttribute("paymentTypeChartSumValues", paymentTypeChartSumValues);
        model.addAttribute("paymentTypeChartColors", paymentTypeChartColors);
        model.addAttribute("paymentTypeChartNames", paymentTypeChartNames);
    }

    private String formatChartValues(String[] values, Function<String, String> formatter) {
        return "[\"" + Arrays.stream(values)
                .map(formatter)
                .collect(Collectors.joining("\", \"")) + "\"]";
    }

    private String formatNumericValues(Integer[] values) {
        return "[" + Arrays.stream(values)
                .map(e -> ((double) e) / 100.0)
                .map(String::valueOf)
                .collect(Collectors.joining(", ")) + "]";
    }

    private int calculateFullPeriodsBetween(LocalDate fromDate, LocalDate toDate, String periodType) {
        return switch (periodType.toLowerCase()) {
            case "months" -> {
                LocalDate nextMonth = fromDate.getDayOfMonth() == 1
                        ? fromDate
                        : fromDate.withDayOfMonth(1).plusMonths(1);
                LocalDate toDateNextMonth = toDate.withDayOfMonth(1);

                yield (toDate.isEqual(toDateNextMonth) && fromDate.isEqual(toDateNextMonth.minusMonths(1)))
                        ? 1
                        : (int) ChronoUnit.MONTHS.between(nextMonth, toDateNextMonth);
            }

            case "weeks" -> {
                LocalDate startOfWeek = fromDate.getDayOfWeek().getValue() == 1
                        ? fromDate
                        : fromDate.plusDays(8 - fromDate.getDayOfWeek().getValue());
                LocalDate startOfNextWeek = toDate.minusDays(toDate.getDayOfWeek().getValue() - 1);

                yield (!startOfWeek.isBefore(toDate) && !startOfWeek.isEqual(toDate))
                        ? 0
                        : (int) ChronoUnit.WEEKS.between(startOfWeek, startOfNextWeek);
            }

            default -> throw new IllegalArgumentException("Invalid period type. Please specify 'Weeks' or 'Months'.");
        };
    }
}

