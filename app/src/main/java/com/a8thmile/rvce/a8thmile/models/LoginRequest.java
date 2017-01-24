package com.a8thmile.rvce.a8thmile.models;

/**
 * Created by ashwin on 13/1/17.
 */
public class LoginRequest {
    String idtoken;
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginRequest(String email,String idtoken)
    {
        this.email=email;
        this.idtoken=idtoken;
    }
    public String getToken() {
        return idtoken;
    }

    public void setToken(String idtoken) {
        this.idtoken = idtoken;
    }
}
