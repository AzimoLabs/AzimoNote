package com.example.labs.azimo.automationtestsupervisorexample.ui.component;

import com.example.labs.azimo.automationtestsupervisorexample.ActivityScope;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.LoginActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.WelcomeActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.module.LoginActivityModule;
import com.example.labs.azimo.automationtestsupervisorexample.ui.module.WelcomeActivityModule;

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