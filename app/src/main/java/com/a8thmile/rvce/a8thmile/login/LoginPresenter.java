package com.a8thmile.rvce.a8thmile.login;

/**
 * Created by ashwin on 13/1/17.
 */
public interface LoginPresenter {

    public static int INITIAL_PROGRESS = 5;
    public static int STRING_LENGTH = 10;
    public static int VALIDATED_PROGRESS = 50;
    public static int COMPLETED_PROGRESS = 100;
    public static int FAILED_PROGRESS = 0;

    public boolean checkStringLength(String phone);

    public void validatePhoneNUmber(String phone);



}
