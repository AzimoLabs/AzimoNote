package com.example.labs.azimo.automationtestsupervisorexample.api.mock;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class CloudMock {

    public static final String REPOSITORY_KEY = "CloudMockKey";
    private static final String CLOUD_USERS_KEY = "CloudUsersKey";

    private SharedPreferences cloudPreferences;
    private Gson gson;

    public CloudMock(SharedPreferences sharedPreferences, Gson gson) {
        this.cloudPreferences = sharedPreferences;
        this.gson = gson;
    }

    public Observable<CloudMockResponse> registerUser(final String email, final String password) {
        return Observable
                .fromCallable(() -> {
                    List<CloudUser> users = getUsers();

                    for (CloudUser registeredUser : users) {
                        if (registeredUser.getEmail().equals(email)) {
                            return new CloudMockResponse(
                                    CloudMockResponseCodes.RESPONSE_CODE_402, null);
                        }
                    }

                    CloudUser newUser = new CloudUser();
                    newUser.setUniqueId(UUID.randomUUID().toString());
                    newUser.setEmail(email);
                    newUser.setPassword(password);

                    users.add(newUser);
                    updateUserCloudList(users);

                    return new CloudMockResponse(
                            CloudMockResponseCodes.RESPONSE_CODE_200, gson.toJson(newUser));
                })
                .delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    public Observable<CloudMockResponse> loginUser(final String email, final String password) {
        return Observable
                .fromCallable(() -> {
                    List<CloudUser> users = getUsers();

                    for (CloudUser registeredUser : users) {
                        if (registeredUser.getEmail().equals(email)) {
                            if (registeredUser.getPassword().equals(password)) {
                                return new CloudMockResponse(
                                        CloudMockResponseCodes.RESPONSE_CODE_200,
                                        gson.toJson(registeredUser));
                            } else {
                                return new CloudMockResponse(
                                        CloudMockResponseCodes.RESPONSE_CODE_404,
                                        null);
                            }
                        }
                    }

                    return new CloudMockResponse(
                            CloudMockResponseCodes.RESPONSE_CODE_401, null);
                })
                .delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    public Observable<CloudMockResponse> fetchNotes(final String userUniqueId) {
        return Observable.fromCallable(() -> {
            if (!cloudPreferences.contains(userUniqueId)) {
                return new CloudMockResponse(
                        CloudMockResponseCodes.RESPONSE_CODE_401,
                        null);
            }

            String jsonString = cloudPreferences.getString(userUniqueId, "");
            return new CloudMockResponse(CloudMockResponseCodes.RESPONSE_CODE_200, jsonString);
        }).delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    public Observable<CloudMockResponse> addNote(final String userUniqueId, final String message,
                                                 final long creationDate) {
        return Observable.fromCallable(() -> {
            if (!cloudPreferences.contains(userUniqueId)) {
                List<CloudNote> notesList = new ArrayList<>();
                updateNoteCloudList(userUniqueId, notesList);
            }

            List<CloudNote> notes = getNotes(userUniqueId);

            CloudNote note = new CloudNote();
            note.setUniqueId(UUID.randomUUID().toString());
            note.setMessage(message);
            note.setCreationDate(creationDate);

            notes.add(note);
            updateNoteCloudList(userUniqueId, notes);

            return new CloudMockResponse(
                    CloudMockResponseCodes.RESPONSE_CODE_200, gson.toJson(note));
        }).delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    public Observable<CloudMockResponse> removeNote(final String userUniqueId, final String uniqueId) {
        return Observable.fromCallable(() -> {
            if (!cloudPreferences.contains(userUniqueId)) {
                return new CloudMockResponse(
                        CloudMockResponseCodes.RESPONSE_CODE_401,
                        null);
            }

            List<CloudNote> notes = getNotes(userUniqueId);

            boolean noteFound = false;
            List<CloudNote> notesModified = new ArrayList<>();
            for (CloudNote note : notes) {
                if (note.getUniqueId().equals(uniqueId)) {
                    noteFound = true;
                    continue;
                }
                notesModified.add(note);
            }

            if (noteFound) {
                updateNoteCloudList(userUniqueId, notesModified);
                return new CloudMockResponse(
                        CloudMockResponseCodes.RESPONSE_CODE_200, null);
            } else {
                return new CloudMockResponse(
                        CloudMockResponseCodes.RESPONSE_CODE_403, null);
            }
        }).delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    public Observable<CloudMockResponse> updateNote( final String userUniqueId, final String uniqueId,
                                                    final String message, final long creationDate) {
        return Observable.fromCallable(() -> {
            if (!cloudPreferences.contains(userUniqueId)) {
                return new CloudMockResponse(
                        CloudMockResponseCodes.RESPONSE_CODE_401,
                        null);
            }

            List<CloudNote> notes = getNotes(userUniqueId);

            boolean noteFound = false;
            CloudNote modifiedNote = new CloudNote();
            for (CloudNote note : notes) {
                if (note.getUniqueId().equals(uniqueId)) {
                    noteFound = true;
                    note.setMessage(message);
                    note.setCreationDate(creationDate);
                    modifiedNote = note;
                    break;
                }
            }

            if (noteFound) {
                updateNoteCloudList(userUniqueId, notes);
                return new CloudMockResponse(
                        CloudMockResponseCodes.RESPONSE_CODE_200, gson.toJson(modifiedNote));
            } else {
                return new CloudMockResponse(
                        CloudMockResponseCodes.RESPONSE_CODE_403, null);
            }
        }).delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    private void updateNoteCloudList(String userUniqueId, List<CloudNote> notesList) {
        String jsonString = gson.toJson(notesList);
        SharedPreferences.Editor editor = cloudPreferences.edit();
        editor.putString(userUniqueId, jsonString).apply();
    }

    private List<CloudNote> getNotes(final String userUniqueId) {
        String jsonString = cloudPreferences.getString(userUniqueId, "");
        List<CloudNote> list = new ArrayList<>();
        if (!jsonString.isEmpty()) {
            list = gson.fromJson(jsonString, new TypeToken<List<CloudNote>>() {
            }.getType());
        }
        return list;
    }

    private void updateUserCloudList(List<CloudUser> userList) {
        String jsonString = gson.toJson(userList);
        SharedPreferences.Editor editor = cloudPreferences.edit();
        editor.putString(CLOUD_USERS_KEY, jsonString).apply();
    }

    private List<CloudUser> getUsers() {
        String jsonString = cloudPreferences.getString(CLOUD_USERS_KEY, "");
        List<CloudUser> list = new ArrayList<>();
        if (!jsonString.isEmpty()) {
            list = gson.fromJson(jsonString, new TypeToken<List<CloudUser>>() {
            }.getType());
        }
        return list;
    }
}
