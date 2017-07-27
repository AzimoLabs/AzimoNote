package com.example.labs.azimo.automationtestsupervisorexample.ui.presenter;

import com.example.labs.azimo.automationtestsupervisorexample.api.manager.ApiErrorManager;
import com.example.labs.azimo.automationtestsupervisorexample.api.manager.NotesManager;
import com.example.labs.azimo.automationtestsupervisorexample.api.utils.ErrorTrackingApiObserver;
import com.example.labs.azimo.automationtestsupervisorexample.data.model.Note;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.NotesActivity;
import com.example.labs.azimo.automationtestsupervisorexample.utils.Navigator;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class NotesActivityPresenter {

    private NotesActivity view;

    private Navigator navigator;
    private NotesManager notesManager;
    private ApiErrorManager apiErrorManager;

    private Subscription fetchNotesSubscription;

    @Inject
    public NotesActivityPresenter(NotesActivity view, Navigator navigator,
                                  NotesManager notesManager, ApiErrorManager apiErrorManager) {
        this.view = view;
        this.navigator = navigator;
        this.notesManager = notesManager;
        this.apiErrorManager = apiErrorManager;
    }

    public void onFabAddNoteButtonClick() {
        navigator.navigateToAddNoteScreenFrom(view);
    }

    public void refreshNoteList() {
        if (!view.isLoaderVisible()) {
            view.showLoader();
        }
        if (fetchNotesSubscription == null || fetchNotesSubscription.isUnsubscribed()) {

            fetchNotesSubscription = notesManager.fetchNotes()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ErrorTrackingApiObserver<List<Note>>(apiErrorManager) {
                        @Override
                        public void onNext(List<Note> notes) {
                            super.onNext(notes);
                            view.updateNoteList(notes);
                            view.hideLoader();
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.hideLoader();
                            super.onError(e);
                        }
                    });
        }
    }

    public void onDestroy() {
        if (fetchNotesSubscription != null && fetchNotesSubscription.isUnsubscribed()) {
            fetchNotesSubscription.unsubscribe();
        }
    }
}
