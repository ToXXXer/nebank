package com.bxrbasov.app.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class LocalDateFormatter {
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    public static LocalDateTime format(String date) {
        return LocalDateTime.parse(date, FORMATTER);
    }

    public static boolean isValid(String date) {
        try {
            return Optional.ofNullable(date)
                    .map(LocalDateFormatter::format)
                    .isPresent();
        } catch (DateTimeParseException exception) {
            return false;
        }
    }
}
