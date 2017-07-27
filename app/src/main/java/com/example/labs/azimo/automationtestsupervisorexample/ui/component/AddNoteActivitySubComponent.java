package com.example.labs.azimo.automationtestsupervisorexample.ui.component;

import com.example.labs.azimo.automationtestsupervisorexample.ActivityScope;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.AddNoteActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.NotesActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.module.AddNoteActivityModule;
import com.example.labs.azimo.automationtestsupervisorexample.ui.module.NotesActivityModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@ActivityScope
@Subcomponent(modules = {AddNoteActivityModule.class})
public interface AddNoteActivitySubComponent extends AndroidInjector<AddNoteActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<AddNoteActivity> {
    }
}