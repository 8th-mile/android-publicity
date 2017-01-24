package com.a8thmile.rvce.a8thmile.ui.fragments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.a8thmile.rvce.a8thmile.R;

public class HomeFragment extends Fragment {
    CountDownTimer cdt;
    TextView timerem;
    Button bdays,bhours,bmin,bsec;
    TextView tdays,thours,tmin,tsec;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_home, container,false);
        super.onCreate(savedInstanceState);
        // get the listview
        //timerem=(TextView)findViewById(R.id.tvcdt);
       /* tdays=(TextView)view.findViewById(R.id.tdays);
        thours=(TextView)view.findViewById(R.id.thour);
        tmin=(TextView)view.findViewById(R.id.tminutes);
        tsec=(TextView)view.findViewById(R.id.tseconds);*/
        Date today = new Date();
        Date userDob=new Date();
        try {
            userDob = new SimpleDateFormat("yyyy-MM-dd").parse("2017-03-23");

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long diff =userDob.getTime()-today.getTime() ;
        System.out.println(""+diff);

        cdt=new CountDownTimer(diff,1000) {

            public void onTick(long millisUntilFinished) {
                // timerem.setText(""+millisUntilFinished);

/*                if(((millisUntilFinished/1000)%60)<10)
                {

                    tdays.setText("Days\n\n"+millisUntilFinished / (3600000*24));
                    thours.setText("Hours\n\n"+((millisUntilFinished / 3600000)%24));
                    tmin.setText("Mins\n\n"+((millisUntilFinished / 60000)%60));
                    tsec.setText("Secs\n\n"+((millisUntilFinished/1000)%60));

                    //bdays.setText(""+millisUntilFinished / (3600000*24));
                    //timerem.setText("Time remaining:"+millisUntilFinished / (3600000*24)+":" +((millisUntilFinished / 3600000)%24)+":"+ ((millisUntilFinished / 60000)%60)+":0"+((millisUntilFinished/1000)%60));

                }
                else
                {
                    tdays.setText("Days\n\n"+millisUntilFinished / (3600000*24));
                    thours.setText("Hours\n\n"+((millisUntilFinished / 3600000)%24));
                    tmin.setText("Mins\n\n"+((millisUntilFinished / 60000)%60));
                    tsec.setText("Secs\n\n"+((millisUntilFinished/1000)%60));

                }*/
                //	timerem.setText("Time remaining:"+millisUntilFinished / (3600000*24)+":" +((millisUntilFinished / 3600000)%24)+":"+ ((millisUntilFinished / 60000)%60)+":"+((millisUntilFinished/1000)%60));
            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub

            }
        };
        cdt.start();
        return view;
    }




}
