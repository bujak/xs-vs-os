package com.codecool.xvso.app.exception;

/**
 * Created by pgurdek on 13.06.17.
 */
public class CellOutOfRangeException extends IllegalArgumentException {

    public CellOutOfRangeException(String message) {
        super(message);
    }
}
