package com.a8thmile.rvce.a8thmile.login;

/**
 * Created by ashwin on 13/1/17.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.onLoginFinishedListener{

    private LoginView mLoginView;
    private LoginInteractor mLoginInteractor;
   // private String phoneNumber;

    public LoginPresenterImpl(LoginView loginView){
        mLoginView = loginView;
        mLoginInteractor = new LoginInteractorImpl();
    }

    @Override
    public boolean checkStringLength(String phone) {
        if(phone.length()==STRING_LENGTH)
            return true;
        else
            return false;
    }

    @Override
    public void validatePhoneNUmber(String phone) {
        mLoginView.startCircularProgressButton();
        mLoginView.setCircularProgressStatus(INITIAL_PROGRESS);

        if(checkStringLength(phone)) {
            mLoginView.setCircularProgressStatus(VALIDATED_PROGRESS);
            mLoginInteractor.callLoginApi(phone, this);
        }
        else{
            mLoginView.setCircularProgressStatus(FAILED_PROGRESS);
            mLoginView.onValidationFailure();
        }
    }

    @Override
    public void onSucess() {
        mLoginView.setCircularProgressStatus(COMPLETED_PROGRESS);
        mLoginView.goToOtpActivity();
    }

    @Override
    public void onFailure() {
        mLoginView.setCircularProgressStatus(FAILED_PROGRESS);

    }
}
