package com.a8thmile.rvce.a8thmile.ui;

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

import com.dd.CircularProgressButton;

import com.a8thmile.rvce.a8thmile.R;


public class MainActivity extends AppCompatActivity {
    private Button ProgressButton,signupbtn;
    private CircularProgressButton cb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ProgressButton = (Button) findViewById(R.id.btnWithText);
        signupbtn = (Button)findViewById(R.id.signupbtn);
        cb = (CircularProgressButton) findViewById(R.id.btnWithText);
        ProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb.setIndeterminateProgressMode(true);
                cb.setProgress(50);
                //API CALL HERE VIGNESH
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        cb.setProgress(100);
                        Intent intent = new Intent(MainActivity.this, otp.class );

                        startActivity(intent);
                    }
                }, 1000);




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
}
