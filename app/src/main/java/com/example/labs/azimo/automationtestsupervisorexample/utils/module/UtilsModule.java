package com.example.labs.azimo.automationtestsupervisorexample.utils.module;

import com.example.labs.azimo.automationtestsupervisorexample.utils.Navigator;
import com.example.labs.azimo.automationtestsupervisorexample.utils.validator.EmailValidator;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@Module
public class UtilsModule {

    @Provides
    @Singleton
    public Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    public EmailValidator provideEmailValidator() {
        return new EmailValidator();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }
}
