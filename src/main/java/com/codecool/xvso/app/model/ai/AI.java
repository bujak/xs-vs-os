package com.codecool.xvso.app.model.ai;

import com.codecool.xvso.app.model.CellRange;
import com.codecool.xvso.app.model.Game;
import com.codecool.xvso.app.model.Seed;
import sun.nio.cs.ext.SimpleEUCEncoder;

import java.util.Random;

/**
 * com.codecool.xvso.app.model
 * Created by bujak on 14.06.17.
 */
public class AI {

    Game game;
    Seed seed;

    public AI(Game game) {
        this.game = game;
        seed = Seed.NOUGHT;
    }

    private int randomCoordGenerator() {
        Random random = new Random();
        return random.nextInt(CellRange.MAXIMAL.getValue() - CellRange.MINIMAL.getValue() + 1) + CellRange.MINIMAL.getValue();

    }

    public void makeMove(Seed seed) {
        int randomRow = randomCoordGenerator();
        int randomColumn = randomCoordGenerator();
        boolean notMoved = true;
        while (notMoved) {
            if (game.getBoard().getCells()[randomRow-1][randomColumn-1].getContent().equals(Seed.EMPTY)) {
                game.updateGameState(seed, randomRow, randomColumn);
                notMoved = false;
            }
            randomRow = randomCoordGenerator();
            randomColumn = randomCoordGenerator();
        }
    }

}

