package com.example.labs.azimo.automationtestsupervisorexample.api.mock;

/**
 * Created by F1sherKK on 25/07/2017.
 */

public class CloudMockDelayGenerator {

    private static final int MIN_RANGE = 200;
    private static final int MAX_RANGE = 5000;

    public static int generateDelay() {
        return MIN_RANGE + (int) (Math.random() * ((MAX_RANGE - MIN_RANGE) + 1));
    }

}
