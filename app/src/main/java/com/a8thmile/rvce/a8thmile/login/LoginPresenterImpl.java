package com.a8thmile.rvce.a8thmile.login;

import android.content.Context;
import android.content.Intent;

import com.a8thmile.rvce.a8thmile.models.LoginResponse;
import com.a8thmile.rvce.a8thmile.ui.MainActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by ashwin on 13/1/17.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.onLoginFinishedListener{

    private LoginView mLoginView;
    private LoginInteractor mLoginInteractor;

    public LoginPresenterImpl(LoginView loginView){
        mLoginView = loginView;
        mLoginInteractor = new LoginInteractorImpl();
    }


    @Override
    public void onSucess(LoginResponse mLoginResponse) {
        mLoginView.setCircularProgressStatus(COMPLETED_PROGRESS);
        mLoginView.goToHomeActivity(mLoginResponse /*email,String name*/);
    }

    @Override
    public void onFailure(String message) {
        mLoginView.setCircularProgressStatus(FAILED_PROGRESS);
        mLoginView.displayFailureToast(message);

    }


    @Override
    public void tokenLogin(String email,String token) {
mLoginInteractor.callLoginApi(email,token,this);
    }
}
