package com.codecool.xvso.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CellTest {

    Cell cell;

    @BeforeEach
    void setUp() {
        cell = new Cell(1, 1);
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Throw exceptions if row and column are above 3 or below 1")
    @ParameterizedTest
    @CsvSource(value = {"0,0", "-1,2", "2,-1", "5,3", "4,4"})
    void isConstructorRowAndCellValid(Integer row, Integer column) {
        assertThrows(IllegalArgumentException.class, () -> new Cell(row, column));
    }

    @DisplayName("Check if cell content is empty after create")
    @Test
    void checkContentIsEmptyAfterCreate() {
        assertEquals(Seed.EMPTY, cell.getContent());
    }

    @DisplayName("Set content cell and get cell content")
    @Test
    void setCellContentAndGetCellContent() {
        cell.setContent(Seed.CROSS);
        assertEquals(Seed.CROSS, cell.getContent());
    }

    @DisplayName("Clear cell content value and set it to Empty")
    @Test
    void clearCellContentAndSetToEmpty() {
        cell.setContent(Seed.CROSS);
        cell.clear();
        assertEquals(Seed.EMPTY, cell.getContent());
    }

}