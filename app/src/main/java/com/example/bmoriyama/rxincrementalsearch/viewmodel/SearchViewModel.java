package com.example.bmoriyama.rxincrementalsearch.viewmodel;

import android.widget.SearchView;

import com.example.bmoriyama.rxincrementalsearch.service.StackOverflowService;
import com.example.bmoriyama.rxincrementalsearch.ui.SearchActivity;
import com.jakewharton.rxbinding2.widget.RxSearchView;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.exceptions.OnErrorNotImplementedException;

public class SearchViewModel {

    private SearchActivity view;
    private StackOverflowService service;

    public SearchViewModel(SearchActivity view, StackOverflowService service) {
        this.view = view;
        this.service = service;
    }

    private ObservableTransformer<QueryTextChangeEvent, SearchActionStateModel> createObservableSearchEvent() {
        ObservableTransformer<QueryTextChangeEvent, SearchActionStateModel> search = events -> events
                .flatMap(event -> service.searchQuestions(event.getQueryString())
                        .map(response -> SearchActionStateModel.getInstance().success())
                        .onErrorReturn(t -> SearchActionStateModel.getInstance().failure(t.getMessage()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(SearchActionStateModel.getInstance().inProgress()));
        return search;
    }

    public void subscribeToSearchEvents(SearchView searchView) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(RxSearchView.queryTextChanges(searchView)
                .map(ignored -> new QueryTextChangeEvent(searchView.getQuery().toString()))
                .compose(createObservableSearchEvent())
                .subscribe(model -> {
                    if (!SearchActionStateModel.getInstance().isInProgress()) {
                        if (SearchActionStateModel.getInstance().isSuccess()) {
                            // update the view stuff here - success
                        } else {
                            // show error
                        }
                    }
                }, t -> { throw new OnErrorNotImplementedException(t); }));
    }
}
