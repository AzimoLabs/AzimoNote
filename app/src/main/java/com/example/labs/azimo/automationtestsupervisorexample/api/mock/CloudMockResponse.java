package com.example.labs.azimo.automationtestsupervisorexample.api.mock;

import com.example.labs.azimo.automationtestsupervisorexample.api.response.LoginUserResponse;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class CloudMockResponse extends LoginUserResponse {
    public int code;
    public String body;
    public String message;

    public CloudMockResponse(int code, String body) {
        this.code = code;
        this.body = body;
        this.message = CloudMockResponseCodes.mockMessage(code);
    }

    public boolean success() {
        return this.code == CloudMockResponseCodes.RESPONSE_CODE_200;
    }

    @Override
    public String toString() {
        return "CloudMockResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
