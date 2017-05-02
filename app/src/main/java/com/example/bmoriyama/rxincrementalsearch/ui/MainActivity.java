package com.example.bmoriyama.rxincrementalsearch.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bmoriyama.rxincrementalsearch.R;
import com.example.bmoriyama.rxincrementalsearch.RxIncrementalSearchApplication;
import com.example.bmoriyama.rxincrementalsearch.dagger.MainActivityComponent;
import com.example.bmoriyama.rxincrementalsearch.dagger.MainActivityModule;

public class MainActivity extends AppCompatActivity {

    MainActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActivityComponent();
    }

    private void setupActivityComponent() {
        activityComponent = ((RxIncrementalSearchApplication) getApplication()).getApplicationComponent()
                .plus(new MainActivityModule(this));
        activityComponent.inject(this);
    }
}
