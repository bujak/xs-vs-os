package com.codecool.xvso.app.model;

/**
 * Created by pgurdek on 12.06.17.
 */
public class Cell {

    private Seed content;
    private Integer row;
    private Integer column;

    public Cell(Integer row, Integer column) {
        if (!isRowAndColValid(new int[]{row, column})) {
            throw new IllegalArgumentException();
        }
        ;
        this.row = row;
        this.column = column;
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

    public Integer getCol() {
        return column;
    }

    public void clear() {
        this.setContent(Seed.EMPTY);
    }
}
