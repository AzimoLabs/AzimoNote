package com.example.labs.azimo.automationtestsupervisorexample.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.labs.azimo.automationtestsupervisorexample.R;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.base.BaseActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.presenter.RegisterActivityPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class RegisterActivity extends BaseActivity {

    public static final String REGISTER_KEY_EMAIL = "KeyEmail";
    public static final String REGISTER_KEY_PASSWORD = "KeyPassword";

    @Inject RegisterActivityPresenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.btnRegister) Button btnRegister;
    @BindView(R.id.btnLogin) TextView btnLogin;
    @BindView(R.id.etPassword) TextInputEditText etPassword;
    @BindView(R.id.etEmail) TextInputEditText etEmail;
    @BindView(R.id.tilPassword) TextInputLayout tilPassword;
    @BindView(R.id.tilEmail) TextInputLayout tilEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        setupToolbar();
        setupViews();

        presenter.init(getIntent());
    }

    private void setupToolbar() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.textColorPrimary));
        toolbar.setTitle(R.string.register_screen_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupViews() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                presenter.onLoginClick(email, password);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                presenter.onRegisterClick(email, password);
            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tilEmail.setError("");
                tilEmail.setErrorEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tilPassword.setError("");
                tilPassword.setErrorEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void setEmail(String email) {
        etEmail.setText(email);
    }

    public void setPassword(String password) {
        etPassword.setText(password);
    }

    public void setEmailError(String errorMessage) {
        tilEmail.setError(errorMessage);
    }

    public void setPasswordError(String errorMessage) {
        tilPassword.setError(errorMessage);
    }
}
