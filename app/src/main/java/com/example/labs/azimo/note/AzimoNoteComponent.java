package com.example.labs.azimo.note;

import com.example.labs.azimo.note.api.manager.UserManager;
import com.example.labs.azimo.note.api.mock.CloudMock;
import com.example.labs.azimo.note.api.module.ApiModule;
import com.example.labs.azimo.note.data.UserDataStore;
import com.example.labs.azimo.note.data.module.DataModule;
import com.example.labs.azimo.note.utils.module.UtilsModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by F1sherKK on 26/07/2017.
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AzimoNoteModule.class,
        UtilsModule.class,
        DataModule.class,
        ApiModule.class,
        BuildersModule.class}
)
public interface AzimoNoteComponent {
    void inject(AzimoNoteApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(AzimoNoteApplication application);

        @BindsInstance
        Builder utilsModule(UtilsModule utilsModule);

        @BindsInstance
        Builder dataModule(DataModule dataModule);

        @BindsInstance
        Builder apiModule(ApiModule apiModule);

        AzimoNoteComponent build();
    }

    public CloudMock getCloudMock();
    public Gson getGson();
    public UserManager getUserManager();
    public UserDataStore getUserDataStore();
}