package com.a8thmile.rvce.a8thmile.models;

/**
 * Created by ashwin on 13/1/17.
 */
public class SignUpRequest {
    String phone;
    String name;
    String email;

    public SignUpRequest(String name,String email,String phone){
        this.phone = phone;
        this.name=name;
        this.email=email;
    }
}
