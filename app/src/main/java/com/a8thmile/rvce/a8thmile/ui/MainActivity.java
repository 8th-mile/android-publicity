package com.a8thmile.rvce.a8thmile.ui;

import android.graphics.Color;
import android.os.Handler;


import android.app.SearchManager;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.login.LoginPresenter;
import com.a8thmile.rvce.a8thmile.login.LoginPresenterImpl;
import com.a8thmile.rvce.a8thmile.login.LoginView;
import com.dd.CircularProgressButton;

import com.a8thmile.rvce.a8thmile.R;


public class MainActivity extends AppCompatActivity implements LoginView {


    private Button ProgressButton,signupbtn;
    private CircularProgressButton cb;
    private EditText phoneNumberEditText;
    private LoginPresenter mLoginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ProgressButton = (Button) findViewById(R.id.btnWithText);
        signupbtn = (Button)findViewById(R.id.signupbtn);
        cb = (CircularProgressButton) findViewById(R.id.btnWithText);
        phoneNumberEditText  = (EditText) findViewById(R.id.PhoneNumberLabel);

        mLoginPresenter = new LoginPresenterImpl(this);

        ProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // cb.setIndeterminateProgressMode(true);
             //   cb.setProgress(50);
                //API CALL HERE VIGNESH
              //  mLoginPresenter.validatePhoneNUmber(getPhoneNumber());
                Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
                /*
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        cb.setProgress(100);
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class );

                        startActivity(intent);
                    }
                }, 1000);

                */


            }

        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, signup.class );

                startActivity(intent);

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
    public void onValidationFailure() {

       Toast toast= Toast.makeText(this,"Phone number not valid",Toast.LENGTH_LONG);
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
        Toast.makeText(this,"Login Failed",Toast.LENGTH_LONG).show();
    }

    public String getPhoneNumber() {
        return phoneNumberEditText.getText().toString();
    }
}
