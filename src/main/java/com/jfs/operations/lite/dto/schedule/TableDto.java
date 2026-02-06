package com.jfs.operations.lite.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class TableDto { //TODO: change to TableEventDto
	private String id;
	private String title;
	private String description;
	private String typeValue;
	private String statusValue;
	private String dateFrom;
	private String dateTo;
	private String time;
	private String paymentStatusValue;
	private String incomeNet;
	private String incomeGross;
}
