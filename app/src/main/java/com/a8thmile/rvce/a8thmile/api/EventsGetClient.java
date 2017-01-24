package com.a8thmile.rvce.a8thmile.api;

import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.models.EventResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by vignesh on 23/1/17.
 */

public interface EventsGetClient {

    @GET("/event")
    Call<EventResponse> getEvent(@Header("token") String token);
}
