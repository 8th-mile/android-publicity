package com.a8thmile.rvce.a8thmile.ui.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.ui.Activities.HomeActivity;
import com.ramotion.foldingcell.FoldingCell;

public class ContactFragment extends Fragment implements View.OnClickListener {
    ImageView cOne,cTwo,cThree,cFour,fOne,fTwo,tOne,tTwo,aOne,aTwo,aThree;
    private String[] convenorContact={"9739101400","9008606189","8095848102","8197838086"};
    private String[] facultyContact={"9480404395","9741419511"};
    private String[] techContact={"7676171398","8971215561"};
    //private String[] androidContact={"720407080"};
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
        fOne=(ImageView)view.findViewById(R.id.fOne);
        fOne.setOnClickListener(this);
        fTwo=(ImageView)view.findViewById(R.id.fTwo);
        fTwo.setOnClickListener(this);
        tOne=(ImageView)view.findViewById(R.id.tOne);
        tOne.setOnClickListener(this);
        tTwo=(ImageView)view.findViewById(R.id.tTwo);
        tTwo.setOnClickListener(this);
        ((HomeActivity)getActivity()).changeActionbar(30);
        final FoldingCell fc = (FoldingCell) view.findViewById(R.id.folding_cell);
        final FoldingCell fc2 = (FoldingCell) view.findViewById(R.id.folding_cell2);
        final FoldingCell fc3 = (FoldingCell) view.findViewById(R.id.folding_cell3);
        final FoldingCell fc4 = (FoldingCell) view.findViewById(R.id.folding_cell4);
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
        fc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc4.toggle(false);
            }
        });



        return view;
    }
    /*public  void call()
    {
        try {
            Intent callIntent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNo));
            startActivity(callIntent);
        }
        catch (ActivityNotFoundException e)
        {
            Toast.makeText(getActivity(),"Failed to open",Toast.LENGTH_LONG).show();
        }

    }*/
    public void call() {
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    123);
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+phoneNo)));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    call();
                } else {

                }
                break;

            default:
                break;
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
            case R.id.fOne:
                phoneNo=facultyContact[0];
                call();
                break;
            case R.id.fTwo:
                phoneNo=facultyContact[1];
                call();
                break;
            case R.id.tOne:
                phoneNo=techContact[0];
                call();
                break;
            case R.id.tTwo:
                phoneNo=techContact[1];
                call();
                break;


        }
    }
}