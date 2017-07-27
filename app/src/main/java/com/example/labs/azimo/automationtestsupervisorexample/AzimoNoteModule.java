package com.example.labs.azimo.automationtestsupervisorexample;

import android.content.Context;

import com.example.labs.azimo.automationtestsupervisorexample.ui.component.AddNoteActivitySubComponent;
import com.example.labs.azimo.automationtestsupervisorexample.ui.component.DispatcherActivitySubComponent;
import com.example.labs.azimo.automationtestsupervisorexample.ui.component.LoginActivitySubComponent;
import com.example.labs.azimo.automationtestsupervisorexample.ui.component.NotesActivitySubComponent;
import com.example.labs.azimo.automationtestsupervisorexample.ui.component.RegisterActivitySubComponent;
import com.example.labs.azimo.automationtestsupervisorexample.ui.component.WelcomeActivitySubComponent;

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
    Context provideContext(AzimoNoteApplication application) {
        return application.getApplicationContext();
    }
}