package com.a8thmile.rvce.a8thmile.login;

import com.a8thmile.rvce.a8thmile.models.LoginResponse;

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

        mLoginView.goToHomeActivity(mLoginResponse /*email,String name*/);
    }

    @Override
    public void onFailure(String message) {

        mLoginView.displayFailureToast(message);

    }


    @Override
    public void tokenLogin(String email,String token) {
mLoginInteractor.callLoginApi(email,token,this);
    }
}
