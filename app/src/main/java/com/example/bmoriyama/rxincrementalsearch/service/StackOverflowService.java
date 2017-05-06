package com.example.bmoriyama.rxincrementalsearch.service;

import android.net.Uri;
import android.util.Log;

import com.example.bmoriyama.rxincrementalsearch.model.SearchResponse;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class StackOverflowService {
    private String BASE_URL = "https://api.stackexchange.com/";

    private StackOverflowAPI stackOverflowAPI;

    public StackOverflowService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        stackOverflowAPI = retrofit.create(StackOverflowAPI.class);
    }

    public Observable<SearchResponse> searchQuestions(String inTitle) {
        Log.i("query", Uri.encode(inTitle));
        return stackOverflowAPI.searchQuestions(Uri.encode(inTitle));
    }
}
