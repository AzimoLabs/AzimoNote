package com.example.labs.azimo.note.api.mock;

/**
 * Created by F1sherKK on 25/07/2017.
 */

public class CloudMockResponseCodes {

    public static final int RESPONSE_CODE_200 = 200;
    public static final int RESPONSE_CODE_401 = 401;
    public static final int RESPONSE_CODE_402 = 402;
    public static final int RESPONSE_CODE_403 = 403;
    public static final int RESPONSE_CODE_404 = 404;
    public static final int RESPONSE_CODE_500 = 500;

    public static String mockMessage(int code) {
        switch (code) {
            case RESPONSE_CODE_200:
                return "Success!";
            case RESPONSE_CODE_401:
                return "User does not exist!";
            case RESPONSE_CODE_402:
                return "User already exists!";
            case RESPONSE_CODE_403:
                return "Note does not exist!";
            case RESPONSE_CODE_404:
                return "Invalid password!";
            case RESPONSE_CODE_500:
                return "Server does not respond.";
            default:
                return "Unexpected error has occurred.";
        }
    }
}
