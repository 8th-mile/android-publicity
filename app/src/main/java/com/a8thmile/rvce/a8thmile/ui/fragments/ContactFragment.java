package com.a8thmile.rvce.a8thmile.ui.fragments;

import java.util.ArrayList;


import android.net.Uri;
import android.os.Bundle;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.ui.Adapters.ContactUsAdapter;
import com.a8thmile.rvce.a8thmile.ui.Adapters.Team_ContactUs;

public class ContactFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_contact, container,false);
        super.onCreate(savedInstanceState);
        //THE EXPANDABLE
        final ExpandableListView elv=(ExpandableListView) view.findViewById(R.id.expandableListView1);
        final ArrayList<Team_ContactUs> team=getData();
        //CREATE AND BIND TO ADAPTER
        ContactUsAdapter adapter=new ContactUsAdapter(ContactFragment.this.getActivity(), team);
        elv.setAdapter(adapter);
        //SET ONCLICK LISTENER
        elv.setOnGroupExpandListener(new OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    elv.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
        elv.setOnChildClickListener(new OnChildClickListener() {
                                        @Override
                                        public boolean onChildClick(ExpandableListView parent, View v, int groupPos,
                                                                    int childPos, long id) {
                                            if(groupPos==0)
                                            {
                                                if(childPos==0)
                                                {
                                                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                                                    callIntent.setData(Uri.parse("tel:0377778888"));

                                                    startActivity(callIntent);
                                                }
                                                else if(childPos==1)
                                                {
                                                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                                                    callIntent.setData(Uri.parse("tel:0377778888"));

                                                    startActivity(callIntent);
                                                }
                                                else if(childPos==2)
                                                {
                                                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                                                    callIntent.setData(Uri.parse("tel:0377778888"));

                                                    startActivity(callIntent);
                                                }
                                                else if(childPos==2)
                                                {
                                                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                                                    callIntent.setData(Uri.parse("tel:0377778888"));

                                                    startActivity(callIntent);
                                                }
                                            }
                                            else  if(groupPos==1)
                                            {
                                                if(childPos==0)
                                                {
                                                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                                                    callIntent.setData(Uri.parse("tel:0377778888"));

                                                    startActivity(callIntent);
                                                }
                                                else if(childPos==1)
                                                {
                                                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                                                    callIntent.setData(Uri.parse("tel:0377778888"));

                                                    startActivity(callIntent);
                                                }
                                                else if(childPos==2)
                                                {
                                                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                                                    callIntent.setData(Uri.parse("tel:0377778888"));

                                                    startActivity(callIntent);
                                                }
                                                else if(childPos==2)
                                                {
                                                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                                                    callIntent.setData(Uri.parse("tel:0377778888"));

                                                    startActivity(callIntent);
                                                }  }

                                            return false;
                                        }
                                    }

        );
        return view;
    }
    //ADD AND GET DATA
    private ArrayList<Team_ContactUs> getData()
    {
        Team_ContactUs t1=new Team_ContactUs("Conveniers");
        t1.contacts.add("Convenier1");
        t1.contacts.add("Convenier2");
        t1.contacts.add("Convenier3");

        Team_ContactUs t2=new Team_ContactUs("Technical Head");
        t2.contacts.add("TechHead1");
        t2.contacts.add("TechHead2");
        t2.contacts.add("TechHead3");

        ArrayList<Team_ContactUs> allTeams=new ArrayList<Team_ContactUs>();
        allTeams.add(t1);
        allTeams.add(t2);
        return allTeams;
    }
}