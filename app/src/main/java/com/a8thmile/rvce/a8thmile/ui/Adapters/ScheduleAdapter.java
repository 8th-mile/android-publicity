package com.a8thmile.rvce.a8thmile.ui.Adapters;

/**
 * Created by vignesh on 22/3/17.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.a8thmile.rvce.a8thmile.ui.fragments.DayOneFragment;
import com.a8thmile.rvce.a8thmile.ui.fragments.DayTwoFragment;

import com.a8thmile.rvce.a8thmile.ui.fragments.DayThreeFragment;

/**
 * Created by Belal on 2/3/2016.
 */
//Extending FragmentStatePagerAdapter
public class ScheduleAdapter extends FragmentStatePagerAdapter {
    private String[] tabTitles = new String[]{"24th Mar", "25th Mar", "26th Mar"};
    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public ScheduleAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                DayOneFragment tab1 = new DayOneFragment();
                return tab1;
            case 1:
                DayTwoFragment tab2 = new DayTwoFragment();
                return tab2;
            case 2:
                DayThreeFragment tab3 = new DayThreeFragment();
                return tab3;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}