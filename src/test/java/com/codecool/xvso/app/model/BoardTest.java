package com.codecool.xvso.app.model;

import com.codecool.xvso.app.exception.CellAlreadyHasContentException;
import com.codecool.xvso.app.exception.CellOutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * com.codecool.xvso.app.model
 * Created by bujak on 13.06.17.
 */
class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.init();
    }

    @DisplayName("Initialized board is an array with cells with empty seed")
    @Test
    void isInitializedBoardHasArrayWithEmptyCells() {
        Boolean isEmpty = true;
        for (Cell[] cells : board.getCells()) {
            for (Cell cell : cells) {
                if (!cell.getContent().equals(Seed.EMPTY)) {
                    isEmpty = false;
                    break;
                }
            }
        }
        assertTrue(isEmpty);
    }

    @DisplayName("Update cell throws exception when cell adress out of range")
    @Test
    void isUpdateCellThrowsCellOutOfRangeExceptionWhenCellAddressIsOutOfRange() {
        int rangeIncreaser = 1;
        assertThrows(CellOutOfRangeException.class, () ->
                board.updateCell(
                        Seed.NOUGHT,
                        CellRange.MAXIMAL.getValue() + rangeIncreaser,
                        CellRange.MAXIMAL.getValue() + rangeIncreaser
                ));
    }

    @DisplayName("isDraw return true when draw configuaration")
    @Test
    void isDrawReturnsTrueAfter9MovesWithDrawConfiguration() {
        //inserting cells for draw configuration
        int[][] crossCoordinates = {{1, 1}, {1, 3}, {2, 1}, {2, 3}, {3, 2}};
        int[][] noughtCoordinates = {{1, 2}, {2, 2}, {3, 1}, {3, 3}};
        for (int[] pair : crossCoordinates) {
            board.updateCell(Seed.CROSS, pair[0], pair[1]);
        }
        for (int[] pair : noughtCoordinates) {
            board.updateCell(Seed.NOUGHT, pair[0], pair[1]);
        }
        assertTrue(board.isDraw(Seed.NOUGHT, CellRange.MAXIMAL.getValue(), CellRange.MAXIMAL.getValue()));
    }

    @DisplayName("hasWon returns false after 1 move")
    @Test
    void isHasWonReturnsFalseAfter1Move() {
        assertFalse(board.hasWon(Seed.NOUGHT, CellRange.MINIMAL.getValue(), CellRange.MINIMAL.getValue()));
    }

    @DisplayName("hasWon returns true when 3 same seed in column")
    @Test
    void hasWonReturnsTrueWhen3SameSeedsInColumn() {
        int column = CellRange.MAXIMAL.getValue();
        for (int row = 1; row <= CellRange.MAXIMAL.getValue(); row++) {
            board.updateCell(Seed.NOUGHT, row, column);
        }
        assertTrue(board.hasWon(Seed.NOUGHT, CellRange.MINIMAL.getValue(), column));

    }

    @DisplayName("hasWon returns true when 3 same seed in row")
    @Test
    void hasWonReturnsTrueWhen3SameSeedsInRow() {
        int row = CellRange.MINIMAL.getValue();
        for (int column = 1; column <= CellRange.MAXIMAL.getValue(); column++) {
            board.updateCell(Seed.NOUGHT, row, column);
        }
        assertTrue(board.hasWon(Seed.NOUGHT, row, CellRange.MINIMAL.getValue()));

    }

    @DisplayName("Throw CellAlreadyHasContentException when Player enters taken row and column")
    @Test
    void isUpdateGameStateCellAlreadyHasContentExceptionWhenPlayerEnterTakenRowAndColumn() {
        int row = CellRange.MINIMAL.getValue();
        int column = CellRange.MAXIMAL.getValue();
        board.updateCell(Seed.CROSS, row, column);
        assertThrows(CellAlreadyHasContentException.class, () -> board.updateCell(Seed.NOUGHT, row, column))
        ;
    }
}