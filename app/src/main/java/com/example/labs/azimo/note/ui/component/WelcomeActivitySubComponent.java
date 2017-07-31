package com.example.labs.azimo.note.ui.component;

import com.example.labs.azimo.note.ActivityScope;
import com.example.labs.azimo.note.ui.activity.WelcomeActivity;
import com.example.labs.azimo.note.ui.module.WelcomeActivityModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@ActivityScope
@Subcomponent(modules = {WelcomeActivityModule.class})
public interface WelcomeActivitySubComponent extends AndroidInjector<WelcomeActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<WelcomeActivity> {
    }
}