package com.jfs.operations.lite.dto.account.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class TokenDto {
	//TODO Custom deserializer
	private String access_token;
	private long expires_in;
	private String token_type;
}
