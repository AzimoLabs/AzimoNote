package com.example.labs.azimo.automationtestsupervisorexample.api.response;

import com.example.labs.azimo.automationtestsupervisorexample.data.model.Note;

import java.util.List;

/**
 * Created by F1sherKK on 25/07/2017.
 */

public class FetchNotesResponse {

    private boolean success;
    private int code;
    private List<Note> noteList;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FetchNotesResponse{" +
                "success=" + success +
                ", code=" + code +
                ", noteList=" + noteList +
                ", message='" + message + '\'' +
                '}';
    }
}
