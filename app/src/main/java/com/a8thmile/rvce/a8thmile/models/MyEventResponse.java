package com.a8thmile.rvce.a8thmile.models;

import java.util.List;

/**
 * Created by vignesh on 28/1/17.
 */

public class MyEventResponse {

    private String id;
    private String name;
    private List<Integer> registered_events;
    private List<Integer> events_in_wishlist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyEventResponse(String id, String name, List<Integer> registered_events, List<Integer> events_in_wishlist) {
        this.id = id;
        this.name = name;
        this.registered_events = registered_events;
        this.events_in_wishlist = events_in_wishlist;
    }

    public List<Integer> getRegistered_events() {

        return registered_events;
    }

    public void setRegistered_events(List<Integer> registered_events) {
        this.registered_events = registered_events;
    }

    public List<Integer> getEvents_in_wishlist() {
        return events_in_wishlist;
    }

    public void setEvents_in_wishlist(List<Integer> events_in_wishlist) {
        this.events_in_wishlist = events_in_wishlist;
    }

}
