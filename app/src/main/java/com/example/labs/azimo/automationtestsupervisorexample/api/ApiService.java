package com.example.labs.azimo.automationtestsupervisorexample.api;

import com.example.labs.azimo.automationtestsupervisorexample.api.mock.ClockMockHttpException;
import com.example.labs.azimo.automationtestsupervisorexample.api.mock.CloudMock;
import com.example.labs.azimo.automationtestsupervisorexample.api.mock.CloudMockResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.mock.CloudNote;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.AddNoteResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.FetchNotesResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.LoginUserResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.RegisterUserResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.RemoveNoteResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.UpdateNoteResponse;
import com.example.labs.azimo.automationtestsupervisorexample.data.model.Note;
import com.example.labs.azimo.automationtestsupervisorexample.data.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class ApiService {

    private CloudMock cloudMock;
    private Gson gson;

    public ApiService(CloudMock cloudMock, Gson gson) {
        this.cloudMock = cloudMock;
        this.gson = gson;
    }

    public Observable<LoginUserResponse> loginUser(final String email, final String password) {
        return cloudMock.loginUser(email, password).flatMap(new Func1<CloudMockResponse, Observable<LoginUserResponse>>() {
            @Override
            public Observable<LoginUserResponse> call(CloudMockResponse response) {
                if (response.success()) {
                    LoginUserResponse loginUserResponse = new LoginUserResponse();
                    loginUserResponse.setSuccess(response.success());
                    loginUserResponse.setMessage(response.message);
                    loginUserResponse.setCode(response.code);

                    if (response.body != null && !response.body.isEmpty()) {
                        User user = gson.fromJson(response.body, User.class);
                        loginUserResponse.setUser(user);
                    }

                    return Observable.just(loginUserResponse);
                } else {
                    return Observable.error(new ClockMockHttpException(response.message, response.code));
                }
            }
        });
    }

    public Observable<RegisterUserResponse> registerUser(final String email, final String password) {
        return cloudMock.registerUser(email, password).flatMap(new Func1<CloudMockResponse, Observable<RegisterUserResponse>>() {
            @Override
            public Observable<RegisterUserResponse> call(CloudMockResponse response) {
                if (response.success()) {
                    RegisterUserResponse registerUserResponse = new RegisterUserResponse();
                    registerUserResponse.setSuccess(response.success());
                    registerUserResponse.setMessage(response.message);
                    registerUserResponse.setCode(response.code);

                    if (response.body != null && !response.body.isEmpty()) {
                        User user = gson.fromJson(response.body, User.class);
                        registerUserResponse.setUser(user);
                    }

                    return Observable.just(registerUserResponse);
                } else {
                    return Observable.error(new ClockMockHttpException(response.message, response.code));
                }
            }
        });
    }

    public Observable<FetchNotesResponse> fetchNotes() {
        return cloudMock.fetchNotes().flatMap(new Func1<CloudMockResponse, Observable<FetchNotesResponse>>() {
            @Override
            public Observable<FetchNotesResponse> call(CloudMockResponse response) {
                if (response.success()) {
                    FetchNotesResponse fetchNotesResponse = new FetchNotesResponse();
                    fetchNotesResponse.setSuccess(response.success());
                    fetchNotesResponse.setMessage(response.message);
                    fetchNotesResponse.setCode(response.code);
                    fetchNotesResponse.setNoteList(new ArrayList<Note>());

                    if (response.body != null && !response.body.isEmpty()) {
                        List<CloudNote> cloudNotes = gson.fromJson(response.body,
                                new TypeToken<List<CloudNote>>() {
                                }.getType());
                        List<Note> notes = new ArrayList<>();

                        for (CloudNote note : cloudNotes) {
                            Note incomingNote = new Note();
                            incomingNote.setUniqueId(note.getUniqueId());
                            incomingNote.setMessage(note.getMessage());
                            incomingNote.setCreationDate(note.getCreationDate());
                            incomingNote.setStatus(note.getStatus());
                            notes.add(incomingNote);
                        }
                        fetchNotesResponse.setNoteList(notes);
                    }
                    return Observable.just(fetchNotesResponse);
                } else {
                    return Observable.error(new ClockMockHttpException(response.message, response.code));
                }
            }
        });
    }

    public Observable<AddNoteResponse> addNote(final String message, final long creationDate, final int status) {
        return cloudMock.addNote(message, creationDate, status).flatMap(new Func1<CloudMockResponse, Observable<AddNoteResponse>>() {
            @Override
            public Observable<AddNoteResponse> call(CloudMockResponse response) {
                if (response.success()) {
                    AddNoteResponse addNoteResponse = new AddNoteResponse();
                    addNoteResponse.setSuccess(response.success());
                    addNoteResponse.setMessage(response.message);
                    addNoteResponse.setCode(response.code);

                    if (response.body != null && !response.body.isEmpty()) {
                        CloudNote note = gson.fromJson(response.body, CloudNote.class);
                        Note incomingNote = new Note();
                        incomingNote.setUniqueId(note.getUniqueId());
                        incomingNote.setMessage(note.getMessage());
                        incomingNote.setCreationDate(note.getCreationDate());
                        incomingNote.setStatus(note.getStatus());
                        addNoteResponse.setNote(incomingNote);
                    }
                    return Observable.just(addNoteResponse);
                } else {
                    return Observable.error(new ClockMockHttpException(response.message, response.code));
                }
            }
        });
    }

    public Observable<RemoveNoteResponse> removeNote(final String uniqueId) {
        return cloudMock.removeNote(uniqueId).flatMap(new Func1<CloudMockResponse, Observable<RemoveNoteResponse>>() {
            @Override
            public Observable<RemoveNoteResponse> call(CloudMockResponse response) {
                if (response.success()) {
                    RemoveNoteResponse removeNoteResponse = new RemoveNoteResponse();
                    removeNoteResponse.setSuccess(response.success());
                    removeNoteResponse.setMessage(response.message);
                    removeNoteResponse.setCode(response.code);
                    return Observable.just(removeNoteResponse);
                } else {
                    return Observable.error(new ClockMockHttpException(response.message, response.code));
                }
            }
        });
    }

    public Observable<UpdateNoteResponse> updateNote(final String uniqueId, final String message,
                                                     final long creationDate, final int status) {
        return cloudMock.updateNote(uniqueId, message, creationDate, status).flatMap(new Func1<CloudMockResponse, Observable<UpdateNoteResponse>>() {
            @Override
            public Observable<UpdateNoteResponse> call(CloudMockResponse response) {
                if (response.success()) {
                    UpdateNoteResponse updateNoteResponse = new UpdateNoteResponse();
                    updateNoteResponse.setSuccess(response.success());
                    updateNoteResponse.setMessage(response.message);
                    updateNoteResponse.setCode(response.code);

                    if (response.body != null && !response.body.isEmpty()) {
                        CloudNote note = gson.fromJson(response.body, CloudNote.class);
                        Note incomingNote = new Note();
                        incomingNote.setUniqueId(note.getUniqueId());
                        incomingNote.setMessage(note.getMessage());
                        incomingNote.setCreationDate(note.getCreationDate());
                        incomingNote.setStatus(note.getStatus());
                        updateNoteResponse.setNote(incomingNote);
                    }
                    return Observable.just(updateNoteResponse);
                } else {
                    return Observable.error(new ClockMockHttpException(response.message, response.code));
                }
            }
        });
    }
}
