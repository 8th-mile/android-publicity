package com.a8thmile.rvce.a8thmile.ui.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.ui.Activities.HomeActivity;

public class Hospitality extends Fragment {

   ImageView i1,i2;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_hospitality, container,false);
        super.onCreate(savedInstanceState);
        ((HomeActivity)getActivity()).changeActionbar(30);
        i1=(ImageView)view.findViewById(R.id.one);
        i2=(ImageView)view.findViewById(R.id.two);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call("7676536038");
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call("9742630662");
            }
        });
        return view;
    }
    public void call(String phoneNo) {
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

}