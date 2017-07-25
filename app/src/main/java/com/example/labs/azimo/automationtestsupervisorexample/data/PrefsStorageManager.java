package com.example.labs.azimo.automationtestsupervisorexample.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class PrefsStorageManager {

    private Context context;

    public PrefsStorageManager(Context context) {
        this.context = context;
    }

    public SharedPreferences getSharedPreferencesForKey(String key) {
        return context.getSharedPreferences(key, Context.MODE_PRIVATE);
    }
}
