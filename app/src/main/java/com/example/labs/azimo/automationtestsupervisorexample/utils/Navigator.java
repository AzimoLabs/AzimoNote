package com.example.labs.azimo.automationtestsupervisorexample.utils;

import android.content.Context;
import android.content.Intent;

import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.LoginActivity;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class Navigator {

    public void navigateToLoginScreenFrom(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void navigateToRegisterScreenFrom(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
