package com.codecool.xvso.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * com.codecool.xvso.model
 * Created by bujak on 14.06.17.
 */
class GameTest {

    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void isInitGameInitializedEmptyBoard() {
        game.initGame();
        Board board = new Board();
        board.init();
        assertSame(board, game.getBoard());
    }

    @ParameterizedTest
    @CsvSource(value = {"CROSS,1,1", "NOUGHT, 1,2", "CROSS,2,3", "NOUGHT,3,3"})
    void isUpdateGameStateUpdateUpdateProperCells(Seed seed, int row, int column){
        game.initGame();
        game.updateGameState(seed, row, column);
        Cell cell = new Cell(row,column);
        cell.setContent(seed);
        assertSame(cell, game.getBoard().getCells()[row][column]);
    }

    @RepeatedTest(10)
    void isCurrentPlayerChosenAfterGameInit() {
        game.initGame();
        assertTrue(game.getCurrentPlayer().equals(Seed.CROSS) || game.getCurrentPlayer().equals(Seed.NOUGHT));
    }

    @RepeatedTest(10)
    void isChoseRandomFirstPlayerDoNotSetSeedEmpty() {
        game.initGame();
        assertFalse(game.getCurrentPlayer().equals(Seed.EMPTY));

    }
}