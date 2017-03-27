package com.a8thmile.rvce.a8thmile.api;

import com.a8thmile.rvce.a8thmile.models.EventRegister;
import com.a8thmile.rvce.a8thmile.models.EventRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by vignesh on 24/1/17.
 */

public interface EventRegisterApi {
    @POST("/event/register")
    Call<EventRegisterResponse> register(@Body EventRegister eventRegister, @Header("token") String token);
}
