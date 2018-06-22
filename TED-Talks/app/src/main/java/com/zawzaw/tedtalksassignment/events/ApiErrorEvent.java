package com.zawzaw.tedtalksassignment.events;

/**
 * Created by zawzaw on 22/06/2018.
 */

public class ApiErrorEvent {

    private String errorMessage;

    public ApiErrorEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
