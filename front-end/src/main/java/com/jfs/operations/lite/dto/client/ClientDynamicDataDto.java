package com.jfs.operations.lite.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ClientDynamicDataDto {
    private Double yearlyClientDynamic;
    private Integer allClients;
    private Integer newClients;
    private Integer lostClients;
}
