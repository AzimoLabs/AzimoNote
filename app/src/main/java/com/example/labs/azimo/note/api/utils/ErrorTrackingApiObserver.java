package com.example.labs.azimo.note.api.utils;

import android.support.annotation.Nullable;

import com.example.labs.azimo.note.api.manager.ApiErrorManager;

import rx.Observer;

/**
 * Created by F1sherKK on 25/07/2017.
 */
public class ErrorTrackingApiObserver<T> implements Observer<T> {
    @Nullable
    private ApiErrorManager apiErrorManager;

    public ErrorTrackingApiObserver() {
        this(null);
    }

    public ErrorTrackingApiObserver(@Nullable ApiErrorManager apiErrorManager) {
        this.apiErrorManager = apiErrorManager;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        if (apiErrorManager != null) {
            apiErrorManager.handleError(e);
        }
    }
}
