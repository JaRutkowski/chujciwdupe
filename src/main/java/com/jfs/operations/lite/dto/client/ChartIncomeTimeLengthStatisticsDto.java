package com.jfs.operations.lite.dto.client;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ChartIncomeTimeLengthStatisticsDto {
    private String[] chartClientLabels;
    private List<BigDecimal> chartYearlyNetRevenuePerClientValues;
    private List<BigDecimal> chartAvgWeeklyTimeAllocationValues;
    private List<Long> chartTotalCooperationTimeValues;
}
