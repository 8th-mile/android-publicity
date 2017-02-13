package com.a8thmile.rvce.a8thmile.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.models.EventRegister;
import com.a8thmile.rvce.a8thmile.ui.Activities.SubEventActivity;
import com.a8thmile.rvce.a8thmile.ui.Adapters.EventAdapter;
import com.a8thmile.rvce.a8thmile.ui.EventItem;

import java.util.ArrayList;
import java.util.List;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

import org.w3c.dom.Text;

public class SliderFragment extends Fragment {
    private static final String KEY_POSITION="position";
    private static EventAdapter eventAdapter;
    private TextView title;

    public static SliderFragment newInstance(EventAdapter adapter,int position, EventItem list, List<EventFields> eventList, String token, String user_id, String category) {
        SliderFragment frag=new SliderFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        args.putParcelableArrayList("sublist", (ArrayList<? extends Parcelable>) eventList);
        args.putParcelable("list", list);
        args.putString("token",token);
        args.putString("user_id",user_id);
        args.putString("category",category);
       eventAdapter=adapter;
        frag.setArguments(args);

        return(frag);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            // Fetch data or something...!=

            eventAdapter.changeToolbarColor(getArguments().getInt(KEY_POSITION));

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        EventItem eventItem=getArguments().getParcelable("list");
        View result=inflater.inflate(R.layout.event_list_item, container, false);
        title=(TextView) result.findViewById(R.id.eventTitle);
        ImageView imageView=(ImageView)result.findViewById(R.id.eventImage);




        LinearLayout linearLayout=(LinearLayout)result.findViewById(R.id.eventLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subIntent=new Intent(getActivity(), SubEventActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("subevents", (ArrayList<? extends Parcelable>) getArguments().getParcelableArrayList("sublist"));
                subIntent.putExtras(bundle);
                subIntent.putExtra("category",getArguments().getString("category"));
                subIntent.putExtra("user_id",getArguments().getString("user_id"));
                subIntent.putExtra("token",getArguments().getString("token"));
                startActivity(subIntent);
            }
        });


        title.setText(eventItem.getTitle());
        imageView.setImageResource(eventItem.getIconId());
        linearLayout.setBackgroundResource(eventItem.getBackColId());
        return(result);
    }
}