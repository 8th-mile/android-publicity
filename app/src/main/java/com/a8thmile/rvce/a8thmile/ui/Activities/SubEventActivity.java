package com.a8thmile.rvce.a8thmile.ui.Activities;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.events.register.RegisterView;
import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.models.EventResponse;
import com.a8thmile.rvce.a8thmile.models.MyEventResponse;
import com.a8thmile.rvce.a8thmile.ui.RowItem;
import com.a8thmile.rvce.a8thmile.ui.Adapters.SubEventAdapter;
import com.daprlabs.aaron.swipedeck.SwipeDeck;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class SubEventActivity extends AppCompatActivity implements RegisterView {
    private List<RowItem> rowItems;
    private static Integer[] images={
            R.drawable.proshow1,
            R.drawable.proshow2,
            R.drawable.proshow3,
            R.drawable.proshow3
    };
    private List<EventFields> eventFields;
    private List<EventFields> copyEventFields;
    private String token;
    private SwipeDeck cardStack;
    private String id;
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_event);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Exo2-Bold.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        copyEventFields=new ArrayList<EventFields>();
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/Exo2-Bold.otf");


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF702F64));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.subevent_title_bar, null);

        TextView title=((TextView)v.findViewById(R.id.title));
        title.setText(getIntent().getStringExtra("category"));
        title.setTypeface(face);

        getSupportActionBar().setCustomView(v);
        getSupportActionBar().setElevation(0);


        eventFields=getIntent().getExtras().getParcelableArrayList("subevents");
        for(int i=0;i<eventFields.size();i++)
        {
            copyEventFields.add(i,eventFields.get(i));
        }

        id=getIntent().getStringExtra("user_id");
        token=getIntent().getStringExtra("token");
      //  getSupportActionBar().setTitle(getIntent().getStringExtra("category"));

        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        SubEventAdapter adapter = new SubEventAdapter(this, R.layout.event_card, eventFields,token,id,spinner);
       // lv.setAdapter(adapter);
        cardStack.setAdapter(adapter);
        cardStack.bringToFront();

        cardStack.setCallback(new SwipeDeck.SwipeDeckCallback() {
            @Override
            public void cardSwipedLeft(long stableId) {


              /*  for (int i = 0; i < times.length; i++) {
                    RowItem item = new RowItem(images[i], times[i], descriptions[i]);
                    rowItems.add(item);
                }*/
               for(int i=0;i<copyEventFields.size();i++)
                {
                    eventFields.add(copyEventFields.get(i));
                }
                synchronized (cardStack){
                    cardStack.notify();

                }
            }

            @Override
            public void cardSwipedRight(long stableId) {


            /*    for (int i = 0; i < times.length; i++) {
                    RowItem item = new RowItem(images[i], times[i], descriptions[i]);
                    rowItems.add(item);
                }*/
                for(int i=0;i<copyEventFields.size();i++)
                {
                    eventFields.add(copyEventFields.get(i));

                }
                synchronized (cardStack){
                    cardStack.notify();
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void registered(String message) {
        spinner.setVisibility(View.GONE);
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void RegisterFailed(String message) {
        spinner.setVisibility(View.GONE);
        Toast.makeText(this,"Failed. "+message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void wishListGot(EventResponse eventResponse) {
        //Irrelevant to this activity
    }

    @Override
    public void MyEventListGot(MyEventResponse eventResponse) {

    }
}
