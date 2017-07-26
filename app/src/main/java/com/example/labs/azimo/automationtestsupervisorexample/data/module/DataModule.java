package com.example.labs.azimo.automationtestsupervisorexample.data.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.labs.azimo.automationtestsupervisorexample.data.PrefsStorageManager;
import com.example.labs.azimo.automationtestsupervisorexample.data.UserDataStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    public PrefsStorageManager providePrefsStorageManager(Context context) {
        return new PrefsStorageManager(context);
    }

    @Provides
    @Singleton
    public UserDataStore provideUserDataStore(PrefsStorageManager prefsStorageManager) {
        SharedPreferences userDataPrefs =
                prefsStorageManager.getSharedPreferencesForKey(UserDataStore.REPOSITORY_KEY);
        return new UserDataStore(userDataPrefs);
    }
}
