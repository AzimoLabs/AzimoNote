package com.example.labs.azimo.automationtestsupervisorexample.data;

import android.content.SharedPreferences;

import com.example.labs.azimo.automationtestsupervisorexample.data.model.User;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class UserDataStore {

    public static final String REPOSITORY_KEY = "UserDataStorePreferences";

    private static final String USER_UNIQUE_KEY = "UserUniqueIdKey";
    private static final String USER_EMAIL_KEY = "UserEmailKey";
    private static final String USER_PASSWORD_KEY = "UserPasswordKey";

    private SharedPreferences preferences;

    public UserDataStore(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public User getUser() {
        User user = null;
        if (isUserLoggedIn()) {
            String uniqueId = preferences.getString(USER_UNIQUE_KEY, "");
            String email = preferences.getString(USER_EMAIL_KEY, "");
            String password = preferences.getString(USER_PASSWORD_KEY, "");

            user = new User();
            user.setUniqueId(uniqueId);
            user.setEmail(email);
            user.setPassword(password);
        }
        return user;
    }

    public void saveUser(User user) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_UNIQUE_KEY, user.getUniqueId());
        editor.putString(USER_EMAIL_KEY, user.getEmail());
        editor.putString(USER_PASSWORD_KEY, user.getPassword());
        editor.apply();
    }

    public void removeUser() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(USER_UNIQUE_KEY);
        editor.remove(USER_EMAIL_KEY);
        editor.remove(USER_PASSWORD_KEY);
        editor.apply();
    }

    public boolean isUserLoggedIn() {
        return preferences.contains(USER_UNIQUE_KEY)
                && preferences.contains(USER_EMAIL_KEY)
                && preferences.contains(USER_PASSWORD_KEY);
    }
}
