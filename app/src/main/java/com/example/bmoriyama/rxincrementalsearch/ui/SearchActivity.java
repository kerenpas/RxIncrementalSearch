package com.example.bmoriyama.rxincrementalsearch.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import com.example.bmoriyama.rxincrementalsearch.R;
import com.example.bmoriyama.rxincrementalsearch.RxIncrementalSearchApplication;
import com.example.bmoriyama.rxincrementalsearch.dagger.SearchActivityComponent;
import com.example.bmoriyama.rxincrementalsearch.dagger.SearchActivityModule;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    SearchActivityComponent activityComponent;

    @BindView(R.id.sv_main)
    SearchView svMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupActivityComponent();
    }

    private void setupActivityComponent() {
        activityComponent = ((RxIncrementalSearchApplication) getApplication()).getApplicationComponent()
                .plus(new SearchActivityModule(this));
        activityComponent.inject(this);
    }
}
