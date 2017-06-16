package com.codecool.xvso.app.model.ai;

import com.codecool.xvso.app.model.CellRange;
import com.codecool.xvso.app.model.Game;
import com.codecool.xvso.app.model.Seed;

import java.util.Random;

/**
 * com.codecool.xvso.app.model
 * Created by bujak on 14.06.17.
 */
public class AI {

    private Game game;
    private Seed seed;

    public AI(Game game, Seed seed) {
        this.game = game;
        setAIPlayer(seed);
    }

    public Seed getSeed() {
        return seed;
    }

    private void setAIPlayer(Seed seed) {
        if (seed.equals(Seed.CROSS)) {
            this.seed = Seed.NOUGHT;
        } else {
            this.seed = Seed.CROSS;
        }
    }

    private int randomCoordGenerator() {
        Random random = new Random();
        return random.nextInt(CellRange.MAXIMAL.getValue()) + CellRange.MINIMAL.getValue();
    }

    public void makeMove() {
        int randomRow = randomCoordGenerator();
        int randomColumn = randomCoordGenerator();
        boolean notMoved = true;
        while (notMoved) {
            if (game.getBoard().getCells()
                    [randomRow - CellRange.ARRAYMODIFIER.getValue()]
                    [randomColumn - CellRange.ARRAYMODIFIER.getValue()]
                    .getContent().equals(Seed.EMPTY)) {
                game.updateGameState(this.seed, randomRow, randomColumn);
                notMoved = false;
            }
            randomRow = randomCoordGenerator();
            randomColumn = randomCoordGenerator();
        }
    }
}

