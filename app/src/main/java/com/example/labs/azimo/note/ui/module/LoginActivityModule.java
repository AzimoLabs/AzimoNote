package com.example.labs.azimo.note.ui.module;

import com.example.labs.azimo.note.api.manager.ApiErrorManager;
import com.example.labs.azimo.note.ui.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@Module
public class LoginActivityModule {
    @Provides
    public ApiErrorManager provideApiErrorManager(LoginActivity loginActivity) {
        return new ApiErrorManager(loginActivity);
    }
}