package com.example.bmoriyama.rxincrementalsearch.viewmodel.event;

public class QueryTextChangeEvent {

    private String queryString;

    public QueryTextChangeEvent(String queryString) {
        this.queryString = queryString;
    }

    public String getQueryString() {
        return queryString;
    }
}
