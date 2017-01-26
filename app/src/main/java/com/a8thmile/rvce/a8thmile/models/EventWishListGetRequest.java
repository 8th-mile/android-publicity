package com.a8thmile.rvce.a8thmile.models;

/**
 * Created by vignesh on 24/1/17.
 */

public class EventWishListGetRequest {
    int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public EventWishListGetRequest(int user_id) {
        this.user_id = user_id;

    }
}
