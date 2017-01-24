package com.a8thmile.rvce.a8thmile.ui.Adapters;

/**
 * Created by vignesh on 22/1/17.
 */

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a8thmile.rvce.a8thmile.R;

public class ContactUsAdapter extends BaseExpandableListAdapter {
    private Context c;
    private ArrayList<Team_ContactUs> team;
    private LayoutInflater inflater;
    public ContactUsAdapter(Context c,ArrayList<Team_ContactUs> team)
    {
        this.c=c;
        this.team=team;
        inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    //GET A SINGLE PLAYER
    @Override
    public Object getChild(int groupPos, int childPos) {
        // TODO Auto-generated method stub
        return team.get(groupPos).contacts.get(childPos);
    }
    //GET PLAYER ID
    @Override
    public long getChildId(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return 0;
    }
    //GET PLAYER ROW
    @Override
    public View getChildView(int groupPos, int childPos, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        //ONLY INFLATER XML ROW LAYOUT IF ITS NOT PRESENT,OTHERWISE REUSE IT
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.contactlist_contact_us, null);
        }
        //GET CHILD/PLAYER NAME
        String  child=(String) getChild(groupPos, childPos);
        //SET CHILD NAME
        TextView nameTv=(TextView) convertView.findViewById(R.id.textView1);
        ImageView img=(ImageView) convertView.findViewById(R.id.imageView1);
        nameTv.setText(child);
        //GET TEAM NAME
        String teamName= getGroup(groupPos).toString();
        //ASSIGN IMAGES TO contacts ACCORDING TO THEIR NAMES AN TEAMS
        if(teamName=="Conveniers")
        {
            if(child=="Convenier1")
            {
                //mg.setImageResource(R.drawable.ic_launcher)  ;
            }else if(child=="Convenier2")
            {
                //img.setImageResource(R.drawable.ic_launcher)  ;
            }else if(child=="Convenier3")
            {
                //img.setImageResource(R.drawable.ic_launcher)  ;
            }
        }else if(teamName=="Technical Head")
        {
            if(child=="TechHead1")
            {
                //img.setImageResource(R.drawable.ic_launcher)  ;
            }else if(child=="TechHead2")
            {
                //img.setImageResource(R.drawable.ic_launcher)  ;
            }else if(child=="TechHead3")
            {
                //img.setImageResource(R.drawable.ic_launcher)  ;
            }
        }
        return convertView;
    }
    //GET NUMBER OF contacts
    @Override
    public int getChildrenCount(int groupPosw) {
        // TODO Auto-generated method stub
        return team.get(groupPosw).contacts.size();
    }
    //GET TEAM
    @Override
    public Object getGroup(int groupPos) {
        // TODO Auto-generated method stub
        return team.get(groupPos);
    }
    //GET NUMBER OF TEAMS
    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return team.size();
    }
    //GET TEAM ID
    @Override
    public long getGroupId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
    //GET TEAM ROW
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //ONLY INFLATE XML TEAM ROW MODEL IF ITS NOT PRESENT,OTHERWISE REUSE IT
        if(convertView == null)
        {
            convertView=inflater.inflate(R.layout.head_contact_us, null);
        }
        //GET GROUP/TEAM ITEM
        Team_ContactUs t=(Team_ContactUs) getGroup(groupPosition);
        //SET GROUP NAME
        TextView nameTv=(TextView) convertView.findViewById(R.id.textView1);
        ImageView img=(ImageView) convertView.findViewById(R.id.imageView1);
        String name=t.Name;
        nameTv.setText(name);

        convertView.setBackgroundColor(Color.LTGRAY);
        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return true;
    }
}