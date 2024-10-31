package com.bxrbasov.app.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

class LocalDateFormatterTest {

    @Test
    void formatDateTime() {
        String dateTime = "2024-10-21 18:28:00.0";

        LocalDateTime dateFormatter = LocalDateFormatter.format(dateTime);

        Assertions.assertEquals(LocalDateTime.of(2024, 10, 21, 18, 28, 00), dateFormatter);
    }

    @Test
    void formatDateTimeParseException() {
        String dateTime = "2024-10-21";

        Assertions.assertThrows(DateTimeParseException.class, () -> LocalDateFormatter.format(dateTime));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsFormat")
    void formatDateTimeIsValid(String dateTime, boolean expected) {
        boolean dateTimeIsValid = LocalDateFormatter.isValid(dateTime);
        Assertions.assertEquals(dateTimeIsValid, expected);
    }

    static Stream<Arguments> getArgumentsFormat() {
        return Stream.of(
                Arguments.of("2024-10-21", false),
                Arguments.of("2024-10-21 21:10:12", true),
                Arguments.of("2024-10-21 10:21:46", true),
                Arguments.of("2024-15-21", false),
                Arguments.of("2024-10-89", false)
        );
    }
}