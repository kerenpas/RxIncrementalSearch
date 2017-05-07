package com.example.bmoriyama.rxincrementalsearch.service;

import com.example.bmoriyama.rxincrementalsearch.model.SearchResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.reactivex.Observable;

public class StackOverflowService {

    private StackOverflowAPI stackOverflowAPI;

    public StackOverflowService(StackOverflowAPI stackOverflowAPI) {
        this.stackOverflowAPI = stackOverflowAPI;
    }

    public Observable<SearchResponse> searchQuestions(String inTitle) throws UnsupportedEncodingException {
        return stackOverflowAPI.searchQuestions(URLEncoder.encode(inTitle, "UTF-8"));
    }
}
