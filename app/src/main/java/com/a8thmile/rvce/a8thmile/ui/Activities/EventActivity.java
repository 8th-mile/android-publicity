package com.a8thmile.rvce.a8thmile.ui.Activities;

import android.content.pm.ProviderInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.TextView;

import com.a8thmile.rvce.a8thmile.R;

public class EventActivity extends AppCompatActivity {
    private String eventName;
    private String eventDesc;
    private String eventRules;
    private String eventDate;
    private String eventFees;
    private String eventPrize1;
    private String eventPrize2;

    private Toolbar toolbar;
    private TextView aboutText;
    private TextView rulesText;
    private TextView dateText;
    private TextView firstPrizeText;
    private TextView secondPrizeText;
    private TextView priceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.95);
        int screenHeight = (int) (metrics.heightPixels * 0.80);
        getWindow().setLayout(screenWidth, screenHeight);
        aboutText = (TextView) findViewById(R.id.about);
        dateText = (TextView) findViewById(R.id.date);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        priceText = (TextView) findViewById(R.id.price);
        rulesText = (TextView) findViewById(R.id.rules);


        eventName = getIntent().getStringExtra("name");
        eventDesc = getIntent().getStringExtra("desc");
        eventRules = getIntent().getStringExtra("rules");
        eventDate = getIntent().getStringExtra("date");
        eventFees = getIntent().getStringExtra("price");
        eventPrize1 = getIntent().getStringExtra("first");
        eventPrize2 = getIntent().getStringExtra("second");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(eventName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        aboutText.setText("Come show us the best way to disguise yourself with the perfect blend of colours. Let the world see how you can transform a face canvas into a piece of art.");
        rulesText.setText("1. Theme will be given on spot.\n" +
                "2. Only two members allowed per team (One member will paint on the other member's face). \n" +
                "3. The participant gets 2 hours in total to finish the design.\n" +
                "4. The basic required items like brush and paint(basic colours) will be provided . ");
        dateText.setText(eventDate);

        priceText.setText(eventFees);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


            // On clicking the back arrow
            // Discard any changes

                onBackPressed();
                return true;

        }
    }
