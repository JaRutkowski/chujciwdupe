package com.jfs.operations.lite.dto.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {
	private String username;
	private String firstName;
	private String lastName;
	private String email;
}
