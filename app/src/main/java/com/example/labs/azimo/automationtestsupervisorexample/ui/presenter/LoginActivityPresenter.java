package com.example.labs.azimo.automationtestsupervisorexample.ui.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.example.labs.azimo.automationtestsupervisorexample.R;
import com.example.labs.azimo.automationtestsupervisorexample.api.errors.ApiErrorManager;
import com.example.labs.azimo.automationtestsupervisorexample.api.manager.UserManager;
import com.example.labs.azimo.automationtestsupervisorexample.api.utils.ErrorTrackingApiObserver;
import com.example.labs.azimo.automationtestsupervisorexample.data.model.User;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.LoginActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.RegisterActivity;
import com.example.labs.azimo.automationtestsupervisorexample.utils.Navigator;
import com.example.labs.azimo.automationtestsupervisorexample.utils.validator.EmailValidator;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class LoginActivityPresenter {

    private LoginActivity view;

    private Navigator navigator;
    private UserManager userManager;
    private EmailValidator emailValidator;
    private ApiErrorManager apiErrorManager;

    private Subscription loginUserSubscription;

    public LoginActivityPresenter(LoginActivity view, Navigator navigator,
                                  EmailValidator emailValidator, UserManager userManager,
                                  ApiErrorManager apiErrorManager) {
        this.view = view;
        this.navigator = navigator;
        this.emailValidator = emailValidator;
        this.userManager = userManager;
        this.apiErrorManager = apiErrorManager;
    }

    public void init(Intent intent) {
        if (intent.getExtras() != null) {
            if (intent.getExtras().containsKey(RegisterActivity.REGISTER_KEY_EMAIL)
                    && intent.getExtras().containsKey(RegisterActivity.REGISTER_KEY_PASSWORD)) {
                String email = intent.getStringExtra(RegisterActivity.REGISTER_KEY_EMAIL);
                String password = intent.getStringExtra(RegisterActivity.REGISTER_KEY_PASSWORD);
                view.setEmail(email);
                view.setPassword(password);
            }
        }
    }

    public void onRegisterClick(String email, String password) {
        Bundle bundle = new Bundle();
        bundle.putString(LoginActivity.LOGIN_KEY_EMAIL, email);
        bundle.putString(LoginActivity.LOGIN_KEY_PASSWORD, password);
        navigator.navigateToRegisterScreenFrom(view, bundle);
    }

    public void onLoginClick(String email, String password) {
        if (inputHasErrors(email, password)) return;

        view.showLoadingDialog();
        if (loginUserSubscription == null || loginUserSubscription.isUnsubscribed()) {
            loginUserSubscription = userManager.loginUser(email, password)
                    .subscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ErrorTrackingApiObserver<User>(apiErrorManager) {
                        @Override
                        public void onNext(User user) {
                            view.hideLoadingDialog();
                            super.onNext(user);
                            navigator.navigateToNotesScreenFrom(view);
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.hideLoadingDialog();
                            super.onError(e);
                        }
                    });
        }
    }

    private boolean inputHasErrors(String email, String password) {
        boolean wasError = false;
        if (!emailValidator.isEmailCorrect(email)) {
            view.setEmailError(view.getString(R.string.login_error_incorrect_email));
            wasError = true;
        }

        if (email.isEmpty()) {
            view.setEmailError(view.getString(R.string.login_error_empty_email));
            wasError = true;
        }

        if (password.isEmpty()) {
            view.setPasswordError(view.getString(R.string.login_error_empty_password));
            wasError = true;
        }

        return wasError;
    }

    public void onDestroy() {
        if (loginUserSubscription != null && loginUserSubscription.isUnsubscribed()) {
            loginUserSubscription.unsubscribe();
        }
    }
}
