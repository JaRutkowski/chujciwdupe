package com.jfs.operations.lite.dto.accounting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class ChartQuickStatisticsDto {
    private String dateFrom;
    private String dateTo;
    private String[] chartPeriodLabels;
    private String[] periodFullLabels;
    private Integer[] chartRevenueValues;
    private Integer[] chartHourValues;
}
