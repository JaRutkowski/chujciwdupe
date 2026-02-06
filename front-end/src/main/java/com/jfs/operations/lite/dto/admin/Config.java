package com.jfs.operations.lite.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Config {
    private Integer sheetIndex = 0;
    private Integer headerRowIndex = 0;
    private Integer startRowIndex = 1;
    private Integer step = 1;

    public static Config defaultConf() {
        return new Config();
    }
}
