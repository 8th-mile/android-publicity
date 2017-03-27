package com.a8thmile.rvce.a8thmile.ui.fragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.ui.Activities.HomeActivity;

public class SponserFragment extends Fragment implements OnClickListener{

ImageView indigo,specsmaker,honda,coke,eureka,jkcement,kotak,jeolous,aia,chefs;
    String[] links=new String[]{
      "http://www.specsmakers.in"
            ,"https://www.honda2wheelersindia.com/",
            "http://www.mtvindia.com/cokestudio/"
            ,"https://www.allindiaadmission.co.in/",
            "http://www.eurekaforbes.com/"
            ,"http://www.jkcement.com/"
            ,"http://www.kotak.com/"
            ,"http://www.indigonation.com/",
            "http://www.jealous21.com/",
            "http://chefsbasket.com/"

    };
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_sponser, container,false);
        super.onCreate(savedInstanceState);
        indigo=(ImageView)view.findViewById(R.id.indigo) ;
        indigo.setOnClickListener(this);
        specsmaker=(ImageView)view.findViewById(R.id.specs) ;
        honda=(ImageView)view.findViewById(R.id.honda) ;
        coke=(ImageView)view.findViewById(R.id.coke) ;
        eureka=(ImageView)view.findViewById(R.id.eureka) ;
        jkcement=(ImageView)view.findViewById(R.id.jk) ;
        kotak=(ImageView)view.findViewById(R.id.kotak) ;
        jeolous=(ImageView)view.findViewById(R.id.jeolous) ;
        aia=(ImageView)view.findViewById(R.id.aia) ;
        chefs=(ImageView)view.findViewById(R.id.chefs);
        chefs.setOnClickListener(this);
        specsmaker.setOnClickListener(this);
        honda.setOnClickListener(this);
        coke.setOnClickListener(this);
        eureka.setOnClickListener(this);
        jkcement.setOnClickListener(this);
        kotak.setOnClickListener(this);
        jeolous.setOnClickListener(this);
        aia.setOnClickListener(this);

        indigo.setBackgroundColor(Color.WHITE);
        ((HomeActivity)getActivity()).changeActionbar(30);
        return view;
    }

   public void goToWebsite(String site){
       Intent websiteIntent=new Intent(Intent.ACTION_VIEW);
       websiteIntent.setData(Uri.parse(site));
       startActivity(websiteIntent);
   }
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.specs:
                goToWebsite(links[0]);
                break;
            case R.id.honda:
                goToWebsite(links[1]);
                break;
            case R.id.coke:
                goToWebsite(links[2]);
                break;
            case R.id.aia:
                goToWebsite(links[3]);
                break;
            case R.id.eureka:
                goToWebsite(links[4]);
                break;
            case R.id.jk:
                goToWebsite(links[5]);
                break;
            case R.id.kotak:
                goToWebsite(links[6]);
                break;
            case R.id.indigo:
                goToWebsite(links[7]);
                break;
            case R.id.jeolous:
                goToWebsite(links[8]);
                break;
            case R.id.chefs:
                goToWebsite(links[9]);
                break;
        }
    }

}