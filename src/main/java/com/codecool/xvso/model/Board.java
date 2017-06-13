package com.codecool.xvso.model;

/**
 * Created by pgurdek on 12.06.17.
 */
public class Board {
    Cell[][] cells;

    public Board() {
        this.cells = new Cell[][];
    }

    private void fillCells() {
            for(Integer row=0;row<CellRange.MAXIMAL.getValue()-1;row++){
                for (Integer col = 0;col<CellRange.MAXIMAL.getValue()-1;col++){
                    cells[row,col] = new Cell(row,col);
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
    }

    public Boolean hasWon(Seed nought, int i, int i1) {
        return null;
    }

    public void updateCell(Seed nought, int i, int i1) {
    }

    public boolean isDraw() {
    }
}
