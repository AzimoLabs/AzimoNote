package com.example.labs.azimo.note.ui.presenter;

import com.example.labs.azimo.note.api.manager.ApiErrorManager;
import com.example.labs.azimo.note.api.manager.NotesManager;
import com.example.labs.azimo.note.api.manager.UserManager;
import com.example.labs.azimo.note.api.utils.ErrorTrackingApiObserver;
import com.example.labs.azimo.note.data.model.Note;
import com.example.labs.azimo.note.data.model.User;
import com.example.labs.azimo.note.ui.activity.AddNoteActivity;
import com.example.labs.azimo.note.utils.Navigator;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class AddNoteActivityPresenter {

    private AddNoteActivity view;

    private Navigator navigator;
    private ApiErrorManager apiErrorManager;
    private UserManager userManager;
    private NotesManager notesManager;

    private Subscription addNoteSubscription;

    @Inject
    public AddNoteActivityPresenter(AddNoteActivity view, Navigator navigator,
                                    ApiErrorManager apiErrorManager, UserManager userManager,
                                    NotesManager notesManager) {
        this.view = view;
        this.navigator = navigator;
        this.apiErrorManager = apiErrorManager;
        this.userManager = userManager;
        this.notesManager = notesManager;
    }

    public void onSaveClick(String message) {
        if (addNoteSubscription == null || addNoteSubscription.isUnsubscribed()) {
            view.showLoadingDialog();

            User user = userManager.getCurrentUser();
            Note newNote = createNote(message);
            addNoteSubscription = notesManager.addNote(user, newNote)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ErrorTrackingApiObserver<Note>(apiErrorManager) {
                        @Override
                        public void onNext(Note note) {
                            super.onNext(note);
                            view.hideLoadingDialog();
                            navigator.navigateToNotesScreenFrom(view);
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.hideLoadingDialog();
                            super.onError(e);
                        }
                    });
        }
    }

    private Note createNote(String message) {
        Note note = new Note();
        note.setCreationDate(System.currentTimeMillis());
        note.setMessage(message);
        return note;
    }

    public void onDestroy() {
        if (addNoteSubscription != null && addNoteSubscription.isUnsubscribed()) {
            addNoteSubscription.unsubscribe();
        }
    }
}
