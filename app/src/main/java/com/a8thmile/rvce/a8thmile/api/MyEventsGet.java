package com.a8thmile.rvce.a8thmile.api;

import com.a8thmile.rvce.a8thmile.models.MyEventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by vignesh on 24/1/17.
 */

public interface MyEventsGet {
    @GET("/user")
    Call<MyEventResponse> getMyEvents(@Header("token") String token, @Query("user_id") int user_id);
}
