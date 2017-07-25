package com.example.labs.azimo.automationtestsupervisorexample.api;

import android.content.Context;

import com.example.labs.azimo.automationtestsupervisorexample.api.mock.ClockMockHttpException;
import com.example.labs.azimo.automationtestsupervisorexample.api.mock.CloudMock;
import com.example.labs.azimo.automationtestsupervisorexample.api.mock.CloudMockResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.LoginUserResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.RegisterUserResponse;
import com.example.labs.azimo.automationtestsupervisorexample.data.model.User;
import com.google.gson.Gson;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class ApiService {

    private CloudMock cloudMock;
    private Gson gson;

    public ApiService(Context context, Gson gson) {
        this.cloudMock = new CloudMock(context, gson);
        this.gson = new Gson();
    }

    public Observable<LoginUserResponse> loginUser(final String email, final String password) {
        return cloudMock.loginUser(email, password).flatMap(new Func1<CloudMockResponse, Observable<LoginUserResponse>>() {
            @Override
            public Observable<LoginUserResponse> call(CloudMockResponse response) {
                if (response.success()) {
                    LoginUserResponse loginUserResponse = new LoginUserResponse();
                    loginUserResponse.setSuccess(response.success());
                    loginUserResponse.setMessage(response.message);
                    if (response.body != null && !response.body.isEmpty()) {
                        User user = gson.fromJson(response.body, User.class);
                        loginUserResponse.setUser(user);
                    }
                    return Observable.just(loginUserResponse);
                } else {
                    return Observable.error(new ClockMockHttpException(response.message, response.code));
                }
            }
        });
    }

    public Observable<RegisterUserResponse> registerUser(final String email, final String password) {
        return cloudMock.registerUser(email, password).flatMap(new Func1<CloudMockResponse, Observable<RegisterUserResponse>>() {
            @Override
            public Observable<RegisterUserResponse> call(CloudMockResponse response) {
                if (response.success()) {
                    RegisterUserResponse registerUserResponse = new RegisterUserResponse();
                    registerUserResponse.setSuccess(response.success());
                    registerUserResponse.setMessage(response.message);
                    if (response.body != null && !response.body.isEmpty()) {
                        User user = gson.fromJson(response.body, User.class);
                        registerUserResponse.setUser(user);
                    }
                    return Observable.just(registerUserResponse);
                } else {
                    return Observable.error(new ClockMockHttpException(response.message, response.code));
                }
            }
        });
    }
}
