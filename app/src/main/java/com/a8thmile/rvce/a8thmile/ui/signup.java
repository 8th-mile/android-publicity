package com.a8thmile.rvce.a8thmile.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.signup.SignUpPresenter;
import com.a8thmile.rvce.a8thmile.signup.SignUpPresenterImpl;
import com.a8thmile.rvce.a8thmile.signup.SignUpView;
import com.a8thmile.rvce.a8thmile.R;
import com.dd.CircularProgressButton;

public class signup extends AppCompatActivity implements SignUpView {
    private Button otpbtn;
    private CircularProgressButton cb;
    private EditText mNameEditText,mEmailEditText,mPhoneEditText;
    private String mName,mEmail,mPhone;
    private SignUpPresenter mSignUpPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mNameEditText=(EditText) findViewById(R.id.nameEditText);
        mEmailEditText=(EditText) findViewById(R.id.emailEditText);
        mPhoneEditText=(EditText) findViewById(R.id.phoneEditText);
        mSignUpPresenter=new SignUpPresenterImpl(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        cb = (CircularProgressButton) findViewById(R.id.btnWithText);
        otpbtn = (Button) findViewById(R.id.btnWithText);
        otpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInputs();
                mSignUpPresenter.validateInputs(mName,mPhone,mEmail);
            }
        });
    }



    @Override
    public void startCircularProgressButton() {
        cb.setIndeterminateProgressMode(true);

    }

    @Override
    public void setCircularProgressStatus(int value) {
        cb.setProgress(value);
    }

    @Override
    public void onValidationFailure(String ValidationFailMessage) {
        Toast toast= Toast.makeText(this,ValidationFailMessage,Toast.LENGTH_LONG);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.RED);
        toast.show();
    }

    @Override
    public void goToOtpActivity() {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);

    }

    @Override
    public void displayFailureToast() {
        Toast.makeText(this,"Sign Up Failed",Toast.LENGTH_LONG).show();
    }

    private void getInputs() {
        mName=mNameEditText.getText().toString();
        mEmail=mEmailEditText.getText().toString();
        mPhone=mPhoneEditText.getText().toString();
    }
}
