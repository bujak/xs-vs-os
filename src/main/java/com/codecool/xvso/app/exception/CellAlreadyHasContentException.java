package com.codecool.xvso.app.exception;

/**
 * Created by pgurdek on 13.06.17.
 */
public class CellAlreadyHasContentException extends RuntimeException {
    public CellAlreadyHasContentException(String message) {
        super(message);
    }
}
