package com.codecool.xvso.model;

/**
 * Created by pgurdek on 12.06.17.
 */
public class Cell {

    private Seed content;
    private Integer row;
    private Integer col;

    public Cell(Integer row, Integer col) {
        this.row = row;
        this.col = col;
    }


}
