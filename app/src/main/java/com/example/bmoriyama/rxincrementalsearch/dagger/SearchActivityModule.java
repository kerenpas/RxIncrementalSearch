package com.example.bmoriyama.rxincrementalsearch.dagger;

import com.example.bmoriyama.rxincrementalsearch.service.StackOverflowService;
import com.example.bmoriyama.rxincrementalsearch.ui.SearchActivity;
import com.example.bmoriyama.rxincrementalsearch.viewmodel.SearchViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchActivityModule {
    SearchActivity activity;

    public SearchActivityModule(SearchActivity activity) {
        this.activity = activity;
    }

    @Provides
    SearchViewModel provideSearchViewModel(SearchActivity activity, StackOverflowService service) {
        return new SearchViewModel(activity, service);
    }
}
