package com.example.labs.azimo.note.data;

import com.example.labs.azimo.note.data.model.User;

import java.util.UUID;

/**
 * Created by F1sherKK on 31/07/2017.
 */

public class TestUsers {

    public static final String LOGIN_TEST_USER_NAME = "test@test.com";
    public static final String LOGIN_TEST_USER_PASSWORD = "test123";

    public static final String GENERATED_USER_PASSWORD = "test123";

    public static User generateNewUniqueUser() {
        User user = new User();
        user.setUniqueId(UUID.randomUUID().toString());
        user.setEmail("test" + String.valueOf(System.currentTimeMillis()) + "@test.com");
        user.setPassword(GENERATED_USER_PASSWORD);
        return user;
    }
}
