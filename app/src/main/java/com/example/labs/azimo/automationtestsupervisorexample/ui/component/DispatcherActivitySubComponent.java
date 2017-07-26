package com.example.labs.azimo.automationtestsupervisorexample.ui.component;

import com.example.labs.azimo.automationtestsupervisorexample.ActivityScope;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.DispatcherActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.module.DispatcherActivityModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@ActivityScope
@Subcomponent(modules = {DispatcherActivityModule.class})
public interface DispatcherActivitySubComponent extends AndroidInjector<DispatcherActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<DispatcherActivity> {
    }
}