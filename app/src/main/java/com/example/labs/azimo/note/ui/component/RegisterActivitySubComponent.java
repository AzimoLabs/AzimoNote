package com.example.labs.azimo.note.ui.component;

import com.example.labs.azimo.note.ActivityScope;
import com.example.labs.azimo.note.ui.activity.RegisterActivity;
import com.example.labs.azimo.note.ui.module.RegisterActivityModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@ActivityScope
@Subcomponent(modules = {RegisterActivityModule.class})
public interface RegisterActivitySubComponent extends AndroidInjector<RegisterActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RegisterActivity> {
    }
}