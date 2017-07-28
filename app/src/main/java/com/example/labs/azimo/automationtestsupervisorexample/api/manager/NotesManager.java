package com.example.labs.azimo.automationtestsupervisorexample.api.manager;

import com.example.labs.azimo.automationtestsupervisorexample.api.ApiService;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.AddNoteResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.FetchNotesResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.RemoveNoteResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.UpdateNoteResponse;
import com.example.labs.azimo.automationtestsupervisorexample.data.model.Note;
import com.example.labs.azimo.automationtestsupervisorexample.data.model.User;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;


/**
 * Created by F1sherKK on 26/07/2017.
 */

public class NotesManager {

    private ApiService apiService;

    public NotesManager(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<List<Note>> fetchNotes(User user) {
        return apiService.fetchNotes(user.getUniqueId()).flatMap(new Func1<FetchNotesResponse, Observable<List<Note>>>() {
            @Override
            public Observable<List<Note>> call(FetchNotesResponse fetchNotesResponse) {
                return Observable.just(fetchNotesResponse.getNoteList());
            }
        });
    }

    public Observable<Note> addNote(User user, Note note) {
        return apiService.addNote(user.getUniqueId(), note.getMessage(), note.getCreationDate())
                .flatMap(new Func1<AddNoteResponse, Observable<Note>>() {
                    @Override
                    public Observable<Note> call(AddNoteResponse addNoteResponse) {
                        return Observable.just(addNoteResponse.getNote());
                    }
                });
    }

    public Observable<Boolean> removeNote(User user, Note note) {
        return apiService.removeNote(user.getUniqueId(), note.getUniqueId())
                .flatMap(new Func1<RemoveNoteResponse, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(RemoveNoteResponse removeNoteResponse) {
                        return Observable.just(removeNoteResponse.isSuccess());
                    }
                });
    }

    public Observable<Note> updateNote(User user, Note note) {
        return apiService.updateNote(user.getUniqueId(), note.getUniqueId(), note.getMessage(), note.getCreationDate())
                .flatMap(new Func1<UpdateNoteResponse, Observable<Note>>() {
                    @Override
                    public Observable<Note> call(UpdateNoteResponse updateNoteResponse) {
                        return Observable.just(updateNoteResponse.getNote());
                    }
                });
    }
}
