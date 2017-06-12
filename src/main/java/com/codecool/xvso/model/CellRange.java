package com.codecool.xvso.model;

/**
 * com.codecool.xvso.model
 * Created by bujak on 12.06.17.
 */
public enum CellRange {

    MINIMAL(1),
    MAXIMAL(3);

    private final int value;

    CellRange(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
