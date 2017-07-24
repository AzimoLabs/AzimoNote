package com.example.labs.azimo.automationtestsupervisorexample.ui.presenter;

import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.WelcomeActivity;
import com.example.labs.azimo.automationtestsupervisorexample.utils.Navigator;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class WelcomeActivityPresenter {

    private WelcomeActivity view;
    private Navigator navigator;

    public WelcomeActivityPresenter(WelcomeActivity view, Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    public void onLoginClick() {
        navigator.navigateToLoginScreenFrom(view);
    }

    public void onRegisterClick() {
        navigator.navigateToRegisterScreenFrom(view);
    }
}
