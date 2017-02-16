package com.a8thmile.rvce.a8thmile.ui.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a8thmile.rvce.a8thmile.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class WelcomeActivity extends Activity {
    SharedPreferences pref;
    Button dontShow,skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/Exo2-Bold.otf");

         pref= getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(pref.getBoolean("activity_executed", false)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        dontShow=(Button)findViewById(R.id.dontshow);
        dontShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor ed = pref.edit();
                ed.putBoolean("activity_executed", true);
                ed.commit();
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        dontShow.setTypeface(face);
         skip=(Button)findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent skipIntent=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(skipIntent);
            }
        });
        skip.setTypeface(face);
    }
}
