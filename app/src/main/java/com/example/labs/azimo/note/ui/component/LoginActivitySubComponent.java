package com.example.labs.azimo.note.ui.component;

import com.example.labs.azimo.note.ActivityScope;
import com.example.labs.azimo.note.ui.activity.LoginActivity;
import com.example.labs.azimo.note.ui.module.LoginActivityModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@ActivityScope
@Subcomponent(modules = {LoginActivityModule.class})
public interface LoginActivitySubComponent extends AndroidInjector<LoginActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LoginActivity> {
    }
}