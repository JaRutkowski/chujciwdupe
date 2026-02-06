package com.jfs.operations.lite.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Builder
@Getter
public class TodoDto {
    private String title;
    private String checked;
    private String start;
    private String end;
    private Double duration;
    private String type;
    private String onlineMeetURL;

    @AllArgsConstructor
    @Getter
    public enum Type {
        CURRENT("badge-success"), STARTING_SOON("badge-warning"), STANDARD("badge-primary"),
        DONE("badge-primary"), MISSED("badge-danger"), UNKNOWN("");

        private String name;

        public static Type findByName(String name) {
            return Arrays.stream(values()).filter(e -> name.equals(e.getName())).findFirst().orElse(UNKNOWN);
        }
    }
}
