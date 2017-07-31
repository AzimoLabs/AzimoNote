package com.example.labs.azimo.note;

import android.app.Activity;

import com.example.labs.azimo.note.ui.activity.AddNoteActivity;
import com.example.labs.azimo.note.ui.activity.DispatcherActivity;
import com.example.labs.azimo.note.ui.activity.LoginActivity;
import com.example.labs.azimo.note.ui.activity.NotesActivity;
import com.example.labs.azimo.note.ui.activity.RegisterActivity;
import com.example.labs.azimo.note.ui.activity.WelcomeActivity;
import com.example.labs.azimo.note.ui.component.AddNoteActivitySubComponent;
import com.example.labs.azimo.note.ui.component.DispatcherActivitySubComponent;
import com.example.labs.azimo.note.ui.component.LoginActivitySubComponent;
import com.example.labs.azimo.note.ui.component.NotesActivitySubComponent;
import com.example.labs.azimo.note.ui.component.RegisterActivitySubComponent;
import com.example.labs.azimo.note.ui.component.WelcomeActivitySubComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@Module
public abstract class BuildersModule {
    @Binds
    @IntoMap
    @ActivityKey(WelcomeActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindWelcomeActivityInjectorFactory(WelcomeActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(DispatcherActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindDispatcherActivityInjectorFactory(DispatcherActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindLoginActivityInjectorFactory(LoginActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(RegisterActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindRegisterActivityInjectorFactory(RegisterActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(NotesActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindNotesActivityInjectorFactory(NotesActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(AddNoteActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindAddNoteActivityInjectorFactory(AddNoteActivitySubComponent.Builder builder);
}