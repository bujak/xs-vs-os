package com.codecool.xvso.model;

/**
 * Created by pgurdek on 12.06.17.
 */
public class Board {
    Cell[][] cells;

    public Board() {
        this.cells = new Cell[3][3];
    }

    private void fillCells() {
        for (Integer row = 0; row < CellRange.MAXIMAL.getValue(); row++) {
            for (Integer col = 0; col < CellRange.MAXIMAL.getValue(); col++) {
                cells[row][col] = new Cell(row + 1, col + 1);
            }
        }
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public Integer getMoveCounter() {
        return null;
    }

    public void setMoveCounter(int i) {
    }

    public void init() {
        fillCells();
    }

    public Boolean hasWon(Seed nought, int i, int i1) {
        return null;
    }

    public void updateCell(Seed nought, int i, int i1) {
    }

    public boolean isDraw() {
        return false;
    }
}
