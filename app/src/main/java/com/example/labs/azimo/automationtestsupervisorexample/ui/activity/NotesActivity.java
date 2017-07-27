package com.example.labs.azimo.automationtestsupervisorexample.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.labs.azimo.automationtestsupervisorexample.R;
import com.example.labs.azimo.automationtestsupervisorexample.data.model.Note;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.base.BaseActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.adapter.NotesAdapter;
import com.example.labs.azimo.automationtestsupervisorexample.ui.presenter.NotesActivityPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class NotesActivity extends BaseActivity {

    @Inject
    NotesActivityPresenter presenter;
    @Inject
    NotesAdapter notesAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.srlLoading)
    SwipeRefreshLayout srlLoading;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fabAddNote)
    FloatingActionButton fabAddNote;
    @BindView(R.id.rvNotes)
    RecyclerView rvNotes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ButterKnife.bind(this);

        setupToolbar();
        setupNotesList();
        setupViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.refreshNoteList();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.fabAddNote)
    public void onFabAddNoteClick() {
        presenter.onFabAddNoteButtonClick();
    }

    private void setupToolbar() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.textColorPrimary));
        toolbar.setTitle(R.string.notes_screen_title);
        setSupportActionBar(toolbar);
    }

    private void setupNotesList() {
        rvNotes.setLayoutManager(linearLayoutManager);
        rvNotes.setAdapter(notesAdapter);
    }

    public void setupViews() {
        srlLoading.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refreshNoteList();
            }
        });
    }

    public void updateNoteList(List<Note> noteList) {
        notesAdapter.addItems(noteList);
        notesAdapter.notifyDataSetChanged();
    }

    public boolean isLoaderVisible() {
        return srlLoading.isRefreshing();
    }

    public void showLoader() {
        srlLoading.post(new Runnable() {
            @Override
            public void run() {
                srlLoading.setRefreshing(true);
            }
        });

    }

    public void hideLoader() {
        srlLoading.post(new Runnable() {
            @Override
            public void run() {
                srlLoading.setRefreshing(false);
            }
        });
    }
}
