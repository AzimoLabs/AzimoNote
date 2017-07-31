package com.example.labs.azimo.note.ui.component;

import com.example.labs.azimo.note.ActivityScope;
import com.example.labs.azimo.note.ui.activity.DispatcherActivity;
import com.example.labs.azimo.note.ui.module.DispatcherActivityModule;

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