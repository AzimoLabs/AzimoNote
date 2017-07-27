package com.example.labs.azimo.automationtestsupervisorexample.api.manager;

import com.example.labs.azimo.automationtestsupervisorexample.api.ApiService;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.AddNoteResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.FetchNotesResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.RemoveNoteResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.response.UpdateNoteResponse;
import com.example.labs.azimo.automationtestsupervisorexample.data.model.Note;

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

    public Observable<List<Note>> fetchNotes() {
        return apiService.fetchNotes().flatMap(new Func1<FetchNotesResponse, Observable<List<Note>>>() {
            @Override
            public Observable<List<Note>> call(FetchNotesResponse fetchNotesResponse) {
                return Observable.just(fetchNotesResponse.getNoteList());
            }
        });
    }

    public Observable<Note> addNote(Note note) {
        return apiService.addNote(note.getMessage(), note.getCreationDate(), note.getStatus())
                .flatMap(new Func1<AddNoteResponse, Observable<Note>>() {
                    @Override
                    public Observable<Note> call(AddNoteResponse addNoteResponse) {
                        return Observable.just(addNoteResponse.getNote());
                    }
                });
    }

    public Observable<Boolean> removeNote(Note note) {
        return apiService.removeNote(note.getUniqueId())
                .flatMap(new Func1<RemoveNoteResponse, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(RemoveNoteResponse removeNoteResponse) {
                        return Observable.just(removeNoteResponse.isSuccess());
                    }
                });
    }

    public Observable<Note> updateNote(Note note) {
        return apiService.updateNote(note.getUniqueId(), note.getMessage(), note.getCreationDate(), note.getStatus())
                .flatMap(new Func1<UpdateNoteResponse, Observable<Note>>() {
                    @Override
                    public Observable<Note> call(UpdateNoteResponse updateNoteResponse) {
                        return Observable.just(updateNoteResponse.getNote());
                    }
                });
    }
}
