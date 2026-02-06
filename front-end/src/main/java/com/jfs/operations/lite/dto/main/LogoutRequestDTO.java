package com.jfs.operations.lite.dto.main;

import lombok.Builder;

@Builder
public record LogoutRequestDTO(
        String jwtToken,
        String authorizationToken) { }
