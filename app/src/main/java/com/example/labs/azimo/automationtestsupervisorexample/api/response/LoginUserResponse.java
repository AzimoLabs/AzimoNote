package com.example.labs.azimo.automationtestsupervisorexample.api.response;

import com.example.labs.azimo.automationtestsupervisorexample.data.model.User;

/**
 * Created by F1sherKK on 25/07/2017.
 */

public class LoginUserResponse {

    private boolean success;
    private int code;
    private User user;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
