package com.codecool.xvso.app;

import com.codecool.xvso.app.UI.UserInterface;
import com.codecool.xvso.app.controller.AIvsPlayerController;
import com.codecool.xvso.app.controller.PvPController;
import com.codecool.xvso.app.model.Board;
import com.codecool.xvso.app.model.Game;

/**
 * com.codecool
 * Created by bujak on 14.06.17.
 */
public class App {


    public void run() {
        UserInterface userInterface = new UserInterface();

        userInterface.showMenu();
        String gameMode = userInterface.getInput();

        switch (gameMode) {
            case ("1"):
                PvPController pvPController = new PvPController(new Game(new Board()));
                pvPController.startGame();
                break;

            case ("2"):
                AIvsPlayerController aIvsPlayerController  = new  AIvsPlayerController(new Game(new Board()));
                aIvsPlayerController.startGame();
                break;
            default:
                break;
        }

    }
}

