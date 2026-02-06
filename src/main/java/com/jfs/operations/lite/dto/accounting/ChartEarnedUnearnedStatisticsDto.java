package com.jfs.operations.lite.dto.accounting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class ChartEarnedUnearnedStatisticsDto {
    private String dateFrom;
    private String dateTo;
    private String[] chartPeriodLabels;
    private Integer[] chartEarnedValues;
    private Integer[] chartUnearnedValues;
    private Integer[] chartSumValues;
}
