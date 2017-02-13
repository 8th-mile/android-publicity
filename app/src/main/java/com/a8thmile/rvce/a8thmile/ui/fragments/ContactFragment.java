package com.a8thmile.rvce.a8thmile.ui.fragments;

import java.util.ArrayList;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.ui.Activities.HomeActivity;
import com.ramotion.foldingcell.FoldingCell;

public class ContactFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_contact, container,false);
        super.onCreate(savedInstanceState);
        ((HomeActivity)getActivity()).changeActionbar(30);
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

}