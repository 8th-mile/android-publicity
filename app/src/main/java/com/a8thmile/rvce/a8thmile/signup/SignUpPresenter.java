package com.a8thmile.rvce.a8thmile.signup;

/**
 * Created by ashwin on 13/1/17.
 */
public interface SignUpPresenter {

    public static int INITIAL_PROGRESS = 5;
    public static int STRING_LENGTH = 10;
    public static int VALIDATED_PROGRESS = 50;
    public static int COMPLETED_PROGRESS = 100;
    public static int FAILED_PROGRESS = 0;

    public boolean checkStringLength(String phone);
    public Boolean validateName(String name);
    public Boolean validatePhoneNUmber(String phone);
    public Boolean validateEmail(String Email);
    public void validateInputs(String name,String phone,String email);



}
