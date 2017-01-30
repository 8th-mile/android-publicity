package com.a8thmile.rvce.a8thmile.ui.Activities;

import android.graphics.drawable.ColorDrawable;

import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.events.register.RegisterView;
import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.models.EventResponse;
import com.a8thmile.rvce.a8thmile.models.MyEventResponse;
import com.a8thmile.rvce.a8thmile.ui.OnSwipeTouchListener;
import com.a8thmile.rvce.a8thmile.ui.RowItem;
import com.a8thmile.rvce.a8thmile.ui.Adapters.SubEventAdapter;
import com.daprlabs.aaron.swipedeck.SwipeDeck;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class SubEventActivity extends AppCompatActivity implements RegisterView {
    private List<RowItem> rowItems;
    private static Integer[] images={
            R.drawable.event1,
            R.drawable.event2,
            R.drawable.event3,
            R.drawable.event4
    };
    private List<EventFields> eventFields;
    private List<EventFields> copyEventFields;
    private String token;
    private SwipeDeck cardStack;
    private String id;
    private ProgressBar spinner;


    /*public SubEventAdapter adapter;
    public String[] descriptions;
    public String[] times;
    public int i,j=0;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_event);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        copyEventFields=new ArrayList<EventFields>();
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF702F64));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setElevation(0);


        eventFields=getIntent().getExtras().getParcelableArrayList("subevents");
        for(int i=0;i<eventFields.size();i++)
        {
            copyEventFields.add(i,eventFields.get(i));
        }

        id=getIntent().getStringExtra("user_id");
        token=getIntent().getStringExtra("token");
        getSupportActionBar().setTitle(getIntent().getStringExtra("category"));
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);
        // Log.v("test","inside "+eventFields.get(0).getName());

      //  ListView lv = (ListView) findViewById(R.id.myList);
       /* rowItems = new ArrayList<RowItem>();
        String[] times = {"26 Mar @8pm","27 Mar @11am","27 Mar @2.30pm","28 Mar @7pm"};
        String[] descriptions = {"Coke Studio","Hackathon","Some Event","Party"};
       for (int i = 0; i < times.length; i++) {
            RowItem item = new RowItem(images[i], times[i], descriptions[i]);
            rowItems.add(item);
        }*/
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
