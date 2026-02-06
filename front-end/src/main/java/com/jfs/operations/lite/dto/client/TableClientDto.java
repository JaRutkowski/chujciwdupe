package com.jfs.operations.lite.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class TableClientDto {
	private String id;
	private String name;
	private String displayName;
	private String source;
	private String contactDate;
	private String firstMeetDateTime;
	private String lastMeetDateTime;
	private String address;
	private String postalCode;
	private String city;
	private String country;
	private String email;
	private String phoneNo;
	private String onlineMeetURL;
	private String nip;
	private String isEntity;
	private String isActive;
	private String price;
	private String paymentUnit;
	private String paymentDuration;
	private String paymentType;
}
