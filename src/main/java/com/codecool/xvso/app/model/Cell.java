package com.codecool.xvso.app.model;

/**
 * Created by pgurdek on 12.06.17.
 */
public class Cell {

    private CellRange cellRange;
    private Seed content;
    private Integer row;
    private Integer col;

    public Cell(Integer row, Integer col) {
        if (!isRowAndColValid(new int[]{row, col})) {
            throw new IllegalArgumentException();
        }
        ;
        this.row = row;
        this.col = col;
        this.content = Seed.EMPTY;
    }

    static boolean isRowAndColValid(int[] values) {
        for (int coordinate : values) {
            if (coordinate < CellRange.MINIMAL.getValue() || coordinate > CellRange.MAXIMAL.getValue()) {
                return false;
            }
        }
        return true;
    }

    public Seed getContent() {
        return content;
    }

    public void setContent(Seed content) {
        this.content = content;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

    public void clear() {
        this.setContent(Seed.EMPTY);
    }
}
