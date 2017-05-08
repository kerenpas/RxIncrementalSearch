package com.example.bmoriyama.rxincrementalsearch.dagger;

import com.example.bmoriyama.rxincrementalsearch.R;
import com.example.bmoriyama.rxincrementalsearch.adapter.SearchResultsListAdapter;
import com.example.bmoriyama.rxincrementalsearch.service.StackOverflowService;
import com.example.bmoriyama.rxincrementalsearch.ui.activity.SearchActivity;
import com.example.bmoriyama.rxincrementalsearch.viewmodel.action.SearchActionStateModel;
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
    SearchViewModel provideSearchViewModel(StackOverflowService service, SearchActionStateModel searchActionStateModel) {
        return new SearchViewModel(activity, service, searchActionStateModel);
    }

    @Provides
    SearchActionStateModel provideSearchActionStateModel() {
        return new SearchActionStateModel();
    }

    @Provides
    SearchResultsListAdapter provideSearchResultsListAdapter() {
        return new SearchResultsListAdapter(activity, R.layout.list_item_search_result);
    }
}
