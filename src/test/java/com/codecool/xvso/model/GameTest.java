package com.codecool.xvso.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

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



}