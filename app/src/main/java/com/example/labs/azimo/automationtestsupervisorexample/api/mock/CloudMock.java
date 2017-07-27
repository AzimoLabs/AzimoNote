package com.example.labs.azimo.automationtestsupervisorexample.api.mock;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class CloudMock {

    public static final String REPOSITORY_KEY = "CloudMockKey";
    private static final String CLOUD_USERS_KEY = "CloudUsersKey";
    private static final String NOTES_KEY = "NotesKey";

    private SharedPreferences cloudPreferences;
    private Gson gson;

    public CloudMock(SharedPreferences sharedPreferences, Gson gson) {
        this.cloudPreferences = sharedPreferences;
        this.gson = gson;
    }

    public Observable<CloudMockResponse> registerUser(final String email, final String password) {
        return Observable
                .fromCallable(new Callable<CloudMockResponse>() {
                    @Override
                    public CloudMockResponse call() throws Exception {
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
                    }
                })
                .delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    public Observable<CloudMockResponse> loginUser(final String email, final String password) {
        return Observable
                .fromCallable(new Callable<CloudMockResponse>() {
                    @Override
                    public CloudMockResponse call() throws Exception {
                        List<CloudUser> users = getUsers();

                        for (CloudUser registeredUser : users) {
                            if (registeredUser.getEmail().equals(email)
                                    && registeredUser.getPassword().equals(password)) {
                                return new CloudMockResponse(
                                        CloudMockResponseCodes.RESPONSE_CODE_200,
                                        gson.toJson(registeredUser));
                            }
                        }

                        return new CloudMockResponse(
                                CloudMockResponseCodes.RESPONSE_CODE_401, null);
                    }
                })
                .delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    public Observable<CloudMockResponse> fetchNotes() {
        return Observable.fromCallable(new Callable<CloudMockResponse>() {
            @Override
            public CloudMockResponse call() throws Exception {
                String jsonString = cloudPreferences.getString(NOTES_KEY, "");
                return new CloudMockResponse(CloudMockResponseCodes.RESPONSE_CODE_200, jsonString);
            }
        }).delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    public Observable<CloudMockResponse> addNote(final String message, final long creationDate,
                                                 final int status) {
        return Observable.fromCallable(new Callable<CloudMockResponse>() {
            @Override
            public CloudMockResponse call() throws Exception {
                List<CloudNote> notes = getNotes();

                CloudNote note = new CloudNote();
                note.setUniqueId(UUID.randomUUID().toString());
                note.setMessage(message);
                note.setCreationDate(creationDate);
                note.setStatus(status);

                notes.add(note);
                updateNoteCloudList(notes);

                return new CloudMockResponse(
                        CloudMockResponseCodes.RESPONSE_CODE_200, gson.toJson(note));
            }
        }).delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    public Observable<CloudMockResponse> removeNote(final String uniqueId) {
        return Observable.fromCallable(new Callable<CloudMockResponse>() {
            @Override
            public CloudMockResponse call() throws Exception {
                List<CloudNote> notes = getNotes();

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
                    updateNoteCloudList(notesModified);
                    return new CloudMockResponse(
                            CloudMockResponseCodes.RESPONSE_CODE_200, null);
                } else {
                    return new CloudMockResponse(
                            CloudMockResponseCodes.RESPONSE_CODE_403, null);
                }
            }
        }).delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    public Observable<CloudMockResponse> updateNote(final String uniqueId, final String message,
                                                    final long creationDate, final int status) {
        return Observable.fromCallable(new Callable<CloudMockResponse>() {
            @Override
            public CloudMockResponse call() throws Exception {
                List<CloudNote> notes = getNotes();

                boolean noteFound = false;
                CloudNote modifiedNote = new CloudNote();
                for (CloudNote note : notes) {
                    if (note.getUniqueId().equals(uniqueId)) {
                        noteFound = true;
                        note.setMessage(message);
                        note.setCreationDate(creationDate);
                        note.setStatus(status);
                        modifiedNote = note;
                        break;
                    }
                }

                if (noteFound) {
                    updateNoteCloudList(notes);
                    return new CloudMockResponse(
                            CloudMockResponseCodes.RESPONSE_CODE_200, gson.toJson(modifiedNote));
                } else {
                    return new CloudMockResponse(
                            CloudMockResponseCodes.RESPONSE_CODE_403, null);
                }
            }
        }).delay(CloudMockDelayGenerator.generateDelay(), TimeUnit.MILLISECONDS);
    }

    private void updateNoteCloudList(List<CloudNote> notesList) {
        String jsonString = gson.toJson(notesList);
        SharedPreferences.Editor editor = cloudPreferences.edit();
        editor.putString(NOTES_KEY, jsonString).apply();
    }

    private List<CloudNote> getNotes() {
        String jsonString = cloudPreferences.getString(NOTES_KEY, "");
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
