package com.a8thmile.rvce.a8thmile.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.ui.Activities.HomeActivity;
import com.a8thmile.rvce.a8thmile.ui.Activities.SubEventActivity;
import com.a8thmile.rvce.a8thmile.ui.Adapters.EventAdapter;
import com.a8thmile.rvce.a8thmile.ui.EventItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class EventFragment extends Fragment {
    private List<EventItem> eventItems;
    private List<EventFields> eventFields;
    private List<EventFields> cultural;
    private List<EventFields> technical;
    private List<EventFields> informal;
    private List<EventFields> proshows;
    private List<EventFields> flagship;
    private List<EventFields> workshops;

    private String token;
    private String user_id;

    private HashMap<Integer,List<EventFields>> eventMap;
    private HashMap<Integer,String> eventCategory;

    private static int[] images=
            {

            R.drawable.technical,
            R.drawable.cultural,
            R.drawable.informal,
            R.drawable.proshow,
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
        technical=new ArrayList<EventFields>();
        cultural=new ArrayList<EventFields>();
        informal=new ArrayList<EventFields>();
        flagship=new ArrayList<EventFields>();
        workshops=new ArrayList<EventFields>();
        proshows=new ArrayList<EventFields>();
        eventCategory=new HashMap<Integer, String>();
        eventMap=new HashMap<Integer, List<EventFields>>();
        eventMap.put(0,technical);
        eventCategory.put(0,"Technical Events");
        eventMap.put(1,cultural);
        eventCategory.put(1,"Cultural Events");
        eventMap.put(2,informal);
        eventCategory.put(2,"Informal Events");
        eventMap.put(3,proshows);
        eventCategory.put(3,"Pro Shows");
        eventMap.put(4,flagship);
        eventCategory.put(4,"FlagShip Events");
        eventMap.put(5,workshops);
        eventCategory.put(5,"Workshops");

        ListView lv = (ListView) view.findViewById(R.id.eventList);
        eventItems = new ArrayList<EventItem>();
        int[] backColId = {R.color.cultural,R.color.flagship,R.color.proshows,R.color.informal,R.color.technical,R.color.workshop};
        final String[] titles = {"TECHNICAL","CULTURAL","INFORMAL","PRO SHOWS","FLAGSHIP","WORKSHOPS"};
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
               Intent subIntent=new Intent(getActivity(), SubEventActivity.class);
                Bundle bundle=new Bundle();
               bundle.putParcelableArrayList("subevents", (ArrayList<? extends Parcelable>) eventMap.get(i));
               subIntent.putExtras(bundle);
               subIntent.putExtra("category",eventCategory.get(i));
               subIntent.putExtra("user_id",user_id);
               subIntent.putExtra("token",token);
               startActivity(subIntent);
           }
       });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eventFields=((HomeActivity)getActivity()).getEvents();
        token=((HomeActivity)getActivity()).getToken();
        user_id=((HomeActivity)getActivity()).getId();
        splitEventList(eventFields);
    }
    public void splitEventList(List<EventFields> eventFields) {
        for (EventFields event:eventFields)
        {
            switch (event.getType())
            {
                case 1:
                    technical.add(event);
                    break;
                case 2:
                    cultural.add(event);
                    break;
                case 3:
                    informal.add(event);
                    break;
                case 4:
                    proshows.add(event);
                    break;
                case 5:
                    flagship.add(event);
                    break;
                case 6:
                    workshops.add(event);
                    break;

            }
        }
    }
}
