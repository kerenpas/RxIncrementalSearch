package com.example.bmoriyama.rxincrementalsearch.service;

import com.example.bmoriyama.rxincrementalsearch.model.SearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StackOverflowAPI {
    @GET("2.2/search?order=desc&sort=activity&site=stackoverflow")
    Observable<SearchResponse> searchQuestions(@Query("intitle") String inTitle);
}
