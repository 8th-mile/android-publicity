package com.a8thmile.rvce.a8thmile;

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
        cb = (CircularProgressButton) findViewById(R.id.btnWithText);
        ProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb.setIndeterminateProgressMode(true);
                cb.setProgress(50);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        cb.setProgress(100);
                    }
                }, 5000);
                Intent intent = new Intent(MainActivity.this, otp.class );

                startActivity(intent);


            }
        });

    }
}

