package com.example.labs.azimo.automationtestsupervisorexample.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.labs.azimo.automationtestsupervisorexample.api.ApiService;
import com.example.labs.azimo.automationtestsupervisorexample.api.manager.UserManager;
import com.example.labs.azimo.automationtestsupervisorexample.data.PrefsStorageManager;
import com.example.labs.azimo.automationtestsupervisorexample.data.UserDataStore;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.base.BaseActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.presenter.DispatcherActivityPresenter;
import com.example.labs.azimo.automationtestsupervisorexample.utils.Navigator;
import com.google.gson.Gson;

/**
 * Created by F1sherKK on 25/07/2017.
 */

public class DispatcherActivity extends BaseActivity {

    private DispatcherActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupPresenter();
        presenter.start();
    }

    private void setupPresenter() {
        PrefsStorageManager prefsStorageManager = new PrefsStorageManager(this);
        SharedPreferences userDataStorePrefs =
                prefsStorageManager.getSharedPreferencesForKey(UserDataStore.REPOSITORY_KEY);
        UserDataStore userDataStore = new UserDataStore(userDataStorePrefs);
        Gson gson = new Gson();
        ApiService apiService = new ApiService(this, gson);
        UserManager userManager = new UserManager(apiService, userDataStore);
        Navigator navigator = new Navigator();
        presenter = new DispatcherActivityPresenter(this, userManager, navigator);
    }
}
