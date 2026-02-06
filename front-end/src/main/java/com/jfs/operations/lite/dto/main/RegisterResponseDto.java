package com.jfs.operations.lite.dto.main;

import lombok.Builder;

@Builder
public record RegisterResponseDto(
        boolean register,
        String message,
        String totpQr) { }

