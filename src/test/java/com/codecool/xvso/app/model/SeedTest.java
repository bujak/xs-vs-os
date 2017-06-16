package com.codecool.xvso.app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * com.codecool.xvso.app.model
 * Created by bujak on 14.06.17.
 */
class SeedTest {

    @DisplayName("getValue returns proper values")
    @ParameterizedTest
    @CsvSource(value = {"CROSS, X", "NOUGHT, O", "EMPTY, ' '"})
    void seedGetValueReturnProperValue(Seed seed, String value) {
        assertEquals(seed.getValue(), value);
    }
}