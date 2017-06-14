package com.codecool;

import com.codecool.UI.UserInterface;
import com.codecool.controller.PvPController;
import com.codecool.xvso.model.Board;
import com.codecool.xvso.model.Game;

/**
 * com.codecool
 * Created by bujak on 14.06.17.
 */
public class App {


    public void run() {
        UserInterface userInterface = new UserInterface();

        userInterface.showMenu();
        String gameMode = userInterface.getInput();

        if (gameMode.equals("1")) {

            PvPController pvPController = new PvPController(new Game(new Board()));
            pvPController.startGame();
        }
    }
}
