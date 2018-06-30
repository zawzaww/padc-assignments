package com.zawzaw.candkassignment.events;

public class ApiErrorEvent {

    private String errorMessage;

    public ApiErrorEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
