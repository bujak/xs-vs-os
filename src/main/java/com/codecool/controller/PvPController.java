package com.codecool.controller;

import com.codecool.UI.UserInterface;
import com.codecool.xvso.model.Game;
import com.codecool.xvso.model.GameState;
import com.codecool.xvso.model.Seed;

/**
 * Created by pgurdek on 14.06.17.
 */
public class PvPController {

    Game game;
    UserInterface userInterface = new UserInterface();
    Seed currentPlayer;

    public PvPController(Game game) {
        this.game = game;
    }

    public void startGame() {
        game.initGame();
        do {
            currentPlayer = game.getCurrentPlayer();
            userInterface.showGameBoard(game.getBoard().getCells());
            userInterface.showPlayerTurn(currentPlayer);
            Integer row = userInterface.getIntegerInput("Enter row: ");
            Integer column = userInterface.getIntegerInput("Eneter Column: ");
            game.updateGameState(currentPlayer, row, column);

        } while (game.getCurrentState().equals(GameState.PLAYING));

        if (game.getCurrentState().equals(GameState.DRAW)) {
            userInterface.draw();
        } else {
            userInterface.showWinner(currentPlayer);
        }
    }

}
