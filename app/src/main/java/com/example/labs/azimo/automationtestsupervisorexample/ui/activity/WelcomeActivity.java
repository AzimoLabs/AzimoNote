package com.example.labs.azimo.automationtestsupervisorexample.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.labs.azimo.automationtestsupervisorexample.R;
import com.example.labs.azimo.automationtestsupervisorexample.ui.presenter.WelcomeActivityPresenter;
import com.example.labs.azimo.automationtestsupervisorexample.utils.Navigator;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class WelcomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnLogin;
    private Button btnRegister;

    private WelcomeActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        setupToolbar();
        setupPresenter();
        setupViews();
    }

    private void setupToolbar() {
        if (toolbar != null) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.textColorPrimary));
            toolbar.setTitle(R.string.welcome_screen_title);
            setSupportActionBar(toolbar);
        }
    }

    private void setupPresenter() {
        Navigator navigator = new Navigator();
        presenter = new WelcomeActivityPresenter(this, navigator);
    }

    private void setupViews() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginClick();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterClick();
            }
        });
    }
}
