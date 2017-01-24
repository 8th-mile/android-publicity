package com.a8thmile.rvce.a8thmile.models;

/**
 * Created by vignesh on 24/1/17.
 */

public class EventRegisterResponse {
    String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public EventRegisterResponse(String success) {

        this.success = success;
    }
}
