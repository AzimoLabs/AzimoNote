package com.example.labs.azimo.note.api.response;

import com.example.labs.azimo.note.data.model.Note;

/**
 * Created by F1sherKK on 25/07/2017.
 */

public class UpdateNoteResponse {

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
        return "UpdateNoteResponse{" +
                "success=" + success +
                ", code=" + code +
                ", note=" + note +
                ", message='" + message + '\'' +
                '}';
    }
}
