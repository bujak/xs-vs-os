package com.codecool.xvso.app.model;

import com.codecool.xvso.app.exception.CellOutOfRangeException;
import com.codecool.xvso.app.exception.WrongPlayerException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * com.codecool.xvso.app.model
 * Created by bujak on 14.06.17.
 */
class GameTest {

    Game game;

    @BeforeEach
    void setUp() {
        game = new Game(new Board());
    }

    @Test
    void isInitGameInitializedBoard() {
        game.initGame();
        Board board = new Board();
        board.init();
        assertSame(board.getClass(), game.getBoard().getClass());
    }

    @DisplayName("Player wins the game")
    @ParameterizedTest
    @CsvSource(value = {"CROSS,NOUGHT", "NOUGHT,CROSS"})
    void isUpdateGameStateCrossPlayerWon(Seed seedWinner, Seed loser) {
        game.initGame();
        game.setCurrentPlayer(seedWinner);
        game.updateGameState(seedWinner, 1, 1);
        game.updateGameState(loser, 1, 2);
        game.updateGameState(seedWinner, 2, 2);
        game.updateGameState(loser, 1, 3);
        game.updateGameState(seedWinner, 3, 3);
        assertEquals(seedWinner, game.getCurrentPlayer());
    }

    @DisplayName("Cross and Naught players DRAW")
    @Test
    void isUpdateGameStateWhenDraw() {
        game.initGame();
        game.updateGameState(Seed.CROSS, 1, 1);
        game.updateGameState(Seed.NOUGHT, 1, 2);
        game.updateGameState(Seed.CROSS, 1, 3);
        game.updateGameState(Seed.CROSS, 2, 1);
        game.updateGameState(Seed.NOUGHT, 2, 2);
        game.updateGameState(Seed.CROSS, 2, 3);
        game.updateGameState(Seed.NOUGHT, 3, 1);
        game.updateGameState(Seed.CROSS, 3, 2);
        game.updateGameState(Seed.NOUGHT, 3, 3);
        assertTrue(game.getCurrentState().equals(GameState.DRAW));
    }

    @Disabled
    @DisplayName("Throw CellOutOfRangeException when Player enters wrong row")
    @Test
    void isUpdateGameStateThrowCellOutOfRangeExceptionWhenWrongRow() {
        game.initGame();
        assertThrows(CellOutOfRangeException.class, () -> game.updateGameState(Seed.CROSS, 5, 1));
    }


    @ParameterizedTest
    @CsvSource(value = {"CROSS,1,1", "NOUGHT, 1,2", "CROSS,2,3", "NOUGHT,3,3"})
    void isUpdateGameStateUpdateUpdateProperCells(Seed seed, int row, int column) {
        game.initGame();
        game.updateGameState(seed, row, column);
        Cell cell = new Cell(row, column);
        cell.setContent(seed);
        assertEquals(cell.getContent(), game.getBoard().getCells()[row - 1][column - 1].getContent());
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

    @Test
    void isSetCurrentPlayerThrowsWrongPlayerExceptionWhenSettingSeedEmpty() {
        assertThrows(WrongPlayerException.class, () -> game.setCurrentPlayer(Seed.EMPTY));
    }

}