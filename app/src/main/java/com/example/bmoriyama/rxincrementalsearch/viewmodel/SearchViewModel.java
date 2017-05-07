package com.example.bmoriyama.rxincrementalsearch.viewmodel;

import android.util.Log;
import android.widget.SearchView;

import com.example.bmoriyama.rxincrementalsearch.model.Item;
import com.example.bmoriyama.rxincrementalsearch.model.SearchResponse;
import com.example.bmoriyama.rxincrementalsearch.service.StackOverflowService;
import com.example.bmoriyama.rxincrementalsearch.ui.activity.SearchActivity;
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
    private List<Item> itemList;

    CompositeDisposable compositeDisposable;

    public SearchViewModel(SearchActivity view, StackOverflowService service) {
        this.view = view;
        this.service = service;
        itemList = new ArrayList<>();
    }

    private ObservableTransformer<QueryTextChangeEvent, SearchActionStateModel> createObservableSearchEvent() {
        ObservableTransformer<QueryTextChangeEvent, SearchActionStateModel> search = events -> events
                .switchMap(event -> service.searchQuestions(event.getQueryString())
                        .map(response -> onSuccess(response))
                        .onErrorReturn(t -> onFailure(t))
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(SearchActionStateModel.getInstance().inProgress()));
        return search;
    }

    public void subscribeToSearchEvents(SearchView searchView) {
        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(RxSearchView.queryTextChanges(searchView)
                .map(ignored -> new QueryTextChangeEvent(searchView.getQuery().toString()))
                .compose(createObservableSearchEvent())
                .subscribe(model -> {
                    if (!SearchActionStateModel.getInstance().isInProgress()) {
                        if (SearchActionStateModel.getInstance().isSuccess()) {
                            view.updateSearchResults(itemList);
                        } else {
                            view.showError();
                        }
                    }
                }, t -> { throw new OnErrorNotImplementedException(t); }));
    }

    private SearchActionStateModel onSuccess(SearchResponse response) {
        itemList.clear();
        for (Item item : response.getItems()) {
            itemList.add(item);
        }
        return SearchActionStateModel.getInstance().success();
    }

    private SearchActionStateModel onFailure(Throwable t) {
        Log.e("error", t.getLocalizedMessage());
        return SearchActionStateModel.getInstance().failure(t.getMessage());
    }

    public void teardown() {
        compositeDisposable.dispose();
    }
}
