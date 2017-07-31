package com.example.labs.azimo.note.ui.presenter;

import com.example.labs.azimo.note.api.manager.ApiErrorManager;
import com.example.labs.azimo.note.api.manager.NotesManager;
import com.example.labs.azimo.note.api.manager.UserManager;
import com.example.labs.azimo.note.api.utils.ErrorTrackingApiObserver;
import com.example.labs.azimo.note.data.model.Note;
import com.example.labs.azimo.note.data.model.User;
import com.example.labs.azimo.note.ui.activity.NotesActivity;
import com.example.labs.azimo.note.utils.Navigator;

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
    private UserManager userManager;
    private NotesManager notesManager;
    private ApiErrorManager apiErrorManager;

    private Subscription fetchNotesSubscription;

    @Inject
    public NotesActivityPresenter(NotesActivity view, Navigator navigator,
                                  UserManager userManager, NotesManager notesManager,
                                  ApiErrorManager apiErrorManager) {
        this.view = view;
        this.navigator = navigator;
        this.userManager = userManager;
        this.notesManager = notesManager;
        this.apiErrorManager = apiErrorManager;
    }

    public void onFabAddNoteButtonClick() {
        navigator.navigateToAddNoteScreenFrom(view);
    }

    public void onMenuLogoutButtonClick() {
        view.showLogoutPromptDialog();
    }

    public void onLogoutConfirmed() {
        userManager.logoutCurrentUser();
        navigator.navigateToWelcomeScreenFrom(view);
    }

    public void refreshNoteList() {
        if (!view.isLoaderVisible()) {
            view.showLoader();
        }
        if (fetchNotesSubscription == null || fetchNotesSubscription.isUnsubscribed()) {
            User user = userManager.getCurrentUser();
            fetchNotesSubscription = notesManager.fetchNotes(user)
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
