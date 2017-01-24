package com.a8thmile.rvce.a8thmile.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.a8thmile.rvce.a8thmile.api.LoginClient;
import com.a8thmile.rvce.a8thmile.api.ServiceGenerator;
import com.a8thmile.rvce.a8thmile.models.LoginRequest;
import com.a8thmile.rvce.a8thmile.models.LoginResponse;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashwin on 13/1/17.
 */
public class LoginInteractorImpl implements LoginInteractor,GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks {

    @Override
    public void callLoginApi(String email,String token, final onLoginFinishedListener listener) {
        LoginRequest mLoginRequest = createLoginRequestObject(email,token);
        // Create a very simple REST adapter which points the GitHub API endpoint.
        LoginClient client = ServiceGenerator.createService(LoginClient.class);

        // Fetch and print a list of the contributors to this library.
        Log.v("test","calling");
        Call<LoginResponse> call =
                client.loginResponse(mLoginRequest);

        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.code()==200) {

                    LoginResponse mLoginResponse = response.body();
                   listener.onSucess(mLoginResponse);
                }
                else if(response.code()==400)
                {
                        Log.v("test","error "+response.errorBody().toString());
                    listener.onFailure("Server Validation Failed. Try again");
                }
                else
                {
                    listener.onFailure("Server Error.Try again");
                }



            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                    listener.onFailure("Failed connecting to the server");
            }
        });


    }




    private LoginRequest createLoginRequestObject(String  email,String token) {
        return new LoginRequest(email,token);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
