package com.bxrbasov.app.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class PropertiesUtilTest {

    @ParameterizedTest
    @MethodSource("loadPropertiesStream")
    void loadProperties(String filename, String result) {
        String props = PropertiesUtil.get(filename);
        Assertions.assertEquals(props, result);
    }

    static Stream<Arguments> loadPropertiesStream() {
        return Stream.of(
                Arguments.of("db.url.users", "jdbc:postgresql://localhost:5433/Users"),
                Arguments.of("db.user", "postgres"),
                Arguments.of("db.password", "password")
        );
    }

}