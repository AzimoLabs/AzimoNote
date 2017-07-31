package com.example.labs.azimo.note.ui.presenter;

import com.example.labs.azimo.note.api.manager.UserManager;
import com.example.labs.azimo.note.ui.activity.DispatcherActivity;
import com.example.labs.azimo.note.utils.Navigator;

import javax.inject.Inject;

/**
 * Created by F1sherKK on 25/07/2017.
 */

public class DispatcherActivityPresenter {

    private DispatcherActivity view;

    private UserManager userManager;
    private Navigator navigator;

    @Inject
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
