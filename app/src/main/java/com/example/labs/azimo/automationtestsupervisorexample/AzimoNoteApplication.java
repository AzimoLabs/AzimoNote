package com.example.labs.azimo.automationtestsupervisorexample;

import android.app.Activity;
import android.app.Application;

import com.example.labs.azimo.automationtestsupervisorexample.api.module.ApiModule;
import com.example.labs.azimo.automationtestsupervisorexample.data.module.DataModule;
import com.example.labs.azimo.automationtestsupervisorexample.utils.module.UtilsModule;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class AzimoNoteApplication extends Application implements HasDispatchingActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAzimoNoteComponent
                .builder()
                .application(this)
                .utilsModule(new UtilsModule())
                .dataModule(new DataModule())
                .apiModule(new ApiModule())
                .build()
                .inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}