package com.a8thmile.rvce.a8thmile.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.ui.LazyAdapter;
import com.a8thmile.rvce.a8thmile.ui.RowItem;

import java.util.ArrayList;
import java.util.List;

public class SubEventActivity extends AppCompatActivity {
    private List<RowItem> rowItems;
    private static Integer[] images={
            R.drawable.event1,
            R.drawable.event2,
            R.drawable.event3,
            R.drawable.event4
    };;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_event);
        ListView lv = (ListView) findViewById(R.id.myList);
        rowItems = new ArrayList<RowItem>();
        String[] times = {"26 Mar @8pm","27 Mar @11am","27 Mar @2.30pm","28 Mar @7pm"};
        String[] descriptions = {"Coke Studio","Hackathon","Some Event","Party"};
        for (int i = 0; i < times.length; i++) {
            RowItem item = new RowItem(images[i], times[i], descriptions[i]);
            rowItems.add(item);
        }
        LazyAdapter adapter = new LazyAdapter(getBaseContext(), R.layout.event_card, rowItems);
        lv.setAdapter(adapter);

    }
}
