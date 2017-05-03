package com.example.bmoriyama.rxincrementalsearch.dagger;

import com.example.bmoriyama.rxincrementalsearch.service.StackOverflowService;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {
    @Provides
    StackOverflowService provideStackOverflowService() {
        return new StackOverflowService();
    }
}
