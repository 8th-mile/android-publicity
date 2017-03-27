package com.a8thmile.rvce.a8thmile.ui.fragments;


import java.util.ArrayList;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.ui.Activities.HomeActivity;
import com.a8thmile.rvce.a8thmile.ui.Adapters.SlidingNewsAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import cn.iwgang.countdownview.CountdownView;


public class HomeFragment extends Fragment{
    private long count;
    private long EVENT_SECONDS_IN_MILLIS=Long.parseLong("1490346000000");

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    private ImageView fb,twitter,youtube;

    private static final String[] TEXTS={"","","",""};
    private static final Integer[] IMAGES= {R.drawable.proshow3,R.drawable.proshow2,R.drawable.proshow1,R.drawable.sprite};

    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private ArrayList<String> TextsArray=new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
init(view);
        fb=(ImageView)view.findViewById(R.id.fb);
        twitter=(ImageView)view.findViewById(R.id.twitter);
        youtube=(ImageView)view.findViewById(R.id.youtube);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWebsite("https://www.facebook.com/8thmile/");
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWebsite("https://twitter.com/8thmilervce");
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWebsite("https://www.youtube.com/watch?v=1nxCqmBj3u0");
            }
        });
        final CountdownView mCvCountdownView = (CountdownView)view.findViewById(R.id.count);
        count=((EVENT_SECONDS_IN_MILLIS-19800000)- Calendar.getInstance().getTimeInMillis());
        mCvCountdownView.start((count));
        ((HomeActivity)getActivity()).changeActionbar(30);
        mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                mCvCountdownView.setVisibility(View.GONE);
            }
        });
        //ViewCompat.postOnAnimationDelayed(tabDigit1, this, 1000);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

    }
    public void goToWebsite(String site){
        Intent websiteIntent=new Intent(Intent.ACTION_VIEW);
        websiteIntent.setData(Uri.parse(site));
        startActivity(websiteIntent);
    }
    private void init(View view) {
        for(int i=0;i<IMAGES.length;i++) {
            ImagesArray.add(IMAGES[i]);
            TextsArray.add(TEXTS[i]);
        }
        mPager = (ViewPager) view.findViewById(R.id.pager);


        mPager.setAdapter(new SlidingNewsAdapter(getContext(),ImagesArray,TextsArray));


        CirclePageIndicator indicator = (CirclePageIndicator)
                view.findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }
}
