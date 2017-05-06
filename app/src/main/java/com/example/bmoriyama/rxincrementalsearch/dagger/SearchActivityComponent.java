package com.example.bmoriyama.rxincrementalsearch.dagger;

import com.example.bmoriyama.rxincrementalsearch.ui.activity.SearchActivity;

import dagger.Subcomponent;

@Subcomponent(
        modules = { SearchActivityModule.class }
)

public interface SearchActivityComponent {
    void inject(SearchActivity activity);
}
