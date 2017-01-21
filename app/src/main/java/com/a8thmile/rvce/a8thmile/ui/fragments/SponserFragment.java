package com.a8thmile.rvce.a8thmile.ui.fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.a8thmile.rvce.a8thmile.R;

public class SponserFragment extends Fragment implements OnClickListener{
    Button boracle,bpaytm,bfreecgarge,bamazon,bjio,bidea;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_sponser, container,false);
        super.onCreate(savedInstanceState);
        boracle=(Button)view.findViewById(R.id.borac);
        bpaytm=(Button)view.findViewById(R.id.bpay);
        bfreecgarge=(Button)view.findViewById(R.id.bfree);
        bamazon=(Button)view.findViewById(R.id.bamaz);
        bjio=(Button)view.findViewById(R.id.bjio);
        bidea=(Button)view.findViewById(R.id.bidea);

        boracle.setOnClickListener(this);
        bpaytm.setOnClickListener(this);
        bfreecgarge.setOnClickListener(this);
        bidea.setOnClickListener(this);
        bjio.setOnClickListener(this);
        bamazon.setOnClickListener(this);
        DisplayMetrics dm =new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        //getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        boracle.setWidth(width/2);
        bpaytm.setWidth(width/2);
        bfreecgarge.setWidth(width/2);
        bamazon.setWidth(width/2);
        bjio.setWidth(width/2);
        bidea.setWidth(width/2);
        return view;
    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        Button btemp=(Button)v;
        if(btemp==boracle)
        {
            Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.oracle.com"));
            startActivity(i);
        }
        else if(btemp==bfreecgarge)
        {
            Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.freecharge.in"));
            startActivity(i);
        }
        else if(btemp==bamazon)
        {
            Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.amazon.co.in"));
            startActivity(i);
        }
        else if(btemp==bidea)
        {
            Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.ideacellular.com"));
            startActivity(i);
        }
        else if(btemp==bjio)
        {
            Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.jio.com"));
            startActivity(i);
        }
        else if(btemp==bpaytm)
        {
            Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.paytm.com"));
            startActivity(i);
        }

    }

}