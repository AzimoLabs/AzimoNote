package com.example.labs.azimo.note.data.model;

/**
 * Created by F1sherKK on 26/07/2017.
 */

public class Note {

    private String uniqueId;
    private long creationDate;
    private String message;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "Note{" +
                "uniqueId='" + uniqueId + '\'' +
                ", creationDate=" + creationDate +
                ", message='" + message + '\'' +
                '}';
    }
}
