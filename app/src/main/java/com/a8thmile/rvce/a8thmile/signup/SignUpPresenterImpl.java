package com.a8thmile.rvce.a8thmile.signup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ashwin on 13/1/17.
 */
public class SignUpPresenterImpl implements SignUpPresenter, SignUpInteractor.onLoginFinishedListener{

    private SignUpView mSignUpView;
    private SignUpInteractor mSignUpInteractor;
   // private String phoneNumber;

    public SignUpPresenterImpl(SignUpView signUpView){
        mSignUpView = signUpView;
        mSignUpInteractor = new SignUpInteractorImpl();
    }

    @Override
    public boolean checkStringLength(String phone) {
        if(phone.length()==STRING_LENGTH)
            return true;
        else
            return false;
    }

    @Override
    public Boolean validateName(String name) {
        return (name.length()>0);
    }

    @Override
    public Boolean validatePhoneNUmber(String phone) {

        return checkStringLength(phone);
    }

    @Override
    public Boolean validateEmail(String Email) {
        //Check for the valid email format using regular expressions
        String EMAIL_REGEX = "^[\\w_\\.+-]*[\\w_\\.-]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        Pattern pattern=Pattern.compile(EMAIL_REGEX);
        Matcher match=pattern.matcher(Email);
        return match.matches();
    }

    @Override
    public void validateInputs(String name,String phone,String email) {
        mSignUpView.startCircularProgressButton();
        mSignUpView.setCircularProgressStatus(INITIAL_PROGRESS);

        Boolean vName=validateName(name);
        Boolean vEmail=validateEmail(email);
        Boolean vPhone=validatePhoneNUmber(phone);

        if (vName&&vEmail&&vPhone) {
            mSignUpView.setCircularProgressStatus(VALIDATED_PROGRESS);
            mSignUpInteractor.callSignUpApi(name,phone,email, this);
        } else {
            //Make Progress Bar to disappear and Get Otp Button to Appear Again
            mSignUpView.setCircularProgressStatus(FAILED_PROGRESS);


            if(!vName)
                mSignUpView.onValidationFailure("Name Field Cannot Be Empty");
            else if(!vEmail)
                mSignUpView.onValidationFailure("Invalid Email Address");
            else
                mSignUpView.onValidationFailure("Invalid Phone Number Address");
        }
    }

    @Override
    public void onSucess() {
        mSignUpView.setCircularProgressStatus(COMPLETED_PROGRESS);
        mSignUpView.goToOtpActivity();
    }

    @Override
    public void onFailure() {
        mSignUpView.setCircularProgressStatus(FAILED_PROGRESS);


    }
}
