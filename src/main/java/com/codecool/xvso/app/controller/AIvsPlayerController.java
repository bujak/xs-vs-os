package com.codecool.xvso.app.controller;

import com.codecool.xvso.app.UI.UserInterface;
import com.codecool.xvso.app.model.ai.AI;
import com.codecool.xvso.app.model.Game;
import com.codecool.xvso.app.model.GameState;
import com.codecool.xvso.app.model.Seed;

/**
 * com.codecool.xvso.app.controller
 * Created by bujak on 14.06.17.
 */
public class AIvsPlayerController {
    private AI ai;
    private Game game;
    private UserInterface userInterface = new UserInterface();
    private Seed currentPlayer;

    public AIvsPlayerController(Game game) {
        this.game = game;
        this.ai = new AI(game);
    }

    public void startGame() {
        game.initGame();
        do {
            currentPlayer = game.getCurrentPlayer();
            userInterface.showGameBoard(game.getBoard().getCells());
            userInterface.showPlayerTurn(currentPlayer);
            Integer row = userInterface.getIntegerInput("Enter row: ");
            Integer column = userInterface.getIntegerInput("Enter Column: ");
            game.updateGameState(currentPlayer, row, column);
            ai.makeMove(currentPlayer);
        } while (game.getCurrentState().equals(GameState.PLAYING));

        if (game.getCurrentState().equals(GameState.DRAW)) {
            userInterface.draw();
        } else {
            userInterface.showWinner(currentPlayer);
        }
    }
}
