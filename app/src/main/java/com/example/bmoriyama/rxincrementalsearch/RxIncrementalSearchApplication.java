package com.example.bmoriyama.rxincrementalsearch;

import android.app.Application;

import com.example.bmoriyama.rxincrementalsearch.dagger.ApplicationComponent;
import com.example.bmoriyama.rxincrementalsearch.dagger.ApplicationModule;
import com.example.bmoriyama.rxincrementalsearch.dagger.DaggerApplicationComponent;

public class RxIncrementalSearchApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
