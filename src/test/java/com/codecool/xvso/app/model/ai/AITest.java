package com.codecool.xvso.app.model.ai;

import com.codecool.xvso.app.model.Board;
import com.codecool.xvso.app.model.Game;
import com.codecool.xvso.app.model.Seed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * com.codecool.xvso.app.model.ai
 * Created by bujak on 16.06.17.
 */
class AITest {

    private AI ai;
    @Mock
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(new Board());
        game.initGame();
        ai = new AI(game, game.getCurrentPlayer());
    }

    @ParameterizedTest
    @CsvSource(value = {"CROSS, NOUGHT", "NOUGHT, CROSS"})
    void isAIConstructorSetsProperSeed(Seed humanSeed, Seed aiSeed) {
        ai = new AI(game,humanSeed);
        assertEquals(aiSeed, ai.getSeed());
    }

}