package com.example.labs.azimo.note.ui.module;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;

import com.example.labs.azimo.note.api.manager.ApiErrorManager;
import com.example.labs.azimo.note.ui.activity.NotesActivity;
import com.example.labs.azimo.note.ui.adapter.NotesAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@Module
public class NotesActivityModule {
    @Provides
    public ApiErrorManager provideApiErrorManager(NotesActivity notesActivity) {
        return new ApiErrorManager(notesActivity);
    }

    @Provides
    public LinearLayoutManager provideLinearLayoutManager(NotesActivity notesActivity) {
        return new LinearLayoutManager(notesActivity);
    }

    @Provides
    public LayoutInflater provideLayoutInflater(NotesActivity notesActivity) {
        return LayoutInflater.from(notesActivity);
    }

    @Provides
    public NotesAdapter provideNotesAdapter(LayoutInflater layoutInflater) {
        return new NotesAdapter(layoutInflater);
    }
}