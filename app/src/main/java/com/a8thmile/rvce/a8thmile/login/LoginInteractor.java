package com.a8thmile.rvce.a8thmile.login;

import android.content.Context;
import android.util.Log;

import com.a8thmile.rvce.a8thmile.models.LoginResponse;
import com.a8thmile.rvce.a8thmile.ui.MainActivity;

/**
 * Created by ashwin on 13/1/17.
 */
public interface LoginInteractor {

    public interface onLoginFinishedListener{
        public void onSucess(LoginResponse loginResponse);
        public void onFailure(String message);
    }


    public void callLoginApi(String email,String token, onLoginFinishedListener listener);


}
