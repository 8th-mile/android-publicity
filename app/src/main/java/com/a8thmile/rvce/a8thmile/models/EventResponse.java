package com.a8thmile.rvce.a8thmile.models;

import java.util.List;

/**
 * Created by vignesh on 23/1/17.
 */

public class EventResponse {
private List<EventFields> results;

    public List<EventFields> getResults() {
        return results;
    }

    public void setResults(List<EventFields> results) {
        this.results = results;
    }

    public EventResponse(List<EventFields> results) {

        this.results = results;
    }
}
