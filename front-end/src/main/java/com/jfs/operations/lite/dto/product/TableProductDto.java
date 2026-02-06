package com.jfs.operations.lite.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class TableProductDto {
	private String id;
	private String name;
	private String code;
	private String vat;
	private String type;
	private String priceNet;
	private String priceGross;
	private String isActive;
}
