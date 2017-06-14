package com.codecool.xvso.app.model;

/**
 * Created by pgurdek on 12.06.17.
 */
public enum Seed {
    EMPTY(" "),
    CROSS("X"),
    NOUGHT("O");

    String seedValue;

    Seed(String value) {
        this.seedValue = value;
    }

    public String getValue() {
        return this.seedValue;
    }

}
