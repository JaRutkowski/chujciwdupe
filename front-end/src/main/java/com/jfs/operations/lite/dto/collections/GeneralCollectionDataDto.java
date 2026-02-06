package com.jfs.operations.lite.dto.collections;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class GeneralCollectionDataDto {
    private BigDecimal notCollectedAmount;
    private BigDecimal invoicedAmount;
}
