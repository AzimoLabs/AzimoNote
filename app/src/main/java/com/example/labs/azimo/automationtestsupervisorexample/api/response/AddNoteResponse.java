package com.example.labs.azimo.automationtestsupervisorexample.api.response;

import com.example.labs.azimo.automationtestsupervisorexample.data.model.Note;

/**
 * Created by F1sherKK on 27/07/2017.
 */

public class AddNoteResponse {

    private boolean success;
    private int code;
    private Note note;
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

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AddNoteResponse{" +
                "success=" + success +
                ", code=" + code +
                ", note=" + note +
                ", message='" + message + '\'' +
                '}';
    }
}
