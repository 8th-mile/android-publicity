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
import com.ramotion.foldingcell.FoldingCell;

public class ContactFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_contact, container,false);
        super.onCreate(savedInstanceState);

        final FoldingCell fc = (FoldingCell) view.findViewById(R.id.folding_cell);
        final FoldingCell fc2 = (FoldingCell) view.findViewById(R.id.folding_cell2);
        final FoldingCell fc3 = (FoldingCell) view.findViewById(R.id.folding_cell3);
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });
        fc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc2.toggle(false);
            }
        });
        fc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc3.toggle(false);
            }
        });
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