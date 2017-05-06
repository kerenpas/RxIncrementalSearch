package com.example.bmoriyama.rxincrementalsearch.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.bmoriyama.rxincrementalsearch.R;
import com.example.bmoriyama.rxincrementalsearch.RxIncrementalSearchApplication;
import com.example.bmoriyama.rxincrementalsearch.adapter.SearchResultsListAdapter;
import com.example.bmoriyama.rxincrementalsearch.dagger.SearchActivityComponent;
import com.example.bmoriyama.rxincrementalsearch.dagger.SearchActivityModule;
import com.example.bmoriyama.rxincrementalsearch.model.Item;
import com.example.bmoriyama.rxincrementalsearch.viewmodel.SearchViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchActivity extends AppCompatActivity {

    SearchActivityComponent activityComponent;
    Unbinder unbinder;

    @BindView(R.id.sv_main)
    SearchView svMain;

    @BindView(R.id.lv_search_results)
    ListView lvSearchResults;

    @Inject
    SearchViewModel viewmodel;

    @Inject
    SearchResultsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        setupActivityComponent();
        viewmodel.subscribeToSearchEvents(svMain);
        lvSearchResults.setAdapter(adapter);
    }

    private void setupActivityComponent() {
        activityComponent = ((RxIncrementalSearchApplication) getApplication()).getApplicationComponent()
                .plus(new SearchActivityModule(this));
        activityComponent.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        viewmodel.teardown();
    }

    public void updateSearchResults(List<Item> itemList) {
        adapter.setItemList(itemList);
        adapter.notifyDataSetChanged();
    }
}
