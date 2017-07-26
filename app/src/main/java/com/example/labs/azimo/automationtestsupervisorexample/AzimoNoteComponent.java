package com.example.labs.azimo.automationtestsupervisorexample;

import com.example.labs.azimo.automationtestsupervisorexample.api.module.ApiModule;
import com.example.labs.azimo.automationtestsupervisorexample.data.module.DataModule;
import com.example.labs.azimo.automationtestsupervisorexample.utils.module.UtilsModule;

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
}