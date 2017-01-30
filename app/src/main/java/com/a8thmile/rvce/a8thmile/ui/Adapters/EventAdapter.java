package com.a8thmile.rvce.a8thmile.ui.Adapters;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.ui.Activities.HomeActivity;
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



    @Override
    public Fragment getItem(int position) {
      //  ViewHolder holder;

        eventFragment.changeToolbarColor(position);

        return(SliderFragment.newInstance(position,eventList.get(position),eventFields.get(position),
                token,user_id,category.get(position)));
      /*  if (convertView == null){
            convertView = mInflater.inflate(R.layout.event_list_item, null);
            //Log.v("test","hey "+holder);
            holder=new ViewHolder();
            holder.image=(ImageView) convertView.findViewById(R.id.eventImage);
            holder.title=(TextView) convertView.findViewById(R.id.eventTitle) ;
            holder.linearLayout=(LinearLayout)convertView.findViewById(R.id.eventLayout);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder)convertView.getTag();

        holder.image.setImageResource(eventItem.getIconId());
        holder.title.setText(eventItem.getTitle());
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Pacifico.ttf");
        holder.title.setTypeface(font);
        holder.linearLayout.setBackgroundResource(eventItem.getBackColId());

        convertView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350, context.getResources().getDisplayMetrics() )));
        return null;*/
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
