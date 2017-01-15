package com.a8thmile.rvce.a8thmile.signup;

/**
 * Created by ashwin on 12/1/17.
 */
public interface SignUpView {



    public void startCircularProgressButton();

    public void setCircularProgressStatus(int value);

    public void onValidationFailure(String ValidationFailMessage);

    public void goToOtpActivity();

    public void displayFailureToast();


}
