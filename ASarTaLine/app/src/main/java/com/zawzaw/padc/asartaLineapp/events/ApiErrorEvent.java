package com.zawzaw.padc.asartaLineapp.events;

/**
 * Created by zawzaw on 06/07/2018.
 */

public class ApiErrorEvent {

    private String mErrorMessage;

    public ApiErrorEvent(String errorMessage) {
        this.mErrorMessage = errorMessage;
    }

    public String getmErrorMessage() {
        return mErrorMessage;
    }

}
