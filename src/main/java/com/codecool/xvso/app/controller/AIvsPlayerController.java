package com.codecool.xvso.app.controller;

import com.codecool.xvso.app.UI.UserInterface;
import com.codecool.xvso.app.model.Game;
import com.codecool.xvso.app.model.GameState;
import com.codecool.xvso.app.model.Seed;
import com.codecool.xvso.app.model.ai.AI;

/**
 * com.codecool.xvso.app.controller
 * Created by bujak on 14.06.17.
 */
public class AIvsPlayerController {

    private AI ai;
    private Game game;
    private UserInterface userInterface = new UserInterface();
    private Seed currentPlayer;
    private Seed humanPlayer;

    public AIvsPlayerController(Game game) {
        this.game = game;
    }

    private void setCurrentPlayer(Seed currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void startGame() {
        game.initGame();
        this.ai = new AI(game, game.getCurrentPlayer());
        humanPlayer = game.getCurrentPlayer();
        userInterface.showMessage("AI is brutal and mean! \n" +
                "If you type wrong cell, AI takes your turn, so be careful!");
        do {
            userInterface.showGameBoard(game.getBoard().getCells());
            userInterface.showPlayerTurn(humanPlayer);
            Integer row = userInterface.getIntegerInput("Enter row: ");
            Integer column = userInterface.getIntegerInput("Enter Column: ");
            game.updateGameState(humanPlayer, row, column);
            this.setCurrentPlayer(humanPlayer);
            if (game.getCurrentState().equals(GameState.PLAYING)) {
                ai.makeMove();
                this.setCurrentPlayer(ai.getSeed());
            }
        } while (game.getCurrentState().equals(GameState.PLAYING));

        if (game.getCurrentState().equals(GameState.DRAW)) {
            userInterface.showGameBoard(game.getBoard().getCells());

            userInterface.draw();
        } else {
            userInterface.showGameBoard(game.getBoard().getCells());
            userInterface.showWinner(currentPlayer);

        }
    }
}
