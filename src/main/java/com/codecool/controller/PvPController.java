package com.codecool.controller;

import com.codecool.UI.UserInterface;
import com.codecool.xvso.model.Game;
import com.codecool.xvso.model.GameState;
import com.codecool.xvso.model.Seed;

/**
 * Created by pgurdek on 14.06.17.
 */
public class PvPController {

    Game game = new Game();
    UserInterface userInterface = new UserInterface();
    Seed currentPlayer;
    Integer row,
            column;

    public void startGame() {
        game.initGame();
        do {
            currentPlayer = game.getCurrentPlayer();
            userInterface.showGameBoard(game.getBoard().getCells());
            userInterface.showPlayerTurn(currentPlayer);
            Integer row = Integer.valueOf(userInterface.getInput("Enter row: "));
            Integer column = Integer.valueOf(userInterface.getInput("Eneter Column: "));
            game.updateGameState(currentPlayer, row, column);

        } while (game.getCurrentState().equals(GameState.PLAYING));

        if (game.getCurrentState().equals(GameState.DRAW)){
            userInterface.draw();
        }
        else{
            userInterface.showWinner(currentPlayer);
        }
    }

}
