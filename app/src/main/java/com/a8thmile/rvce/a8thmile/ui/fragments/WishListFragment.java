package com.a8thmile.rvce.a8thmile.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.events.register.RegisterPresenter;
import com.a8thmile.rvce.a8thmile.events.register.RegisterPresenterImpl;
import com.a8thmile.rvce.a8thmile.events.register.RegisterView;
import com.a8thmile.rvce.a8thmile.models.EventFields;
import com.a8thmile.rvce.a8thmile.models.EventResponse;
import com.a8thmile.rvce.a8thmile.models.EventWishListGetResponse;
import com.a8thmile.rvce.a8thmile.ui.HomeActivity;
import com.a8thmile.rvce.a8thmile.ui.SubEventAdapter;
import com.a8thmile.rvce.a8thmile.ui.WishListAdapter;

import java.util.ArrayList;
import java.util.List;


public class WishListFragment extends Fragment implements RegisterView{
   List<EventFields> wishList;
    String token;
    String id;

    ListView lv;
    WishListAdapter adapter;
    RegisterPresenter registerPresenter;


    public WishListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerPresenter=new RegisterPresenterImpl(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        wishList=new ArrayList<EventFields>();
        View view= inflater.inflate(R.layout.fragment_wish_list, container, false);
        lv= (ListView) view.findViewById(R.id.myList);

        return view;
    }

    @Override
    public void registered(String message) {
        //Irrelevant to this fragment
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        token=((HomeActivity)getActivity()).getToken();
        id=((HomeActivity)getActivity()).getId();
        registerPresenter.wishListGet(id,token);
    }

    @Override
    public void RegisterFailed(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void wishListGot(EventResponse eventResponse) {
            wishList=eventResponse.getResults();
        adapter = new WishListAdapter(getContext(), R.layout.wishlist_card, wishList,token,id,WishListFragment.this);
        lv.setAdapter(adapter);

        Log.v("test","list "+wishList.get(0).getName());

    }
}
