package com.example.labs.azimo.automationtestsupervisorexample.ui.activity;

import android.content.SharedPreferences;
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
import com.example.labs.azimo.automationtestsupervisorexample.api.ApiService;
import com.example.labs.azimo.automationtestsupervisorexample.api.errors.ApiErrorManager;
import com.example.labs.azimo.automationtestsupervisorexample.api.manager.UserManager;
import com.example.labs.azimo.automationtestsupervisorexample.data.PrefsStorageManager;
import com.example.labs.azimo.automationtestsupervisorexample.data.UserDataStore;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.base.BaseActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.presenter.LoginActivityPresenter;
import com.example.labs.azimo.automationtestsupervisorexample.utils.Navigator;
import com.example.labs.azimo.automationtestsupervisorexample.utils.validator.EmailValidator;
import com.google.gson.Gson;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class LoginActivity extends BaseActivity {

    public static final String LOGIN_KEY_EMAIL = "KeyEmail";
    public static final String LOGIN_KEY_PASSWORD = "KeyPassword";

    private Toolbar toolbar;
    private Button btnLogin;
    private TextView btnRegister;
    private TextInputEditText etPassword;
    private TextInputEditText etEmail;
    private TextInputLayout tilPassword;
    private TextInputLayout tilEmail;

    private LoginActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (TextView) findViewById(R.id.btnRegister);
        etPassword = (TextInputEditText) findViewById(R.id.etPassword);
        etEmail = (TextInputEditText) findViewById(R.id.etEmail);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);

        setupToolbar();
        setupViews();
        setupPresenter();

        presenter.init(getIntent());
    }

    private void setupToolbar() {
        if (toolbar != null) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.textColorPrimary));
            toolbar.setTitle(R.string.login_screen_title);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupPresenter() {
        Navigator navigator = new Navigator();
        EmailValidator emailValidator = new EmailValidator();
        PrefsStorageManager prefsStorageManager = new PrefsStorageManager(this);
        SharedPreferences userDataStorePrefs =
                prefsStorageManager.getSharedPreferencesForKey(UserDataStore.REPOSITORY_KEY);
        Gson gson = new Gson();
        ApiService apiService = new ApiService(this, gson);
        UserDataStore userDataStore = new UserDataStore(userDataStorePrefs);
        ApiErrorManager apiErrorManager = new ApiErrorManager(this);
        UserManager userManager = new UserManager(apiService, userDataStore);
        presenter = new LoginActivityPresenter(
                this, navigator, emailValidator, userManager, apiErrorManager);
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
