package com.example.labs.azimo.automationtestsupervisorexample.api.manager;

import com.example.labs.azimo.automationtestsupervisorexample.api.ApiService;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.LoginUserResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.RegisterUserResponse;
import com.example.labs.azimo.automationtestsupervisorexample.data.UserDataStore;
import com.example.labs.azimo.automationtestsupervisorexample.data.model.User;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class UserManager {

    private ApiService apiService;
    private UserDataStore userDataStore;

    public UserManager(ApiService apiService, UserDataStore userDataStore) {
        this.apiService = apiService;
        this.userDataStore = userDataStore;
    }

    public boolean isUserLoggedIn() {
        return userDataStore.isUserLoggedIn();
    }

    public void logoutCurrentUser() {
        userDataStore.logoutCurrentUser();
    }

    public User getCurrentUser() {
        return userDataStore.getUser();
    }

    public Observable<User> loginUser(String email, String password) {
        return apiService.loginUser(email, password).flatMap(new Func1<LoginUserResponse, Observable<User>>() {
            @Override
            public Observable<User> call(LoginUserResponse loginUserResponse) {
                User apiUser = loginUserResponse.getUser();
                userDataStore.saveUser(apiUser);
                return Observable.just(apiUser);
            }
        });
    }

    public Observable<User> registerUser(String email, String password) {
        return apiService.registerUser(email, password).flatMap(new Func1<RegisterUserResponse, Observable<User>>() {
            @Override
            public Observable<User> call(RegisterUserResponse registerUserResponse) {
                User apiUser = registerUserResponse.getUser();
                userDataStore.saveUser(apiUser);
                return Observable.just(apiUser);
            }
        });
    }
}
