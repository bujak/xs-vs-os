package com.codecool.xcso.exception;

/**
 * Created by pgurdek on 13.06.17.
 */
public class CellAlreadyHasContentException extends RuntimeException{
    public CellAlreadyHasContentException(String message) {
        super(message);
    }
}
