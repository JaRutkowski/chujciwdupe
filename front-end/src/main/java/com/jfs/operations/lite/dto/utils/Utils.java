package com.jfs.operations.lite.dto.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

@UtilityClass
public class Utils { //TODO: #12 to be deleted
    public Calendar toCalendar(LocalDate localDate) {
        if (Objects.isNull(localDate)) return null;
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public Calendar toCalendar(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) return null;
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public LocalDateTime toLocalDateTime(Calendar calendar) {
        if (Objects.isNull(calendar)) return null;
        TimeZone tz = calendar.getTimeZone();
        ZoneId zoneId = tz.toZoneId();
        return LocalDateTime.ofInstant(calendar.toInstant(), zoneId);
    }

    public Calendar get1900() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1900, Calendar.JANUARY, 1);
        return calendar;
    }

    public String encodeString(String string) {
        if (Objects.isNull(string)) return null;
        return UriUtils.encode(string, StandardCharsets.UTF_8);
    }

    public String decodeString(String string) {
        if (Objects.isNull(string)) return null;
        return UriUtils.decode(string, StandardCharsets.UTF_8);
    }
    public String removeSpecialCharacters(String string) {
        if (string == null) return null;
        string = string.replace("Ł", "L").replace("ł", "l");
        return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
