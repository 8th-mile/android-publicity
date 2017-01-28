package com.a8thmile.rvce.a8thmile.ui.Activities;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.events.register.RegisterView;
import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.models.EventResponse;
import com.a8thmile.rvce.a8thmile.ui.OnSwipeTouchListener;
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
    private String token;
    private SwipeDeck cardStack;
    private String id;
    public SubEventAdapter adapter;
    public String[] descriptions;
    public String[] times;
    public int i,j=0;
    public SwipeDeck cardstack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_event);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF702F64));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setElevation(0);

        eventFields=getIntent().getExtras().getParcelableArrayList("subevents");
        id=getIntent().getStringExtra("user_id");
        token=getIntent().getStringExtra("token");
        getSupportActionBar().setTitle(getIntent().getStringExtra("category"));
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);
        // Log.v("test","inside "+eventFields.get(0).getName());
        //ListView lv = (ListView) findViewById(R.id.myList);



        rowItems = new ArrayList<RowItem>();
        final String[] times = {"26 Mar @8pm","27 Mar @11am","27 Mar @2.30pm","28 Mar @7pm"};
        final String[] descriptions = {"Coke Studio","Hackathon","Some Event","Party"};
        for (int i = 0; i < times.length; i++) {
            RowItem item = new RowItem(images[i], times[i], descriptions[i]);
            rowItems.add(item);
        }
        SubEventAdapter adapter = new SubEventAdapter(this, R.layout.event_card, rowItems,token,id);

        cardStack.setAdapter(adapter);
        cardStack.bringToFront();
        /*Button btn3 = (Button) findViewById(R.id.button3);
        assert btn3 != null;
        btn3.setOnTouchListener(new OnSwipeTouchListener(SubEventActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(SubEventActivity.this, "top", Toast.LENGTH_SHORT).show();

            }
            public void onSwipeRight() {
                cardStack.swipeTopCardRight(180);

                for (int i = 0; i < times.length; i++) {
                    RowItem item = new RowItem(images[i], times[i], descriptions[i]);
                    rowItems.add(item);
                }
                cardStack.notify();

            }
            public void onSwipeLeft() {
                cardStack.swipeTopCardLeft(180);

                for (int i = 0; i < times.length; i++) {
                    RowItem item = new RowItem(images[i], times[i], descriptions[i]);
                    rowItems.add(item);
                }
                cardStack.notify();

            }
            public void onSwipeBottom() {
                Toast.makeText(SubEventActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });*/
        cardStack.setCallback(new SwipeDeck.SwipeDeckCallback() {
            @Override
            public void cardSwipedLeft(long stableId) {
                Log.i("MainActivity", "card was swiped left, position in adapter: " );
                for (int i = 0; i < times.length; i++) {
                    RowItem item = new RowItem(images[i], times[i], descriptions[i]);
                    rowItems.add(item);
                }
                synchronized (cardStack){
                cardStack.notify();
                }
            }

            @Override
            public void cardSwipedRight(long stableId) {
                Log.i("MainActivity", "card was swiped right, position in adapter: " );
                for (int i = 0; i < times.length; i++) {
                    RowItem item = new RowItem(images[i], times[i], descriptions[i]);
                    rowItems.add(item);
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
