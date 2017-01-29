package com.a8thmile.rvce.a8thmile.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
private RegisterPresenter registerPresenter;

    public MyEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_events, container, false);
        responseEvents=new ArrayList<Integer>();
        myEvents=new ArrayList<EventFields>();
        listView=(ListView)view.findViewById(R.id.myList);
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
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void wishListGot(EventResponse eventResponse) {
//irrelevent
    }

    @Override
    public void MyEventListGot(MyEventResponse eventResponse) {
            responseEvents=eventResponse.getRegistered_events();
        Log.v("test","list "+responseEvents);
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
