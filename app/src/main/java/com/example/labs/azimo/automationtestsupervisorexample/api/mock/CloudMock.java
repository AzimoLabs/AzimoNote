package com.example.labs.azimo.automationtestsupervisorexample.api.mock;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.labs.azimo.automationtestsupervisorexample.data.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class CloudMock {

    private static final String REPOSITORY_KEY = "CloudMockKey";
    private static final String CLOUD_USERS_KEY = "CloudUsersKey";

    private SharedPreferences cloudPreferences;
    private Gson gson;

    public CloudMock(Context context, Gson gson) {
        this.cloudPreferences = context.getSharedPreferences(REPOSITORY_KEY, Context.MODE_PRIVATE);
        this.gson = gson;
    }

    public Observable<CloudMockResponse> registerUser(final String email, final String password) {
        return Observable
                .fromCallable(new Callable<CloudMockResponse>() {
                    @Override
                    public CloudMockResponse call() throws Exception {
                        List<User> users = getUsers();

                        for (User registeredUser : users) {
                            if (registeredUser.getEmail().equals(email)) {
                                return new CloudMockResponse(
                                        CloudMockResponseCodes.RESPONSE_CODE_402, null);
                            }
                        }

                        User newUser = new User();
                        newUser.setUniqueId(UUID.randomUUID().toString());
                        newUser.setEmail(email);
                        newUser.setPassword(password);

                        users.add(newUser);
                        sendUserListToCloud(users);

                        return new CloudMockResponse(
                                CloudMockResponseCodes.RESPONSE_CODE_200, gson.toJson(newUser));
                    }
                })
                .delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    public Observable<CloudMockResponse> loginUser(final String email, final String password) {
        return Observable
                .fromCallable(new Callable<CloudMockResponse>() {
                    @Override
                    public CloudMockResponse call() throws Exception {
                        List<User> users = getUsers();

                        for (User registeredUser : users) {
                            if (registeredUser.getEmail().equals(email)
                                    && registeredUser.getPassword().equals(password)) {
                                return new CloudMockResponse(
                                        CloudMockResponseCodes.RESPONSE_CODE_200, gson.toJson(registeredUser));
                            }
                        }

                        return new CloudMockResponse(
                                CloudMockResponseCodes.RESPONSE_CODE_401, null);
                    }
                })
                .delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    private void sendUserListToCloud(List<User> userList) {
        String jsonString = gson.toJson(userList);
        SharedPreferences.Editor editor = cloudPreferences.edit();
        editor.putString(CLOUD_USERS_KEY, jsonString).apply();
    }

    private List<User> getUsers() {
        String jsonString = cloudPreferences.getString(CLOUD_USERS_KEY, "");
        List<User> list = new ArrayList<>();
        if (!jsonString.isEmpty()) {
            list = gson.fromJson(jsonString, new TypeToken<List<User>>() {
            }.getType());
        }
        return list;
    }
}
