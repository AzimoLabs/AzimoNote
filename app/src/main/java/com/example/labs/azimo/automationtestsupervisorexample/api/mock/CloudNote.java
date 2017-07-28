package com.example.labs.azimo.automationtestsupervisorexample.api.mock;

/**
 * Created by F1sherKK on 26/07/2017.
 */

public class CloudNote {

    private String ownerUniqueId;
    private String uniqueId;
    private String message;
    private long creationDate;

    public String getOwnerUniqueId() {
        return ownerUniqueId;
    }

    public void setOwnerUniqueId(String ownerUniqueId) {
        this.ownerUniqueId = ownerUniqueId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "CloudNote{" +
                "ownerUniqueId='" + ownerUniqueId + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                ", message='" + message + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
