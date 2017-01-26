package com.a8thmile.rvce.a8thmile.api;

import com.a8thmile.rvce.a8thmile.models.LoginRequest;
import com.a8thmile.rvce.a8thmile.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ashwin on 13/1/17.
 */
public interface LoginClient {

    @POST("/user/signup")
    Call<LoginResponse> loginResponse(
            @Body LoginRequest mLoginRequest
            );
}
