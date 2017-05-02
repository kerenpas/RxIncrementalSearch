package com.example.bmoriyama.rxincrementalsearch.dagger;

import com.example.bmoriyama.rxincrementalsearch.ui.MainActivity;

import dagger.Module;

@Module
public class MainActivityModule {
    MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }
}
