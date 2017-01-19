package com.a8thmile.rvce.a8thmile.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.ui.RowItem;
import com.a8thmile.rvce.a8thmile.ui.LazyAdapter;

import java.util.ArrayList;
import java.util.List;

public class SubEvents extends Fragment {
    private List<RowItem> rowItems;
    private static Integer[] images={
            R.drawable.event1,
            R.drawable.event2,
            R.drawable.event3,
            R.drawable.event4
    };;

    public SubEvents() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sub_events, container, false);
        //  pixelDensity = getResources().getDisplayMetrics().density;
        ListView lv = (ListView) view.findViewById(R.id.myList);
        rowItems = new ArrayList<RowItem>();
        String[] times = {"26 Mar @8pm","27 Mar @11am","27 Mar @2.30pm","28 Mar @7pm"};
        String[] descriptions = {"Coke Studio","Hackathon","Some Event","Party"};
        for (int i = 0; i < times.length; i++) {
            RowItem item = new RowItem(images[i], times[i], descriptions[i]);
            rowItems.add(item);
        }
        LazyAdapter adapter = new LazyAdapter(getContext(), R.layout.event_card, rowItems);
        lv.setAdapter(adapter);
        return view;
    }




}
