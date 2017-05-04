package com.example.bmoriyama.rxincrementalsearch.viewmodel;

/*
A model for tracking the state of an async search request
 */
public class SearchActionStateModel {

    private static SearchActionStateModel instance;
    private boolean inProgress;
    private boolean success;
    private String errorMessage;

    public static synchronized SearchActionStateModel getInstance() {
        if (instance == null) {
            instance = new SearchActionStateModel();
        }
        return instance;
    }

    public SearchActionStateModel inProgress() {
        inProgress = true;
        return instance;
    }

    public SearchActionStateModel success() {
        success = true;
        return instance;
    }

    public SearchActionStateModel failure(String errorMessage) {
        this.errorMessage = errorMessage;
        return instance;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public boolean isSuccess() {
        return success;
    }
}
