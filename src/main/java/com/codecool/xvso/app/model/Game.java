package com.codecool.xvso.app.model;

import com.codecool.xvso.app.exception.CellAlreadyHasContentException;
import com.codecool.xvso.app.exception.CellOutOfRangeException;
import com.codecool.xvso.app.exception.WrongPlayerException;

import java.util.Random;

/**
 * Created by pgurdek on 12.06.17.
 */
public class Game {
    private Board board;
    private GameState currentState;
    private Seed currentPlayer;

    public Game(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public Seed getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Seed currentPlayer) throws WrongPlayerException {
        if (currentPlayer.equals(Seed.EMPTY)) {
            throw new WrongPlayerException();
        }
        this.currentPlayer = currentPlayer;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    private void setWon(Seed seed) {
        if (seed.equals(Seed.CROSS)) {
            this.setCurrentState(GameState.CROSS_WON);
        } else {
            this.setCurrentState(GameState.NOUGHT_WON);
        }
    }

    public void initGame() {
        board.init();
        setCurrentPlayer(choseRandomFirstSeed());
        setCurrentState(GameState.PLAYING);


    }

    public void updateGameState(Seed seed, int row, int column) {
        try {
            this.board.updateCell(seed, row, column);
            if (this.board.hasWon(seed, row, column)) {
                this.setWon(seed);
                return;
            }
            if (this.board.isDraw(seed, row, column)) {
                this.setCurrentState(GameState.DRAW);
                return;
            }
            switchCurrentPlayer();

        } catch (CellOutOfRangeException | CellAlreadyHasContentException e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }


    }

    private Seed choseRandomFirstSeed() {
        Seed[] player = {Seed.NOUGHT, Seed.CROSS};

        Random random = new Random();
        int randomIndex = random.nextInt(player.length);
        return player[randomIndex];
    }

    private void switchCurrentPlayer() {
        if (getCurrentPlayer().equals(Seed.NOUGHT)) {
            setCurrentPlayer(Seed.CROSS);
        } else {
            setCurrentPlayer(Seed.NOUGHT);
        }
    }
}
