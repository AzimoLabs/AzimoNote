package com.example.labs.azimo.automationtestsupervisorexample.utils.validator;

import java.util.regex.Pattern;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class EmailValidator {

    private static final String EMAIL_REGEX = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
    private Pattern pattern;

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_REGEX);
    }

    public boolean isEmailCorrect(String email) {
        return pattern.matcher(email).matches();
    }
}