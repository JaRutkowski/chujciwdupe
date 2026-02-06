package com.jfs.operations.lite.dto.accounting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class DonutChartEarnedUnearnedStatisticsDto {
    private String dateFrom;
    private String dateTo;
    private String[] donutChartLabels;
    private String[] donutChartEarnedColors;
    private String[] donutChartUnearnedColors;
    private String[] donutChartSumColors;
    private String[] donutChartEarnedNames;
    private String[] donutChartUnearnedNames;
    private String[] donutChartSumNames;
    private Integer[] donutChartEarnedValues;
    private Integer[] donutChartUnearnedValues;
    private Integer[] donutChartSumValues;
}
