package com.example.labs.azimo.automationtestsupervisorexample.ui.component;

import com.example.labs.azimo.automationtestsupervisorexample.ActivityScope;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.NotesActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.module.NotesActivityModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@ActivityScope
@Subcomponent(modules = {NotesActivityModule.class})
public interface NotesActivitySubComponent extends AndroidInjector<NotesActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<NotesActivity> {
    }
}