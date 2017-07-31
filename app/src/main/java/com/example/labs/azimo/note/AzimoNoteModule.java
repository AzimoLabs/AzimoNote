package com.example.labs.azimo.note;

import android.content.Context;

import com.example.labs.azimo.note.ui.component.AddNoteActivitySubComponent;
import com.example.labs.azimo.note.ui.component.DispatcherActivitySubComponent;
import com.example.labs.azimo.note.ui.component.LoginActivitySubComponent;
import com.example.labs.azimo.note.ui.component.NotesActivitySubComponent;
import com.example.labs.azimo.note.ui.component.RegisterActivitySubComponent;
import com.example.labs.azimo.note.ui.component.WelcomeActivitySubComponent;

import dagger.Module;
import dagger.Provides;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@Module(subcomponents = {
        WelcomeActivitySubComponent.class,
        DispatcherActivitySubComponent.class,
        LoginActivitySubComponent.class,
        RegisterActivitySubComponent.class,
        NotesActivitySubComponent.class,
        AddNoteActivitySubComponent.class
})
public class AzimoNoteModule {
    @Provides
    public Context provideContext(AzimoNoteApplication application) {
        return application.getApplicationContext();
    }
}