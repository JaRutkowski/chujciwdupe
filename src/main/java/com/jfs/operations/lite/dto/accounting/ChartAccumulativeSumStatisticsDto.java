package com.jfs.operations.lite.dto.accounting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class ChartAccumulativeSumStatisticsDto {
    private String dateFrom;
    private String dateTo;
    private String[] chartPeriodLabels;
    private Integer[] chartSumValues;
    /* Income divided by number of hours in period */
    //TODO: consider using Double values
    private Integer[] chartEfficiencyValues;
}
