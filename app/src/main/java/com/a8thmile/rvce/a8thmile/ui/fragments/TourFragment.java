package com.a8thmile.rvce.a8thmile.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.ui.Activities.HomeActivity;
import com.a8thmile.rvce.a8thmile.ui.Adapters.ScheduleAdapter;


public class TourFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;
    public TourFragment() {
        // Required empty public constructor
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((HomeActivity)getActivity()).changeActionbar(30);
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_tour, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method


        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.pager);

        //Creating our pager adapter

        tabLayout.addTab(tabLayout.newTab().setText("24th Mar"));
        tabLayout.addTab(tabLayout.newTab().setText("25th Mar"));
        tabLayout.addTab(tabLayout.newTab().setText("26th Mar"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ScheduleAdapter adapter = new ScheduleAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
return  view;
    }

}
