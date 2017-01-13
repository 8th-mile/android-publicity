package com.a8thmile.rvce.a8thmile.login;

/**
 * Created by ashwin on 13/1/17.
 */
public interface LoginInteractor {

    public interface onLoginFinishedListener{
        public void onSucess();
        public void onFailure();
    }

    public void callLoginApi(String phone, onLoginFinishedListener listener);


}
