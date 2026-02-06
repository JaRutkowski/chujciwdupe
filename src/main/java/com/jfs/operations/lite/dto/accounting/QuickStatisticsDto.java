package com.jfs.operations.lite.dto.accounting;

import com.jfs.operations.lite.dto.client.ClientDynamicDataDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Setter
@Getter
@Component
public class QuickStatisticsDto {
    private BigDecimal yearlyNetIncome;
    private BigDecimal yearlyEfficiency;
    private Double lastYearBurnedHours;
    private ClientDynamicDataDto yearlyClientDynamicData;
    private Integer yearlyRevenueGoalCompletionTotalPeriods;
    private Integer yearlyRevenueGoalCompletion;
    private Integer yearlyRevenueGoalCompletionPercentage;
    private Integer lastYearRevenueGoalCompletionTotalPeriods;
    private Integer lastYearRevenueGoalCompletion;
    private Integer lastYearRevenueGoalCompletionPercentage;
    private Integer yearlyLongestWeeklyGoalStreak;
    private Integer previousYearlyLongestWeeklyGoalStreak;
    private Double dailyBurnedHours;
    private Double dailyTotalHours;
    private String dailyBurnedHoursPercentage;
    private BigDecimal dailyRevenue;
    private BigDecimal dailyTotalRevenue;
    private String dailyRevenuePercentage;
    private Double weeklyBurnedHours;
    private String weeklyBurnedHoursPercentage;
    private Double weeklyTotalHours;
    private BigDecimal weeklyRevenue;
    private BigDecimal weeklyTotalRevenue;
    private String weeklyRevenuePercentage;
    private Double lastWeekBurnedHours;
    private String lastWeekBurnedHoursPercentage;
    private Double lastWeekTotalHours;
    private BigDecimal lastWeekRevenue;
    private BigDecimal lastWeekTotalRevenue;
    private String lastWeekRevenuePercentage;

    public int getYearlyRevenueGoalCompletionPercentage() {
        return !Objects.isNull(yearlyRevenueGoalCompletionTotalPeriods) && yearlyRevenueGoalCompletion != 0 ? ((yearlyRevenueGoalCompletion / yearlyRevenueGoalCompletionTotalPeriods) * 100) : 0;
    }

    public int getLastYearRevenueGoalCompletionPercentage() {
        return !Objects.isNull(lastYearRevenueGoalCompletionTotalPeriods) && lastYearRevenueGoalCompletion != 0 ? ((lastYearRevenueGoalCompletion / lastYearRevenueGoalCompletionTotalPeriods) * 100) : 0;
    }

    public int getDailyBurnedHoursPercentage() {
        return !Objects.isNull(dailyTotalHours) && dailyTotalHours != 0 ? (int) ((dailyBurnedHours / dailyTotalHours) * 100) : 0;
    }

    public int getWeeklyBurnedHoursPercentage() {
        return !Objects.isNull(weeklyTotalHours) && weeklyTotalHours != 0 ? (int) ((weeklyBurnedHours / weeklyTotalHours) * 100) : 0;
    }

    public int getLastWeekBurnedHoursPercentage() {
        return !Objects.isNull(lastWeekTotalHours) && lastWeekTotalHours != 0 ? (int) ((lastWeekBurnedHours / lastWeekTotalHours) * 100) : 0;
    }

    public int getDailyRevenuePercentage() {
        return !Objects.isNull(dailyTotalRevenue) && dailyTotalRevenue.compareTo(BigDecimal.ZERO) != 0 ? (int) ((dailyRevenue.divide(dailyTotalRevenue, RoundingMode.HALF_UP).doubleValue()) * 100) : 0;
    }

    public int getWeeklyRevenuePercentage() {
        return !Objects.isNull(weeklyTotalRevenue) && weeklyTotalRevenue.compareTo(BigDecimal.ZERO) != 0 ? (int) ((weeklyRevenue.divide(weeklyTotalRevenue, RoundingMode.HALF_UP).doubleValue()) * 100) : 0;
    }

    public int getLastWeekRevenuePercentage() {
        return !Objects.isNull(lastWeekTotalRevenue) && lastWeekTotalRevenue.compareTo(BigDecimal.ZERO) != 0 ? (int) ((lastWeekRevenue.divide(lastWeekTotalRevenue, RoundingMode.HALF_UP).doubleValue()) * 100) : 0;
    }

    public Integer getyearlyLongestWeeklyGoalStreak(){
        return !Objects.isNull(yearlyLongestWeeklyGoalStreak) && yearlyLongestWeeklyGoalStreak != 0 ? yearlyLongestWeeklyGoalStreak : 0;
    }
}
