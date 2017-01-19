package com.a8thmile.rvce.a8thmile.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.ui.SubEventActivity;
import com.a8thmile.rvce.a8thmile.ui.EventAdapter;
import com.a8thmile.rvce.a8thmile.ui.EventItem;

import java.util.ArrayList;
import java.util.List;


public class EventFragment extends Fragment {
    private List<EventItem> eventItems;
    private static int[] images=
            {
            R.drawable.cultural,
            R.drawable.technical,
            R.drawable.proshow,
            R.drawable.informal,
            R.drawable.flagship,
            R.drawable.workshops
    };


    public EventFragment() {
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
        View view= inflater.inflate(R.layout.fragment_event, container, false);
        ListView lv = (ListView) view.findViewById(R.id.eventList);
        eventItems = new ArrayList<EventItem>();
        int[] backColId = {R.color.cultural,R.color.technical,R.color.proshows,R.color.informal,R.color.flagship,R.color.workshop};
        String[] titles = {"CULTURAL","TECHNICAL","PRO SHOWS","INFORMAL","FLAGSHIP","WORKSHOPS"};
        for(int i=0;i<titles.length;i++)
        {
            EventItem eventItem=new EventItem(backColId[i],images[i],titles[i]);
            eventItems.add(eventItem);
        }

        EventAdapter eventAdapter=new EventAdapter(getContext(),R.layout.event_list_item,eventItems);
        lv.setAdapter(eventAdapter);
       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Log.v("test","hey "+i+" "+l);
               Intent cultureIntent=new Intent(getActivity(), SubEventActivity.class);
               startActivity(cultureIntent);
           }
       });
        return view;
    }

}
