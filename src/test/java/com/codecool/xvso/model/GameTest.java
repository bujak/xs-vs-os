package com.codecool.xvso.model;

import com.codecool.xcso.exception.CellAlreadyHasContentException;
import com.codecool.xcso.exception.CellOutOfRangeException;
import com.codecool.xcso.exception.WrongPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        game = new Game(new Board());
    }

    @Test
    void isInitGameInitializedBoard() {
        game.initGame();
        Board board = new Board();
        board.init();
        assertSame(board.getClass(), game.getBoard().getClass());
    }

    @DisplayName("Cross player wins the game")
    @Test
    void isUpdateGameStateCrossPlayerWon() {
        game.initGame();
        game.updateGameState(Seed.CROSS, 1, 1);
        game.updateGameState(Seed.NOUGHT, 1, 2);
        game.updateGameState(Seed.CROSS, 2, 2);
        game.updateGameState(Seed.NOUGHT, 1, 3);
        game.updateGameState(Seed.CROSS, 3, 3);
        assertEquals(Seed.CROSS, game.getCurrentPlayer());
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


    @DisplayName("Throw CellOutOfRangeException when Player enters wrong row")
    @Test
    void isUpdateGameStateThrowCellOutOfRangeExceptionWhenWrongRow() {
        game.initGame();
        assertThrows(CellOutOfRangeException.class, ()-> game.updateGameState(Seed.CROSS, 5, 1));
    }
    @DisplayName("Throw CellAlreadyHasContentException when Player enters taken row and column")
    @Test
    void isUpdateGameStateCellAlreadyHasContentExceptionWhenPlayerEnterTakenRowAndColumn() {
        game.initGame();
        game.updateGameState(Seed.CROSS, 1, 1);
        game.updateGameState(Seed.NOUGHT, 1, 1);

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