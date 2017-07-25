package com.example.labs.azimo.automationtestsupervisorexample.api.mock;

/**
 * Created by F1sherKK on 25/07/2017.
 */

public class ClockMockHttpException extends Exception {
    private int errorCode;

    public ClockMockHttpException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
