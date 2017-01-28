package com.a8thmile.rvce.a8thmile.login;

import com.a8thmile.rvce.a8thmile.models.LoginResponse;

/**
 * Created by ashwin on 12/1/17.
 */
public interface LoginView {


    public void goToHomeActivity(LoginResponse mLoginResponse/*String email,String name*/);

    public void displayFailureToast(String message);


}
