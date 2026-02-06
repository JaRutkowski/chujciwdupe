package com.jfs.operations.lite.dto.schedule;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CalendarDto {
	int id;
	String title;
	String start;
	String end;
	String color;
	String onlineMeetURL;
}
