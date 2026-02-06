package com.jfs.operations.lite.dto.schedule;

import lombok.*;

import java.math.BigDecimal;
import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableEntryCollectionDataDto {

    private String title;
    private String paymentType;
    private BigDecimal amountGross;
    private BigDecimal amountNet;
    private Integer time;
    private Calendar dateFrom;
    private Calendar dateTo;
    private String monthFrom;
    private String yearFrom;
    private String monthTo;
    private String description;

}
