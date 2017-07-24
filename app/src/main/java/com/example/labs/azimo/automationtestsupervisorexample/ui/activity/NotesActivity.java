package com.example.labs.azimo.automationtestsupervisorexample.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.labs.azimo.automationtestsupervisorexample.R;
import com.example.labs.azimo.automationtestsupervisorexample.ui.presenter.NotesActivityPresenter;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class NotesActivity extends AppCompatActivity {

    private NotesActivityPresenter notesActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        notesActivityPresenter = new NotesActivityPresenter();
    }
}
