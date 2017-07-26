package com.example.labs.azimo.automationtestsupervisorexample.ui.component;

import com.example.labs.azimo.automationtestsupervisorexample.ActivityScope;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.RegisterActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.WelcomeActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.module.RegisterActivityModule;
import com.example.labs.azimo.automationtestsupervisorexample.ui.module.WelcomeActivityModule;

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