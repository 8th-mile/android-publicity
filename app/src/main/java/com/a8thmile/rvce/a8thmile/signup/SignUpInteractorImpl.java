package com.a8thmile.rvce.a8thmile.signup;


import com.a8thmile.rvce.a8thmile.api.ServiceGenerator;
import com.a8thmile.rvce.a8thmile.api.SignUpClient;
import com.a8thmile.rvce.a8thmile.models.SignUpRequest;
import com.a8thmile.rvce.a8thmile.models.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashwin on 13/1/17.
 */
public class SignUpInteractorImpl implements SignUpInteractor {

    //private SignUpPresenter mSignUpPresenter;
    @Override
    public void callSignUpApi(String name,String phone,String email, final onLoginFinishedListener listener) {
       SignUpRequest mSignUpRequest = createSignUpRequestObject(name,email,phone);
        // Create a very simple REST adapter which points the GitHub API endpoint.
        SignUpClient client = ServiceGenerator.createService(SignUpClient.class);

        // Fetch and print a list of the contributors to this library.
        Call<SignUpResponse> call =
                client.signupResponse(mSignUpRequest);
        //final SignUpResponse mSignUpResponse ;
        /*
        try {

            SignUpResponse  mSignUpResponse = call.execute()
                    .body();

            if(checkResponse(mSignUpResponse)==true)
                listener.onSucess();
            else
                listener.onFailure();


        } catch (IOException e) {
            e.printStackTrace();
        }

        if(checkResponse(mSignUpResponse)==true)
            listener.onSucess();
        else
            listener.onFailure();
    }
*/

        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                SignUpResponse mSignUpResponse = response.body();
                if(checkResponse(mSignUpResponse)==true)
                    listener.onSucess();
                else
                    listener.onFailure();

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });


    }


    private SignUpRequest createSignUpRequestObject(String name,String email,String phone) {
        return new SignUpRequest(name,email,phone);
    }

    private boolean checkResponse(SignUpResponse mSignUpResponse){
        if(mSignUpResponse.getSuccess().equalsIgnoreCase("true"))
            return true;
        else
            return false;
    }


}
