package com.a8thmile.rvce.a8thmile.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a8thmile.rvce.a8thmile.R;

public class otp extends AppCompatActivity {
    private Button loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //LOT OF BACKEND HERE
        loginbtn = (Button) findViewById(R.id.login);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(otp.this, homepage2.class );
                        startActivity(intent);
                    }


            });

    }
}
