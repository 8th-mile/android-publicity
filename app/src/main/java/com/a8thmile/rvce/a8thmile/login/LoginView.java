package com.a8thmile.rvce.a8thmile.login;

/**
 * Created by ashwin on 12/1/17.
 */
public interface LoginView {



    public void startCircularProgressButton();

    public void setCircularProgressStatus(int value);

    public void onValidationFailure();

    public void goToOtpActivity();

    public void displayFailureToast();


}
