package com.example.labs.azimo.note.ui.component;

import com.example.labs.azimo.note.ActivityScope;
import com.example.labs.azimo.note.ui.activity.AddNoteActivity;
import com.example.labs.azimo.note.ui.module.AddNoteActivityModule;

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