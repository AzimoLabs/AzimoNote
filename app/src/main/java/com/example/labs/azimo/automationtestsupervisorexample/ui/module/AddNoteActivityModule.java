package com.example.labs.azimo.automationtestsupervisorexample.ui.module;

import com.example.labs.azimo.automationtestsupervisorexample.api.manager.ApiErrorManager;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.AddNoteActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@Module
public class AddNoteActivityModule {
    @Provides
    public ApiErrorManager provideApiErrorManager(AddNoteActivity addNoteActivity) {
        return new ApiErrorManager(addNoteActivity);
    }
}