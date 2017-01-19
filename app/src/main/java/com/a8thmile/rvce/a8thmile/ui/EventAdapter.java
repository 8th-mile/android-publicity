package com.a8thmile.rvce.a8thmile.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a8thmile.rvce.a8thmile.R;

import java.util.List;

/**
 * Created by vignesh on 19/1/17.
 */

public class EventAdapter extends ArrayAdapter<EventItem> {
    Context context;
    
    public EventAdapter(Context context, int resource, List<EventItem> eventItems) {
        super(context, resource,eventItems);
        this.context=context;
    }
    public  class ViewHolder{
        ImageView image;
        TextView title;
        LinearLayout linearLayout;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        EventItem eventItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
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
        convertView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,500 ));
        return  convertView;
    }
}
