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
    void updateCellThrowsCellOutOfRangeExceptionWhenCellAddressIsOutOfRange() {
        board.init();
        assertThrows(CellOutOfRangeException.class, () ->
                board.updateCell(
                        Seed.NOUGHT,
                        CellRange.MAXIMAL.getValue() + 1,
                        CellRange.MAXIMAL.getValue() + 1
                ));
    }

    @Test
    void isDrawAfter9Moves() {
        board.init();
        board.updateCell(Seed.CROSS, 1, 1);
        board.updateCell(Seed.NOUGHT, 1, 2);
        board.updateCell(Seed.CROSS, 1, 3);
        board.updateCell(Seed.CROSS, 2, 1);
        board.updateCell(Seed.NOUGHT, 2, 2);
        board.updateCell(Seed.CROSS, 2, 3);
        board.updateCell(Seed.NOUGHT, 3, 1);
        board.updateCell(Seed.CROSS, 3, 2);
        board.updateCell(Seed.NOUGHT, 3, 3);
        assertTrue(board.isDraw(Seed.NOUGHT, 3,3));
    }

    @Test
    void isNotDrawAfter9Moves() {
        board.init();
        board.setMoveCounter(8);
        board.hasWon(Seed.NOUGHT, 3, 3);
        assertFalse(board.isDraw(Seed.NOUGHT, 3,3));

    }

    @Test
    void hasWonIsFalseAfter1Move() {
        board.init();
        assertFalse(board.hasWon(Seed.NOUGHT, 1, 1));
    }

    @Test
    void hasWonIsTrueWhen3SameSeedsInColumn() {
        board.init();
        board.updateCell(Seed.NOUGHT, 1, 1);
        board.updateCell(Seed.NOUGHT, 2, 1);
        board.updateCell(Seed.NOUGHT, 3, 1);
        board.hasWon(Seed.NOUGHT, 3, 1);
    }

    @Test
    void hasWonIsTrueWhen3SameSeedsInRow() {
        board.init();
        board.updateCell(Seed.NOUGHT, 2, 1);
        board.updateCell(Seed.NOUGHT, 2, 2);
        board.updateCell(Seed.NOUGHT, 2, 3);
        board.hasWon(Seed.NOUGHT, 2, 3);
    }


}