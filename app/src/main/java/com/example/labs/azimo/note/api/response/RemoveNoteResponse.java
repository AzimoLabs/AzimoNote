package com.example.labs.azimo.note.api.response;

/**
 * Created by F1sherKK on 27/07/2017.
 */

public class RemoveNoteResponse {

    private boolean success;
    private int code;
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
                ", message='" + message + '\'' +
                '}';
    }
}
