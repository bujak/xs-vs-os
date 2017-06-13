package com.codecool.xvso.model;

import com.codecool.xcso.exception.CellOutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * com.codecool.xvso.model
 * Created by bujak on 13.06.17.
 */
class BoardTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();

    }

    @Test
    void createEmptyConstructor() {
        Board newBoard = new Board();
        assertSame(Board.class, newBoard.getClass());
    }

    @Test
    void isEmptyInitializedBoard3x3Array() {
        board.init();
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

    @Test
    void hasWonThrowsCellOutOfRangeExceptionWhenCellAddressIsOutOfRange() {
        assertThrows(CellOutOfRangeException.class, () ->
                board.hasWon(
                        Seed.NOUGHT,
                        CellRange.MAXIMAL.getValue() + 1,
                        CellRange.MAXIMAL.getValue() + 1
                ));
    }

    @Test
    void isDrawAfter9Moves() {
        board.setMoveCounter(9);
        if (board.hasWon(Seed.NOUGHT, 9, 9).equals(false) && board.getMoveCounter() == 9) {
            assertTrue(board.isDraw());
        }
    }

    @Test
    void isNotDrawAfter9Moves() {
        board.setMoveCounter(9);
        if (board.hasWon(Seed.NOUGHT, 9, 9).equals(true) && board.getMoveCounter() == 9) {
            assertFalse(board.isDraw());
        }
    }

    @Test
    void hasWonIsFalseAfter1Move() {
        assertFalse(board.hasWon(Seed.NOUGHT, 1, 1));
    }

    @Test
    void hasWonIsTrueWhenWith3SameSeedsInColumn() {
        board.updateCell(Seed.NOUGHT, 1, 1);
        board.updateCell(Seed.NOUGHT, 2, 1);
        board.updateCell(Seed.NOUGHT, 3, 1);
        board.hasWon(Seed.NOUGHT, 3, 1);
    }

    @Test
    void hasWonIsTrueWhenWith3SameSeedsInRow() {
        board.updateCell(Seed.NOUGHT, 2, 1);
        board.updateCell(Seed.NOUGHT, 2, 2);
        board.updateCell(Seed.NOUGHT, 2, 3);
        board.hasWon(Seed.NOUGHT, 2, 3);
    }


}