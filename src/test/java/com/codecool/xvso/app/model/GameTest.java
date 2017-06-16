package com.codecool.xvso.app.model;

import com.codecool.xvso.app.exception.WrongPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * com.codecool.xvso.app.model
 * Created by bujak on 14.06.17.
 */
class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(new Board());
        game.initGame();
    }

    @Test
    void isInitGameInitializedBoard() {
        Board board = new Board();
        assertSame(board.getClass(), game.getBoard().getClass());
    }

    @DisplayName("updateGameState don't changes current player after win configuration")
    @ParameterizedTest
    @CsvSource(value = {"CROSS,NOUGHT", "NOUGHT,CROSS"})
    void isUpdateGameStateDontChangeCurrentPlayerWhenWinConfiguration(Seed winner, Seed loser) {
        game.setCurrentPlayer(winner);
        game.updateGameState(winner, 1, 1);
        game.updateGameState(loser, 1, 2);
        game.updateGameState(winner, 2, 2);
        game.updateGameState(loser, 1, 3);
        game.updateGameState(winner, 3, 3);
        assertEquals(winner, game.getCurrentPlayer());
    }

    @DisplayName("UpdateGameState set draw when draw configuration")
    @Test
    void isUpdateGameStateSetDrawWhenDrawConfiguration() {
        //inserting cells for draw configuration
        int[][] crossCoordinates = {{1, 1}, {1, 3}, {2, 1}, {2, 3}, {3, 2}};
        int[][] noughtCoordinates = {{1, 2}, {2, 2}, {3, 1}, {3, 3}};
        for (int[] pair : crossCoordinates) {
            game.updateGameState(Seed.CROSS, pair[0], pair[1]);
        }
        for (int[] pair : noughtCoordinates) {
            game.updateGameState(Seed.NOUGHT, pair[0], pair[1]);
        }
        assertTrue(game.getCurrentState().equals(GameState.DRAW));
    }

    @DisplayName("UpdateGameState update proper cells")
    @ParameterizedTest
    @CsvSource(value = {"CROSS,1,1", "NOUGHT, 1,2", "CROSS,2,3", "NOUGHT,3,3"})
    void isUpdateGameStateUpdateUpdateProperCells(Seed seed, int row, int column) {
        int arrayModifier = CellRange.ARRAYMODIFIER.getValue();
        game.updateGameState(seed, row, column);
        Cell cell = mock(Cell.class);
        when(cell.getContent()).thenReturn(seed);
        assertEquals(
                cell.getContent(),
                game.getBoard().getCells()[row - arrayModifier][column - arrayModifier].getContent()
        );
    }

    @DisplayName("Game init sets random current player")
    @RepeatedTest(10)
    void isCurrentPlayerChosenAfterGameInit() {
        assertTrue(game.getCurrentPlayer().equals(Seed.CROSS) || game.getCurrentPlayer().equals(Seed.NOUGHT));
    }

    @DisplayName("Game init don't sets empty seed as current player")
    @RepeatedTest(10)
    void isChoseRandomFirstPlayerDoNotSetSeedEmpty() {
        assertFalse(game.getCurrentPlayer().equals(Seed.EMPTY));
    }

    @DisplayName("setCurrentPlayer throws exception when trying to set empty seed as current player")
    @Test
    void isSetCurrentPlayerThrowsWrongPlayerExceptionWhenSettingSeedEmpty() {
        assertThrows(WrongPlayerException.class, () -> game.setCurrentPlayer(Seed.EMPTY));
    }
}