package com.example.labs.azimo.automationtestsupervisorexample.ui.presenter;

import com.example.labs.azimo.automationtestsupervisorexample.api.manager.UserManager;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.DispatcherActivity;
import com.example.labs.azimo.automationtestsupervisorexample.utils.Navigator;

/**
 * Created by F1sherKK on 25/07/2017.
 */

public class DispatcherActivityPresenter {

    private DispatcherActivity view;

    private UserManager userManager;
    private Navigator navigator;

    public DispatcherActivityPresenter(DispatcherActivity view, UserManager userManager,
                                       Navigator navigator) {
        this.view = view;
        this.userManager = userManager;
        this.navigator = navigator;
    }

    public void start() {
        if (userManager.isUserLoggedIn()) {
            navigator.navigateToNotesScreenFrom(view);
            return;
        }
        navigator.navigateToWelcomeScreenFrom(view);
    }
}
