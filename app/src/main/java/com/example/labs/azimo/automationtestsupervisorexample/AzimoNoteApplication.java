package com.example.labs.azimo.automationtestsupervisorexample;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.example.labs.azimo.automationtestsupervisorexample.api.module.ApiModule;
import com.example.labs.azimo.automationtestsupervisorexample.data.module.DataModule;
import com.example.labs.azimo.automationtestsupervisorexample.utils.module.UtilsModule;

import javax.inject.Inject;

import dagger.Component;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class AzimoNoteApplication extends Application implements HasDispatchingActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private AzimoNoteComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAzimoNoteComponent
                .builder()
                .application(this)
                .utilsModule(new UtilsModule())
                .dataModule(new DataModule())
                .apiModule(new ApiModule())
                .build();

        component.inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @VisibleForTesting
    public AzimoNoteComponent getComponent() {
        return component;
    }

    @VisibleForTesting
    public void setComponent(AzimoNoteComponent component) {
        this.component = component;
    }
}