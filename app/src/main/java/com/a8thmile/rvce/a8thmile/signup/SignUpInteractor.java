package com.a8thmile.rvce.a8thmile.signup;

/**
 * Created by ashwin on 13/1/17.
 */
public interface SignUpInteractor {

    public interface onLoginFinishedListener{
        public void onSucess();
        public void onFailure();
    }

    public void callSignUpApi(String name,String phone,String email, onLoginFinishedListener listener);


}
