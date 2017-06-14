package com.codecool.xvso.model;

/**
 * Created by pgurdek on 12.06.17.
 */
public class Game {
    Board board;
    GameState currentState;
    Seed currentPlayer;

    public Game() {

    }

    public Board getBoard() {
        return board;
    }

    public void initGame(){

    }

    public void updateGameState(Seed seed, int row, int column){

    }

    public Seed choseRandomFirstSeed() {
        return currentPlayer;
    }

    public Seed getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(Seed currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

}
