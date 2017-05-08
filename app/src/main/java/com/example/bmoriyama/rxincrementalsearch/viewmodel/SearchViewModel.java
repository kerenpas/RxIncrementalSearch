package com.example.bmoriyama.rxincrementalsearch.viewmodel;

import android.widget.SearchView;

import com.example.bmoriyama.rxincrementalsearch.model.Item;
import com.example.bmoriyama.rxincrementalsearch.model.SearchResponse;
import com.example.bmoriyama.rxincrementalsearch.service.StackOverflowService;
import com.example.bmoriyama.rxincrementalsearch.ui.activity.SearchActivity;
import com.example.bmoriyama.rxincrementalsearch.viewmodel.action.SearchActionStateModel;
import com.example.bmoriyama.rxincrementalsearch.viewmodel.event.QueryTextChangeEvent;
import com.jakewharton.rxbinding2.widget.RxSearchView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.exceptions.OnErrorNotImplementedException;

public class SearchViewModel {

    private SearchActivity view;
    private StackOverflowService service;
    private SearchActionStateModel actionStateModel;
    private List<Item> itemList;

    CompositeDisposable compositeDisposable;

    public SearchViewModel(SearchActivity view, StackOverflowService service, SearchActionStateModel actionStateModel) {
        this.view = view;
        this.service = service;
        this.actionStateModel = actionStateModel;
        itemList = new ArrayList<>();
    }

    private ObservableTransformer<QueryTextChangeEvent, SearchActionStateModel> createObservableSearchEvent() {
        ObservableTransformer<QueryTextChangeEvent, SearchActionStateModel> search = events -> events
                .switchMap(event -> service.searchQuestions(event.getQueryString())
                        .map(response -> onSuccess(response))
                        .onErrorReturn(t -> onFailure(t))
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(actionStateModel.inProgress()));
        return search;
    }

    public void subscribeToSearchEvents(SearchView searchView) {
        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(RxSearchView.queryTextChanges(searchView)
                .map(ignored -> new QueryTextChangeEvent(searchView.getQuery().toString()))
                .compose(createObservableSearchEvent())
                .subscribe(model -> {
                    if (!actionStateModel.isInProgress()) {
                        if (actionStateModel.isSuccess()) {
                            updateSearchResults(itemList);
                        } else {
                            showError();
                        }
                    }
                }, t -> { throw new OnErrorNotImplementedException(t); }));
    }

    public SearchActionStateModel onSuccess(SearchResponse response) {
        itemList.clear();
        for (Item item : response.getItems()) {
            itemList.add(item);
        }
        return actionStateModel.success();
    }

    public SearchActionStateModel onFailure(Throwable t) {
        return actionStateModel.failure(t.getMessage());
    }

    public void updateSearchResults(List<Item> itemList) {
        view.updateSearchResults(itemList);
    }

    public void showError() {
        view.showError();
    }

    public void teardown() {
        compositeDisposable.dispose();
    }
}
