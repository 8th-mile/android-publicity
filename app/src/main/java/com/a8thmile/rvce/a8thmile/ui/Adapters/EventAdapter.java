package com.a8thmile.rvce.a8thmile.ui.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.ui.EventItem;
import com.a8thmile.rvce.a8thmile.ui.fragments.EventFragment;
import com.a8thmile.rvce.a8thmile.ui.fragments.SliderFragment;

import java.util.HashMap;
import java.util.List;

/**
 * Created by vignesh on 19/1/17.
 */

public class EventAdapter extends FragmentPagerAdapter {
    public Context context;
    List<EventItem> eventList;
    HashMap<Integer,List<EventFields>> eventFields;
    String token;
    String user_id;
    HashMap<Integer,String> category;
    EventFragment eventFragment;
    public EventAdapter(Context context, android.support.v4.app.FragmentManager fragmentManager, int resource,
                        List<EventItem> eventItems, HashMap<Integer,List<EventFields>> eventFields, String token,
                        String user_id, HashMap<Integer,String> category, EventFragment eventFragment) {
        super(fragmentManager);
        this.context=context;
        this.eventList=eventItems;
        this.eventFields=eventFields;
        this.token=token;
        this.user_id=user_id;
        this.category=category;
        this.eventFragment=eventFragment;
    }

    public void changeToolbarColor(int position)
    {
        eventFragment.changeToolbarColor(position);

    }


    @Override
    public Fragment getItem(int position) {
      //  ViewHolder holder;

        return(SliderFragment.newInstance(this,position,eventList.get(position),eventFields.get(position),
                token,user_id,category.get(position)));

    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    public  class ViewHolder{
        ImageView image;
        TextView title;
        LinearLayout linearLayout;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        return  convertView;
    }
}
