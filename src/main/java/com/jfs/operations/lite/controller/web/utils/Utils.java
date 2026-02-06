package com.jfs.operations.lite.controller.web.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class Utils {
    @Value("${app.statistics.default-period}")
    private Integer statisticsDefaultPeriod;

    public LocalDate[] initializeDates(String from, String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return new LocalDate[]{
                (Objects.isNull(from) || from.isEmpty())
                        ? LocalDate.now().minusMonths(statisticsDefaultPeriod).withDayOfYear(1)
                        : LocalDate.parse(from, formatter),
                (Objects.isNull(to) || to.isEmpty())
                        ? LocalDate.now()
                        : LocalDate.parse(to, formatter)
        };
    }
}
