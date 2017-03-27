package com.a8thmile.rvce.a8thmile.api;

import com.a8thmile.rvce.a8thmile.models.EventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by vignesh on 23/1/17.
 */

public interface EventsGetClient {

    @GET("/event")
    Call<EventResponse> getEvent(@Header("token") String token);
}
