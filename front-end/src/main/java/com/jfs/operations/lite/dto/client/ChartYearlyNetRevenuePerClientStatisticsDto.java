package com.jfs.operations.lite.dto.client;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ChartYearlyNetRevenuePerClientStatisticsDto {
    private String[] chartClientLabels;
    private BigDecimal[] chartYearlyNetRevenuePerClientValues;
}
