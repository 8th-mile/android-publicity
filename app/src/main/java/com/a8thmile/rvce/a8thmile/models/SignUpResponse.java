package com.a8thmile.rvce.a8thmile.models;

/**
 * Created by ashwin on 13/1/17.
 */
public class SignUpResponse {
    private String otp;
    private String success;
    private String statuscode;

    public SignUpResponse(String otp, String success, String statuscode){
        this.otp = otp;
        this.success = success;
        this.statuscode = statuscode;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }
}
