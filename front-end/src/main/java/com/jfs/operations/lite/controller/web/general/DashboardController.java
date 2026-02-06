package com.jfs.operations.lite.controller.web.general;

import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.dto.accounting.ChartQuickStatisticsDto;
import com.jfs.operations.lite.dto.accounting.QuickStatisticsDto;
import com.jfs.operations.lite.dto.client.ClientDynamicDataDto;
import com.jfs.operations.lite.dto.collections.GeneralCollectionDataDto;
import com.jfs.operations.lite.dto.schedule.TodoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web/app/dashboard")
@RequiredArgsConstructor
@SessionAttributes("notCollectedEntriesCount")
public class DashboardController {
    private final RestClient restClient;
    private final HttpServletRequest httpServletRequest;

    @GetMapping
    public String dashboard(Model model) {
        /* todos */
        LocalDate localDate = LocalDate.now();
        initTodos(model, localDate);
        /* quick-statistics */
        QuickStatisticsDto quickStatisticsDto = initQuickStatistics(model, localDate);
        /* goals-completion */
        initGoalsCompletion(model, quickStatisticsDto);
        /* general collection data */
        initGeneralCollectionData(model, quickStatisticsDto);
        /* chart-quick-weekly-statistics */
        initChartQuickWeeklyStatistics(model);
        /* additional-details */
        initAdditionalDetails(model);
        return "dashboard-form";
    }

    private void initTodos(Model model, LocalDate localDate) {
        String startOfToday = LocalDateTime.of(localDate, LocalTime.MIDNIGHT).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                startOfTomorrow = LocalDateTime.of(localDate.plusDays(1), LocalTime.MIDNIGHT).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        List<TodoDto> todoDtos = (List<TodoDto>) restClient.getSync(
                "/api/schedule/todo?start=" + startOfToday + "&end=" + startOfTomorrow, List.class, httpServletRequest);
        model.addAttribute("todayTodoList", todoDtos);
    }

    private QuickStatisticsDto initQuickStatistics(Model model, LocalDate localDate) {
        QuickStatisticsDto quickStatisticsDto = (QuickStatisticsDto) restClient.getSync("/api/statistics/quick", QuickStatisticsDto.class, httpServletRequest);
        Integer yearlyLongestWeeklyGoalStreak = quickStatisticsDto.getyearlyLongestWeeklyGoalStreak(),
                previousYearlyLongestWeeklyGoalStreak = quickStatisticsDto.getPreviousYearlyLongestWeeklyGoalStreak();
        BigDecimal lastYearSamePeriodIncome = (BigDecimal) restClient.getSync(
                "/api/statistics/quick/net-income?from=" + (localDate.minusYears(1).withDayOfYear(1).atStartOfDay()) + "&to=" + (LocalDateTime.now().minusYears(1)), BigDecimal.class, httpServletRequest),
                relativeGrowthPercentage =
                        (!Objects.isNull(lastYearSamePeriodIncome) && lastYearSamePeriodIncome.compareTo(BigDecimal.ZERO) != 0
                                && !Objects.isNull(quickStatisticsDto.getYearlyNetIncome()))
                                ? (quickStatisticsDto.getYearlyNetIncome()
                                .divide(lastYearSamePeriodIncome, 4, RoundingMode.HALF_UP))
                                .subtract(BigDecimal.ONE)
                                .multiply(BigDecimal.valueOf(100.0))
                                : null,
                lastYearIncome = (BigDecimal) restClient.getSync(
                        "/api/statistics/quick/yearly-net-income/" + (localDate.getYear() - 1), BigDecimal.class, httpServletRequest),
                growthPercentage = (!Objects.isNull(lastYearIncome) && lastYearIncome.compareTo(BigDecimal.ZERO) != 0
                        && !Objects.isNull(quickStatisticsDto.getYearlyNetIncome()))
                        ? (quickStatisticsDto.getYearlyNetIncome().divide(lastYearIncome, 4, RoundingMode.HALF_UP))
                        .subtract(BigDecimal.ONE)
                        .multiply(BigDecimal.valueOf(100.0))
                        : BigDecimal.ZERO,
                lastYearEfficiency = (!Objects.isNull(lastYearIncome)
                        && !Objects.isNull(quickStatisticsDto.getLastYearBurnedHours())
                        && quickStatisticsDto.getLastYearBurnedHours() != 0)
                        ? lastYearIncome.divide(BigDecimal.valueOf(quickStatisticsDto.getLastYearBurnedHours()), 4, RoundingMode.HALF_UP)
                        : BigDecimal.ZERO,
                efficiencyGrowthPercentage = (!Objects.isNull(quickStatisticsDto.getYearlyEfficiency())
                        && !Objects.isNull(lastYearEfficiency)
                        && lastYearEfficiency.compareTo(BigDecimal.ZERO) != 0)
                        ? (quickStatisticsDto.getYearlyEfficiency().divide(lastYearEfficiency, 4, RoundingMode.HALF_UP))
                        .subtract(BigDecimal.ONE)
                        .multiply(BigDecimal.valueOf(100.0))
                        : BigDecimal.ZERO,
                goalCompletionsGrowthPercentage = quickStatisticsDto.getLastYearRevenueGoalCompletionPercentage() != 0 ?
                        (BigDecimal.valueOf(quickStatisticsDto.getYearlyRevenueGoalCompletionPercentage())
                                .divide(BigDecimal.valueOf(quickStatisticsDto.getLastYearRevenueGoalCompletionPercentage()), RoundingMode.HALF_UP))
                                .subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100.0))
                        : BigDecimal.ZERO,
                previousToCurrentYearlyLongestWeeklyGoalStreakProportion = !Objects.isNull(previousYearlyLongestWeeklyGoalStreak) && previousYearlyLongestWeeklyGoalStreak != 0 ?
                        BigDecimal.valueOf(yearlyLongestWeeklyGoalStreak)
                                .subtract(BigDecimal.valueOf(previousYearlyLongestWeeklyGoalStreak)) // (current - previous)
                                .divide(BigDecimal.valueOf(previousYearlyLongestWeeklyGoalStreak), 4, RoundingMode.HALF_UP) // divide by previous
                                .multiply(BigDecimal.valueOf(100))
                        : BigDecimal.ZERO;

        ClientDynamicDataDto lastYearYearlyClientDynamic = (ClientDynamicDataDto) restClient.getSync(
                "/api/statistics/quick/yearly-client-dynamic?from=" + (localDate.minusYears(1).withDayOfYear(1).atStartOfDay()) + "&to=" + (LocalDateTime.now().minusYears(1)), ClientDynamicDataDto.class, httpServletRequest);
        Double growPercentageYearlyClientDynamic = quickStatisticsDto.getYearlyClientDynamicData().getYearlyClientDynamic() - lastYearYearlyClientDynamic.getYearlyClientDynamic();

        model.addAttribute("lastYearSamePeriodIncome", lastYearSamePeriodIncome);
        model.addAttribute("growthSamePeriodPercentage", relativeGrowthPercentage);

        model.addAttribute("currentYearIncome", quickStatisticsDto.getYearlyNetIncome());
        model.addAttribute("lastYearIncome", lastYearIncome);
        model.addAttribute("growthPercentage", growthPercentage);

        model.addAttribute("yearlyEfficiency", quickStatisticsDto.getYearlyEfficiency());
        model.addAttribute("lastYearEfficiency", lastYearEfficiency);
        model.addAttribute("efficiencyGrowthPercentage", efficiencyGrowthPercentage);
        /* yearly client dynamic */
        model.addAttribute("currentYearYearlyClientDynamic", quickStatisticsDto.getYearlyClientDynamicData().getYearlyClientDynamic());
        model.addAttribute("currentYearAllClients", quickStatisticsDto.getYearlyClientDynamicData().getAllClients());
        model.addAttribute("currentYearNewClients", quickStatisticsDto.getYearlyClientDynamicData().getNewClients());
        model.addAttribute("currentYearLostClients", quickStatisticsDto.getYearlyClientDynamicData().getLostClients());
        model.addAttribute("currentYear", Year.now().getValue());
        model.addAttribute("lastYearYearlyClientDynamic", lastYearYearlyClientDynamic.getYearlyClientDynamic());
        model.addAttribute("lastYearAllClients", lastYearYearlyClientDynamic.getAllClients());
        model.addAttribute("lastYearNewClients", lastYearYearlyClientDynamic.getNewClients());
        model.addAttribute("lastYearLostClients", lastYearYearlyClientDynamic.getLostClients());
        model.addAttribute("lastYear", (Year.now().getValue() - 1));
        model.addAttribute("growPercentageYearlyClientDynamic", growPercentageYearlyClientDynamic);

        model.addAttribute("yearlyGoalCompletions", quickStatisticsDto.getYearlyRevenueGoalCompletionPercentage());
        model.addAttribute("lastYearGoalCompletions", quickStatisticsDto.getLastYearRevenueGoalCompletionPercentage());
        model.addAttribute("goalCompletionsGrowthPercentage", goalCompletionsGrowthPercentage);

        model.addAttribute("yearlyLongestWeeklyGoalStreak", yearlyLongestWeeklyGoalStreak);
        model.addAttribute("previousYearlyLongestWeeklyGoalStreak", previousYearlyLongestWeeklyGoalStreak);
        model.addAttribute("previousToCurrentYearlyLongestWeeklyGoalStreakProportion", previousToCurrentYearlyLongestWeeklyGoalStreakProportion);
        return quickStatisticsDto;
    }

    private void initGoalsCompletion(Model model, QuickStatisticsDto quickStatisticsDto) {
        /* daily hours */
        model.addAttribute("dailyBurnedHours", quickStatisticsDto.getDailyBurnedHours());
        model.addAttribute("dailyTotalHours", quickStatisticsDto.getDailyTotalHours());
        model.addAttribute("dailyBurnedHoursPercentage", quickStatisticsDto.getDailyBurnedHoursPercentage());
        /* daily revenue */
        model.addAttribute("dailyRevenue", quickStatisticsDto.getDailyRevenue());
        model.addAttribute("dailyTotalRevenue", quickStatisticsDto.getDailyTotalRevenue());
        model.addAttribute("dailyRevenuePercentage", quickStatisticsDto.getDailyRevenuePercentage());
        /* weekly hours */
        model.addAttribute("weeklyBurnedHours", quickStatisticsDto.getWeeklyBurnedHours());
        model.addAttribute("weeklyTotalHours", quickStatisticsDto.getWeeklyTotalHours());
        model.addAttribute("weeklyBurnedHoursPercentage", quickStatisticsDto.getWeeklyBurnedHoursPercentage());
        /* weekly revenue */
        model.addAttribute("weeklyRevenue", quickStatisticsDto.getWeeklyRevenue());
        model.addAttribute("weeklyTotalRevenue", quickStatisticsDto.getWeeklyTotalRevenue());
        model.addAttribute("weeklyRevenuePercentage", quickStatisticsDto.getWeeklyRevenuePercentage());
        /* last week hours */
        model.addAttribute("lastWeekBurnedHours", quickStatisticsDto.getLastWeekBurnedHours());
        model.addAttribute("lastWeekTotalHours", quickStatisticsDto.getLastWeekTotalHours());
        model.addAttribute("lastWeekBurnedHoursPercentage", quickStatisticsDto.getLastWeekBurnedHoursPercentage());
        /* last week revenue */
        model.addAttribute("lastWeekRevenue", quickStatisticsDto.getLastWeekRevenue());
        model.addAttribute("lastWeekTotalRevenue", quickStatisticsDto.getLastWeekTotalRevenue());
        model.addAttribute("lastWeekRevenuePercentage", quickStatisticsDto.getLastWeekRevenuePercentage());
    }

    private void initGeneralCollectionData(Model model, QuickStatisticsDto quickStatisticsDto) {
        GeneralCollectionDataDto generalCollectionDataDto = (GeneralCollectionDataDto) restClient.getSync(
                "/api/statistics/quick/general-collection-data", GeneralCollectionDataDto.class, httpServletRequest);
        BigDecimal notCollectedAmount = generalCollectionDataDto.getNotCollectedAmount(),
                invoicedAmount = generalCollectionDataDto.getInvoicedAmount();
        int notCollectedAmountPercentage = Objects.isNull(notCollectedAmount) || Objects.isNull(quickStatisticsDto.getYearlyNetIncome())
                || quickStatisticsDto.getYearlyNetIncome().compareTo(BigDecimal.ZERO) == 0 ? 0
                : (notCollectedAmount.divide(quickStatisticsDto.getYearlyNetIncome(), RoundingMode.HALF_UP)).multiply(BigDecimal.valueOf(100.0)).intValue(),
                invoicedAmountPercentage = Objects.isNull(invoicedAmount) || Objects.isNull(quickStatisticsDto.getYearlyNetIncome())
                        || quickStatisticsDto.getYearlyNetIncome().compareTo(BigDecimal.ZERO) == 0 ? 0
                        : (invoicedAmount.divide(quickStatisticsDto.getYearlyNetIncome(), RoundingMode.HALF_UP)).multiply(BigDecimal.valueOf(100.0)).intValue();
        model.addAttribute("generalCollectionDataNotCollectedAmount", Objects.isNull(generalCollectionDataDto.getNotCollectedAmount()) ? 0 : generalCollectionDataDto.getNotCollectedAmount().intValue());
        model.addAttribute("generalCollectionDataInvoicedAmount", Objects.isNull(generalCollectionDataDto.getInvoicedAmount()) ? 0 : generalCollectionDataDto.getInvoicedAmount().intValue());
        model.addAttribute("generalCollectionDataNotCollectedAmountPercentage", Math.min(notCollectedAmountPercentage, 100));
        model.addAttribute("generalCollectionDataInvoicedAmountPercentage", Math.min(invoicedAmountPercentage, 100));
    }

    private void initChartQuickWeeklyStatistics(Model model) {
        LocalDateTime[] dates = initializeDates();
        LocalDateTime fromDate = dates[0], toDate = dates[1];
        int noOfWeeks = (int) ChronoUnit.WEEKS.between(fromDate, toDate);

        ChartQuickStatisticsDto chartQuickWeeklyStatisticsDto = (ChartQuickStatisticsDto) restClient.getSync(
                "/api/statistics/quick/chart-weekly?from=" + fromDate + "&to=" + toDate + "&noOfWeeks=" + Math.max(noOfWeeks, 1),
                ChartQuickStatisticsDto.class, httpServletRequest);
        String chartPeriodLabels = (chartQuickWeeklyStatisticsDto == null || chartQuickWeeklyStatisticsDto.getChartPeriodLabels() == null)
                ? "[]"
                : "[" + String.join(", ", chartQuickWeeklyStatisticsDto.getChartPeriodLabels()) + "]";
        String chartRevenueValues = (chartQuickWeeklyStatisticsDto == null || chartQuickWeeklyStatisticsDto.getChartRevenueValues() == null)
                ? "[]"
                : "[" + Arrays.stream(chartQuickWeeklyStatisticsDto.getChartRevenueValues()).map(e -> ((double) e) / 100.0).map(String::valueOf).collect(Collectors.joining(", ")) + "]";
        String chartHourValues = (chartQuickWeeklyStatisticsDto == null || chartQuickWeeklyStatisticsDto.getChartHourValues() == null)
                ? "[]"
                : "[" + Arrays.stream(chartQuickWeeklyStatisticsDto.getChartHourValues()).map(String::valueOf).collect(Collectors.joining(", ")) + "]";

        model.addAttribute("chartDateFrom", chartQuickWeeklyStatisticsDto == null ? null : chartQuickWeeklyStatisticsDto.getDateFrom());
        model.addAttribute("chartDateTo", chartQuickWeeklyStatisticsDto == null ? null : chartQuickWeeklyStatisticsDto.getDateTo());
        model.addAttribute("chartPeriodLabels", chartPeriodLabels);
        model.addAttribute("chartRevenueValues", chartRevenueValues);
        model.addAttribute("chartHourValues", chartHourValues);
    }

    private void initAdditionalDetails(Model model) {
        Long count = (Long) restClient.getSync("/api/collections/not-collected-entries-count", Long.class, httpServletRequest);
        model.addAttribute("notCollectedEntriesCount", count);
    }

    private LocalDateTime[] initializeDates() {
        return new LocalDateTime[]{LocalDateTime.of(LocalDate.now().minusYears(1).withDayOfYear(1), LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MIN)};
    }
}
