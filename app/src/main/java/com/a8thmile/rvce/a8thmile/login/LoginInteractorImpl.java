package com.a8thmile.rvce.a8thmile.login;

import android.util.Log;

import com.a8thmile.rvce.a8thmile.api.LoginClient;
import com.a8thmile.rvce.a8thmile.api.ServiceGenerator;
import com.a8thmile.rvce.a8thmile.models.LoginRequest;
import com.a8thmile.rvce.a8thmile.models.LoginResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashwin on 13/1/17.
 */
public class LoginInteractorImpl implements LoginInteractor {

    //private SignUpPresenter mSignUpPresenter;
    @Override
    public void callLoginApi(String phone, final onLoginFinishedListener listener) {
        LoginRequest mLoginRequest = createLoginRequestObject(phone);
        // Create a very simple REST adapter which points the GitHub API endpoint.
        LoginClient client = ServiceGenerator.createService(LoginClient.class);

        // Fetch and print a list of the contributors to this library.
        Call<LoginResponse> call =
                client.loginResponse(mLoginRequest.getPhone());
        //final LoginResponse mLoginResponse ;
        /*
        try {

            LoginResponse  mLoginResponse = call.execute()
                    .body();

            if(checkResponse(mLoginResponse)==true)
                listener.onSucess();
            else
                listener.onFailure();


        } catch (IOException e) {
            e.printStackTrace();
        }

        if(checkResponse(mLoginResponse)==true)
            listener.onSucess();
        else
            listener.onFailure();
    }
*/

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                    LoginResponse mLoginResponse = response.body();
                    if (checkResponse(mLoginResponse) == true)
                        listener.onSucess();
                    else
                        listener.onFailure();



            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });


    }


    private LoginRequest createLoginRequestObject(String phone) {
        return new LoginRequest(phone);
    }

    private boolean checkResponse(LoginResponse mLoginResponse){
        Log.v("debug","response is " +mLoginResponse);
        if(mLoginResponse.getSuccess().equalsIgnoreCase("true"))
            return true;
        else
            return false;
    }
}
