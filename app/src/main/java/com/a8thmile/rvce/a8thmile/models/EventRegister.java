package com.a8thmile.rvce.a8thmile.models;

/**
 * Created by vignesh on 24/1/17.
 */

public class EventRegister{
    int user_id;
    int event_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public EventRegister(int event_id, int user_id) {

        this.user_id = user_id;
        this.event_id = event_id;
    }
}
