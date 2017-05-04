package com.example.bmoriyama.rxincrementalsearch.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;

import com.example.bmoriyama.rxincrementalsearch.R;
import com.example.bmoriyama.rxincrementalsearch.RxIncrementalSearchApplication;
import com.example.bmoriyama.rxincrementalsearch.dagger.SearchActivityComponent;
import com.example.bmoriyama.rxincrementalsearch.dagger.SearchActivityModule;
import com.example.bmoriyama.rxincrementalsearch.viewmodel.SearchViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchActivity extends AppCompatActivity {

    SearchActivityComponent activityComponent;
    Unbinder unbinder;

    @BindView(R.id.sv_main)
    SearchView svMain;

    @Inject
    SearchViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        setupActivityComponent();
        viewmodel.subscribeToSearchEvents(svMain);
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
}
