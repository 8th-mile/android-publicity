package com.a8thmile.rvce.a8thmile.ui.Activities;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.events.register.RegisterView;
import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.models.EventResponse;
import com.a8thmile.rvce.a8thmile.ui.RowItem;
import com.a8thmile.rvce.a8thmile.ui.Adapters.SubEventAdapter;
import com.daprlabs.aaron.swipedeck.SwipeDeck;

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
    private String id;
    private SwipeDeck cardStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_event);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF702F64));
        getSupportActionBar().setElevation(0);
        copyEventFields=new ArrayList<EventFields>();
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
        SubEventAdapter adapter = new SubEventAdapter(this, R.layout.event_card, eventFields,token,id);
       // lv.setAdapter(adapter);
        cardStack.setAdapter(adapter);
        cardStack.bringToFront();
        cardStack.setCallback(new SwipeDeck.SwipeDeckCallback() {
            @Override
            public void cardSwipedLeft(long stableId) {
                Log.i("MainActivity", "card was swiped left, position in adapter: " );
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
                Log.i("MainActivity", "card was swiped right, position in adapter: " );
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
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void RegisterFailed(String message) {
        Toast.makeText(this,"Registration Failed. "+message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void wishListGot(EventResponse eventResponse) {
        //Irrelevant to this activity
    }
}
