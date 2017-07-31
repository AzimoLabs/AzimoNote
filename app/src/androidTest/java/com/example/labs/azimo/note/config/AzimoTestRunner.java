package com.example.labs.azimo.note.config;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * Created by F1sherKK on 24/10/15.
 */
public class AzimoTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, AzimoTestApplication.class.getName(), context);
    }
}
