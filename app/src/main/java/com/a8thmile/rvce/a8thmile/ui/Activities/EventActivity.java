package com.a8thmile.rvce.a8thmile.ui.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a8thmile.rvce.a8thmile.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EventActivity extends AppCompatActivity implements View.OnClickListener{
    private String eventName;
    private String eventDesc;
    private String eventRules;
    private String eventDate;
    private String eventFees;
    private String eventPrize1;
    private String eventPrize2;
    private String coord1;
    private String coord2;
    private String cph1;
    private String cph2;

    private Toolbar toolbar;
    private TextView aboutText;
    private TextView rulesText;
    private TextView dateText;
    private TextView firstPrizeText;
    private TextView firstPrizeLabel;
    private TextView secondPrizeText;
    private TextView secondPrizeLabel;
    private TextView priceText;
    private TextView cord1;
    private TextView cord2;
    private Button call1;
    private Button call2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.95);
        int screenHeight = (int) (metrics.heightPixels * 0.87);
        getWindow().setLayout(screenWidth, screenHeight);
        aboutText = (TextView) findViewById(R.id.about);
        dateText = (TextView) findViewById(R.id.date);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        priceText = (TextView) findViewById(R.id.price);
        rulesText = (TextView) findViewById(R.id.rules);
        firstPrizeText=(TextView)findViewById(R.id.first);
        secondPrizeText=(TextView)findViewById(R.id.second);
        firstPrizeLabel=(TextView)findViewById(R.id.firstText) ;
        secondPrizeLabel=(TextView)findViewById(R.id.secondText);
        cord1=(TextView)findViewById(R.id.onecoord);
        cord2=(TextView)findViewById(R.id.secondcoord);
        call1=(Button)findViewById(R.id.call1);
        call2=(Button)findViewById(R.id.call2);
        call2.setOnClickListener(this);
        call1.setOnClickListener(this);

        eventName = getIntent().getStringExtra("name");
        eventDesc = getIntent().getStringExtra("about");
        eventRules = getIntent().getStringExtra("rules");
        eventDate = getIntent().getStringExtra("date");
        eventFees = getIntent().getStringExtra("price");
        eventPrize1 = getIntent().getStringExtra("first");
        eventPrize2 = getIntent().getStringExtra("second");
        coord1=getIntent().getStringExtra("c1");
        coord2=getIntent().getStringExtra("c2");
        cph1=getIntent().getStringExtra("cph1");
        cph2=getIntent().getStringExtra("cph2");


        if(eventName.equals("Mr. and Ms. 8th mile")) {
            firstPrizeLabel.setText("Mr 8th Mile");
            secondPrizeLabel.setText("Ms 8th Mile");
        }
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Exo2-Bold.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(eventName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if(eventDesc==null)
            eventDesc="-";
        aboutText.setText(eventDesc);
        if(eventRules==null)
            eventRules="-";
        rulesText.setText(eventRules);
        if(eventDate==null)
            eventDate="Not yet announced";
        dateText.setText(eventDate);
        firstPrizeText.setText(eventPrize1);
        secondPrizeText.setText(eventPrize2);
        if(eventFees==null)
            priceText.setText("Not yet confirmed");
        else
        priceText.setText(eventFees);
        cord1.setText(coord1);
        cord2.setText(coord2);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


            // On clicking the back arrow
            // Discard any changes

                onBackPressed();
                return true;

        }
    public void call(String phoneNo) {
        int permissionCheck = ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    123);
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+phoneNo)));
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.call1:
                call(cph1);
                break;
            case R.id.call2:
                call(cph2);
                break;
        }
    }
}


