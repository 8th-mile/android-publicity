package com.a8thmile.rvce.a8thmile.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a8thmile.rvce.a8thmile.R;

public class signup extends AppCompatActivity {
    private Button otpbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        otpbtn = (Button) findViewById(R.id.btnWithText);
        otpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get OTP progress bar thing to be done here too
                Intent intent = new Intent(signup.this, otp.class );

                startActivity(intent);

            }
        });
    }
}
