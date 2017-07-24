package com.example.labs.azimo.automationtestsupervisorexample.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.labs.azimo.automationtestsupervisorexample.R;
import com.example.labs.azimo.automationtestsupervisorexample.ui.presenter.WelcomeActivityPresenter;
import com.example.labs.azimo.automationtestsupervisorexample.utils.Navigator;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class WelcomeActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegister;

    private WelcomeActivityPresenter welcomeActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        setupPresenter();
        setupViews();
    }

    private void setupPresenter() {
        Navigator navigator = new Navigator();
        welcomeActivityPresenter = new WelcomeActivityPresenter(this, navigator);
    }

    private void setupViews() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeActivityPresenter.onLoginClick();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeActivityPresenter.onRegisterClick();
            }
        });
    }
}
