package com.example.bmoriyama.rxincrementalsearch.dagger;

import com.example.bmoriyama.rxincrementalsearch.ui.MainActivity;

import dagger.Subcomponent;

@Subcomponent(
        modules = { MainActivityModule.class }
)

public interface MainActivityComponent {
    void inject(MainActivity activity);
}
