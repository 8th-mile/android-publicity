package com.a8thmile.rvce.a8thmile.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.events.register.RegisterPresenter;
import com.a8thmile.rvce.a8thmile.events.register.RegisterPresenterImpl;
import com.a8thmile.rvce.a8thmile.events.register.RegisterView;
import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.models.EventResponse;
import com.a8thmile.rvce.a8thmile.models.MyEventResponse;
import com.a8thmile.rvce.a8thmile.ui.Activities.HomeActivity;
import com.a8thmile.rvce.a8thmile.ui.Adapters.MyEventsAdapter;
import com.a8thmile.rvce.a8thmile.ui.Adapters.WishListAdapter;

import java.util.ArrayList;
import java.util.List;


public class MyEventsFragment extends Fragment implements RegisterView{
    private List<Integer> responseEvents;
    private List<EventFields> events;
    private List<EventFields> myEvents;
    private String token;
    private String id;
    private ListView listView;
    private MyEventsAdapter myEventsAdapter;
    private LinearLayout empty;
private RegisterPresenter registerPresenter;
    private ProgressBar spinner;

    public MyEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_events, container, false);
        ((HomeActivity)getActivity()).changeActionbar(30);
        responseEvents=new ArrayList<Integer>();
        myEvents=new ArrayList<EventFields>();
        empty=(LinearLayout) view.findViewById(R.id.empty);
        listView=(ListView)view.findViewById(R.id.myList);
        spinner=(ProgressBar)view.findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        registerPresenter=new RegisterPresenterImpl(this);
    return view;
    }

    @Override
    public void registered(String message) {
        //irrelevent to this fragment
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        token=((HomeActivity)getActivity()).getToken();
        id=((HomeActivity)getActivity()).getId();
        events=((HomeActivity)getActivity()).getEvents();
        registerPresenter.myEventsListGet(id,token);

    }

    @Override
    public void RegisterFailed(String message) {

        spinner.setVisibility(View.GONE);
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void wishListGot(EventResponse eventResponse) {
//irrelevent
    }

    @Override
    public void MyEventListGot(MyEventResponse eventResponse) {
        spinner.setVisibility(View.GONE);
            responseEvents=eventResponse.getRegistered_events();
        Log.v("test","ne "+responseEvents);
        if(responseEvents.size()!=0)
        {
            empty.setVisibility(View.GONE);
        }

           for(EventFields e:events)
           {
               for(int i: responseEvents)
               {
                   if(Integer.parseInt(e.getId())==i)
                   {
                       myEvents.add(e);
                       break;
                   }
               }
           }
        myEventsAdapter = new MyEventsAdapter(getContext(), R.layout.myevents_card, myEvents,token,id);
        listView.setAdapter(myEventsAdapter);

    }
}
