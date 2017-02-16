package com.a8thmile.rvce.a8thmile.ui.fragments;

import java.util.ArrayList;


import android.app.usage.UsageEvents;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.ui.Activities.HomeActivity;
import com.ramotion.foldingcell.FoldingCell;

public class ContactFragment extends Fragment implements View.OnClickListener {
    ImageView cOne,cTwo,cThree,cFour;
    private String[] convenorContact={"9739101400","9008606189","8095848102","8197838086"};
    private String[] techContact={"8971215561","7676171398","7204076080","8277199775"};
    String phoneNo;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {   super.onCreate(savedInstanceState);
        View view= inflater.inflate(R.layout.fragment_contact, container,false);
        cOne=(ImageView)view.findViewById(R.id.cOne);
        cOne.setOnClickListener(this);
        cTwo=(ImageView)view.findViewById(R.id.cTwo);
        cTwo.setOnClickListener(this);
        cThree=(ImageView)view.findViewById(R.id.cThree);
        cThree.setOnClickListener(this);
        cFour=(ImageView)view.findViewById(R.id.cFour);
        cFour.setOnClickListener(this);
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
    public  void call()
    {
        try {
            Intent callIntent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNo));
            startActivity(callIntent);
        }
        catch (ActivityNotFoundException e)
        {
            Toast.makeText(getActivity(),"Failed to open",Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.cOne:
                phoneNo=convenorContact[0];
                call();
                break;
            case R.id.cTwo:
                phoneNo=convenorContact[1];
                call();
                break;
            case R.id.cThree:
                phoneNo=convenorContact[2];
                call();
                break;
            case R.id.cFour:
                phoneNo=convenorContact[3];
                call();
                break;

        }
    }
}