package com.example.bmoriyama.rxincrementalsearch.dagger;

import com.example.bmoriyama.rxincrementalsearch.RxIncrementalSearchApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = { ApplicationModule.class, ServiceModule.class }
)
public interface ApplicationComponent {
    void inject(RxIncrementalSearchApplication application);

    MainActivityComponent plus(MainActivityModule mainActivityModule);
}
