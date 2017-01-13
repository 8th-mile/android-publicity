package com.a8thmile.rvce.a8thmile.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.a8thmile.rvce.a8thmile.R;

public class otp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
