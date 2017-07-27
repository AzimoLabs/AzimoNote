package com.example.labs.azimo.automationtestsupervisorexample.ui.adapter.model;

import com.example.labs.azimo.automationtestsupervisorexample.data.model.Note;

/**
 * Created by F1sherKK on 27/07/2017.
 */

public class NoteAdapterViewModel {

    public static final int TYPE_NOTE = 0;

    private Note note;
    private int type;

    public NoteAdapterViewModel(Note note, int type) {
        this.note = note;
        this.type = type;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NoteAdapterViewModel{" +
                "note=" + note +
                ", type=" + type +
                '}';
    }
}
