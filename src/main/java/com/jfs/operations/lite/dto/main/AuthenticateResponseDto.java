package com.jfs.operations.lite.dto.main;

import lombok.Builder;

import java.util.Map;

@Builder
public record AuthenticateResponseDto(
        boolean authorized,
        String message,
        Map<String, String> params) {
}
