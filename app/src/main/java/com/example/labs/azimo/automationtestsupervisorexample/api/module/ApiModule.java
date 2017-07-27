package com.example.labs.azimo.automationtestsupervisorexample.api.module;

import android.content.SharedPreferences;

import com.example.labs.azimo.automationtestsupervisorexample.api.ApiService;
import com.example.labs.azimo.automationtestsupervisorexample.api.manager.NotesManager;
import com.example.labs.azimo.automationtestsupervisorexample.api.manager.UserManager;
import com.example.labs.azimo.automationtestsupervisorexample.api.mock.CloudMock;
import com.example.labs.azimo.automationtestsupervisorexample.data.PrefsStorageManager;
import com.example.labs.azimo.automationtestsupervisorexample.data.UserDataStore;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@Module
public class ApiModule {
    @Provides
    @Singleton
    public UserManager provideUserManager(ApiService apiService, UserDataStore userDataStore) {
        return new UserManager(apiService, userDataStore);
    }

    @Provides
    @Singleton
    public NotesManager provideNotesManager(ApiService apiService) {
        return new NotesManager(apiService);
    }

    @Provides
    @Singleton
    public CloudMock provideCloudMock(PrefsStorageManager prefsStorageManager, Gson gson) {
        SharedPreferences cloudPreferences =
                prefsStorageManager.getSharedPreferencesForKey(CloudMock.REPOSITORY_KEY);
        return new CloudMock(cloudPreferences, gson);
    }

    @Provides
    @Singleton
    public ApiService provideApiService(CloudMock cloudMock, Gson gson) {
        return new ApiService(cloudMock, gson);
    }

}
