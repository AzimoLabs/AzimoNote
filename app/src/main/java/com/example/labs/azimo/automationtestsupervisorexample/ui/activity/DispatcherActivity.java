package com.example.labs.azimo.automationtestsupervisorexample.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.base.BaseActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.presenter.DispatcherActivityPresenter;

import javax.inject.Inject;

/**
 * Created by F1sherKK on 25/07/2017.
 */

public class DispatcherActivity extends BaseActivity {

    @Inject DispatcherActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
    }
}
