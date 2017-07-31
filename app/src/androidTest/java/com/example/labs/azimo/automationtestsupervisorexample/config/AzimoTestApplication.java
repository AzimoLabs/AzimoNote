package com.example.labs.azimo.automationtestsupervisorexample.config;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;

import com.example.labs.azimo.automationtestsupervisorexample.AzimoNoteApplication;

/**
 * Created by F1sherKK on 28/07/2017.
 */

public class AzimoTestApplication extends AzimoNoteApplication implements Application.ActivityLifecycleCallbacks {

    private Activity currentActivity;

    public static AzimoTestApplication getInstance() {
        return ((AzimoTestApplication) (InstrumentationRegistry.getTargetContext().getApplicationContext()));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }
}
