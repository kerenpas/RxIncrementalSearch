package com.example.bmoriyama.rxincrementalsearch.viewmodel.action;

/*
A model for tracking the state of an async search request
 */
public class SearchActionStateModel {

    private boolean inProgress;
    private boolean success;
    private String errorMessage;

    public SearchActionStateModel() {

    }

    public SearchActionStateModel inProgress() {
        inProgress = true;
        success = false;
        return this;
    }

    public SearchActionStateModel success() {
        inProgress = false;
        success = true;
        return this;
    }

    public SearchActionStateModel failure(String errorMessage) {
        inProgress = false;
        this.errorMessage = errorMessage;
        return this;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public boolean isSuccess() {
        return success;
    }
}
