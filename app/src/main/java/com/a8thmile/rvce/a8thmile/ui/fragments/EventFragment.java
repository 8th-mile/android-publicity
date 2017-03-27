package com.a8thmile.rvce.a8thmile.ui.fragments;


import android.database.SQLException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import github.chenupt.springindicator.SpringIndicator;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.models.EventDb;
import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.ui.Activities.HomeActivity;
import com.a8thmile.rvce.a8thmile.ui.Adapters.EventAdapter;
import com.a8thmile.rvce.a8thmile.ui.EventItem;
import com.a8thmile.rvce.a8thmile.ui.ExtraDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class EventFragment extends Fragment {
    private List<EventItem> eventItems;
    private List<EventFields> eventFields;
    private List<EventFields> cultural;
    private List<EventFields> technical;
    private List<EventFields> informal;
    private List<EventFields> photography;
    private List<EventFields> engaging=new ArrayList<EventFields>()
    {
        {

            add(new EventFields("123","INDI-GENIOUS QUIZ","",8,"Rs 50 per team","-","1 to 3 members per team","9000 Rs"
                    ,"7500 Rs \n3rd Prize\n 6000 Rs","Anirudh S R","Siddharth Achar","8762688994","9972471897",R.drawable.utpt));
            add(new EventFields("124","SCI-TECH QUIZ","",8,"Rs 50 per team","-","1 to 3 members per team","9000 Rs"
                    ,"7500 Rs \n3rd Prize\n 6000 Rs","Anirudh S R","Siddharth Achar","8762688994","9972471897",R.drawable.utpt));
            add(new EventFields("125","SPORTS QUIZ","",8,"Rs 50 per team","-","1 to 3 members per team","9000 Rs"
                    ,"7500 Rs \n3rd Prize\n 6000 Rs","Anirudh S R","Siddharth Achar","8762688994","9972471897",R.drawable.utpt));
            add(new EventFields("126","LIEUT-EN-ENT QUIZ","",8,"Rs 50 per team","-","1 to 3 members per team","9000 Rs"
                    ,"7500 Rs \n3rd Prize\n 6000 Rs","Anirudh S R","Siddharth Achar","8762688994","9972471897",R.drawable.utpt));
            add(new EventFields("127","GENERAL QUIZ","",8,"Rs 50 per team","-","1 to 3 members per team","11000 Rs"
                    ,"9500 Rs \n3rd Prize\n 8000 Rs","Anirudh S R","Siddharth Achar","8762688994","9972471897",R.drawable.utpt));
            add(new EventFields("128","OPEN QUIZ","",8,"Rs 50 per team","-","1 to 3 members per team","18000 Rs"
                    ,"15000 Rs \n3rd Prize\n 12000 Rs","Anirudh S R","Siddharth Achar","8762688994","9972471897",R.drawable.utpt));



        }
    };
    private List<EventFields> sports;
    private List<EventFields> flagship;
    private List<EventFields> fine_arts;
    private List<EventFields> literary;


    private String token;
    private String user_id;

    private HashMap<Integer,List<EventFields>> eventMap;
    private HashMap<Integer,String> eventCategory;
    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    SpringIndicator springIndicator;


    private static int[] images=
            {

            R.drawable.technical,
            R.drawable.cultural,
            R.drawable.sports,
            R.drawable.informal,
            R.drawable.arts,
            R.drawable.literary,
            R.drawable.photography,
            R.drawable.utpt,
            R.drawable.flagship,
            };


    public EventFragment() {
        // Required empty public constructor
    }
public  void changeToolbarColor(int position)
{
    ((HomeActivity)getActivity()).changeActionbar(position);
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

        Log.v("test","hetere");
        technical=new ArrayList<EventFields>();
        cultural=new ArrayList<EventFields>();
        informal=new ArrayList<EventFields>();
        flagship=new ArrayList<EventFields>();
        sports=new ArrayList<EventFields>();
        photography=new ArrayList<EventFields>();
        literary =new ArrayList<EventFields>();
        fine_arts=new ArrayList<EventFields>();

        eventCategory=new HashMap<Integer, String>();
        eventMap=new HashMap<Integer, List<EventFields>>();
        eventMap.put(0,technical);
        eventCategory.put(0,"Technical Events");
        eventMap.put(1,cultural);
        eventCategory.put(1,"Cultural Events");
        eventMap.put(2,sports);
        eventCategory.put(2,"Sports and Gaming Events");
        eventMap.put(3,informal);
        eventCategory.put(3,"Informal Events");
        eventMap.put(4,fine_arts);
        eventCategory.put(4,"Fine Arts Events");
        eventMap.put(5,literary);
        eventCategory.put(5,"Literary Events");
        eventMap.put(6,photography);
        eventCategory.put(6,"Photography Events");
        eventMap.put(7,engaging);
        eventCategory.put(7,"Under the Peepal Tree");
        eventMap.put(8,flagship);
        eventCategory.put(8,"FlagShip Events");
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //ListView lv = (ListView) view.findViewById(R.id.eventList);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        springIndicator = (SpringIndicator) view.findViewById(R.id.indicator);
        eventItems = new ArrayList<EventItem>();
        int[] backColId = {R.drawable.gradient,R.drawable.gradient3,R.drawable.gradient4,R.drawable.gradient5,R.drawable.gradient6,R.drawable.gradient7,R.drawable.gradient8,R.drawable.gradient9,R.drawable.gradient10};
        final String[] titles = {"TECHNICAL","CULTURAL","SPORTS AND GAMING","INFORMAL","FINE ARTS","LITERARY","PHOTOGRAPHY","","FLAGSHIP"};
        for(int i=0;i<titles.length;i++)
        {
            EventItem eventItem=new EventItem(backColId[i],images[i],titles[i]);
            eventItems.add(eventItem);
        }


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //eventFields=((HomeActivity)getActivity()).getEvents();
        token=((HomeActivity)getActivity()).getToken();
        user_id=((HomeActivity)getActivity()).getId();
        EventDb eventDb=new EventDb(getContext());
        try {

            eventDb.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            eventDb.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }
        Log.v("newone","start");
        eventFields=eventDb.getEvents();
        for(EventFields e : eventFields)
        {
           Log.v("newone",e.getName());
        }


        if(eventFields!=null)
        splitEventList(eventFields);

        mPagerAdapter = new EventAdapter(getContext(),getChildFragmentManager(),R.layout.event_list_item,eventItems,eventMap,token,user_id,eventCategory,this);
        mPager.setAdapter(mPagerAdapter);

        //mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        springIndicator.setViewPager(mPager);

    }
    public void splitEventList(List<EventFields> eventFields) {
        for (EventFields event:eventFields)
        {
            Log.v("test","id "+event.getId());
            switch (event.getType())
            {
                case 1:



                        technical.add(event);
                    break;
                case 2:

                    cultural.add(event);
                    break;
                case 3:

                    sports.add(event);
                    break;
                case 4:

                    informal.add(event);
                    break;
                case 5:

                    literary.add(event);
                    break;
                case 6:

                    photography.add(event);
                    break;
                case 7:

                    fine_arts.add(event);
                    break;
                case 8:

                    engaging.add(event);
                    break;
                case 9:

                    flagship.add(event);
                    break;

            }
        }
    }


    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            }
            else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            }
            else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }



}
