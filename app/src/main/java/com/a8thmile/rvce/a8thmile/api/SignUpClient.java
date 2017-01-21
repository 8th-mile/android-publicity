package com.a8thmile.rvce.a8thmile.api;

import com.a8thmile.rvce.a8thmile.models.LoginRequest;
import com.a8thmile.rvce.a8thmile.models.LoginResponse;
import com.a8thmile.rvce.a8thmile.models.SignUpRequest;
import com.a8thmile.rvce.a8thmile.models.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ashwin on 13/1/17.
 */
public interface SignUpClient {

    @POST("/user/signup")
    Call<SignUpResponse> signupResponse(
            @Body SignUpRequest mSignUpRequest
    );
}
