package com.a8thmile.rvce.a8thmile.api;

import com.a8thmile.rvce.a8thmile.models.LoginRequest;
import com.a8thmile.rvce.a8thmile.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ashwin on 13/1/17.
 */
public interface LoginClient {

    @POST("/user/login")
    Call<LoginResponse> loginResponse(
            @Body LoginRequest mLoginRequest
            );
}
