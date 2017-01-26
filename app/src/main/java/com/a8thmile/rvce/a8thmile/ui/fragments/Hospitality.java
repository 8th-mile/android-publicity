package com.a8thmile.rvce.a8thmile.ui.fragments;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.a8thmile.rvce.a8thmile.R;
import com.a8thmile.rvce.a8thmile.ui.Adapters.ExpandableListAdapter_Hospitality;

public class Hospitality extends Fragment {

    ExpandableListAdapter_Hospitality listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_hospitality, container,false);
        super.onCreate(savedInstanceState);
        // get the listview
        expListView = (ExpandableListView)view.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter_Hospitality(Hospitality.this.getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    expListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
        return view;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Policy");
        listDataHeader.add("CL info");
        listDataHeader.add("Accomodation");
        listDataHeader.add("Charges");

        //String policyone="Everybody willing to avail accommodation in 8th mile must register to get their MI numbers. You can register here.";
        //String policytwo="All participants should contact the Contingent Leader (hereafter, called CL) of their college.The details of CL would be made available to you as soon as the CL is decided.";
        String pol="Everybody willing to avail accommodation in 8th mile must register to get their MI numbers. You can register here.All participants should contact the Contingent Leader (hereafter, called CL) of their college.The details of CL would be made available to you as soon as the CL is decided.The CL will be given access to CL interface where they will be allowed to choose students from his/her college for availing accommodation for 8th mile. Last date of finalising the contingent would be 30th November 2016.When your contingent arrives at IIT Bombay, you have to come to accommodation desk, Student Activity Center (SAC) along with your college ID card. The CL should have the list of his/her entire contingent with their MI numbers beforehand. Your place of stay will be allotted along with your accommodation booklet. Accommodation would be strictly on shared basis.Each student will be given accommodation only after it has been confirmed by the Hospitality Core Group Members through the CL of the respective college.Students can avail accommodation facilities from 24th March 7:00 am to 26th March 10:00 am";
        String polone="Everybody willing to avail accommodation in 8th mile must register to get their MI numbers. ";
        String poltwo="You can register here.All participants should contact the Contingent Leader (hereafter, called CL) of their college.";
        String polthree="The details of CL would be made available to you as soon as the CL is decided.";
        String polfour="The CL will be given access to CL interface where they will be allowed to choose students from his/her college for availing accommodation for 8th mile. ";
        String polfive="Last date of finalising the contingent would be 20th February 2017.";
        String polsix="When your contingent arrives at IIT Bombay, you have to come to accommodation desk, Student Activity Center (SAC) along with your college ID card. ";
        String polseven="Your place of stay will be allotted along with your accommodation booklet.";
        String poleight="Accommodation would be strictly on shared basis.";
        String polnine="Each student will be given accommodation only after it has been confirmed by the Hospitality Core Group Members through the CL of the respective college.Students can avail accommodation facilities from 24th March 7:00 am to 26th March 10:00 am.";
        String clinformation="In order to be nominated as the CL for your college, you need an official letterhead of your college signed by the director/ principal saying that you have been nominated as the Contingent Leader for 8th mile 2016. Along with you, names of two other people should also be there who would be the Assistant CL. All three of you must mention your MI number along with your name in the letter. Send a scanned copy of the same to sanskar@moodi.org or hemant@moodi.org.";
        String clprocedure="In order to be nominated as the CL for your college, you need an official letterhead of your college signed by the director/principal saying that you have been nominated as the Contingent Leader for 8th mile 2016.Along with you, names of two other people should also be there who would be the Assistant CL. All three of you must mention your MI number along with your name in the letter.";


        // Adding child data

        String charge1="Accommodation : Rs 1600/- (Rs 100/- is refundable)";
        String charge2="For maximum of 4 days i.e. from 23rd to 27th March, 2017 irrespective of duration of stay. Food not included.";
        String charge3="In exceptional circumstances the refund amount may be forfeited subject to one or more of the following conditions:";
        //String charge4="In exceptional circumstances the refund amount may be forfeited subject to one or more of the following conditions:";
        String charge5="i) Permanent damage has been done to the room/dorm/accessories where accommodation has been provided.";
        String charge6="ii)Rooms are not vacated on or before 27nd March 10:00 am.";
        String charge7="The payment will be done through the online portal whose link will be given to all the people allotted accommodation in R V COLLEGE OF ENGINEERING";
        String charge8="Deadline for selecting contingent is 30th November and for online payment is 10th March";
        String charge9="Refund has to be collected from accommodation desk, between 8am-10am, 27th March.";
        String charge10="No refund would be provided after that.";
        String charge11="8th mile is not liable to refund the amount in case of any mistake made by the person paying the amount.";

        List<String> policy = new ArrayList<String>();

        policy.add(polone);
        policy.add(poltwo);
        policy.add(polthree);
        policy.add(polfour);
        policy.add(polfive);
        policy.add(polsix);
        policy.add(polseven);
        policy.add(poleight);
        policy.add(polnine);
        policy.add(pol);

        List<String> clinfo = new ArrayList<String>();
        clinfo.add(clinformation);
        List<String> clproc = new ArrayList<String>();
        clproc.add(clprocedure);

        List<String> charges = new ArrayList<String>();
        // charges.add(clprocedure);
        charges.add(charge1+"\n"+charge2+"\n"+charge3+"\n"+charge5+"\n"+charge6+"\n"+charge7+"\n"+charge8+"\n"+charge9+"\n"+charge10+"\n"+charge11);


        //listDataHeader.get(0)
        // List<String> policy = new ArrayList<String>();
        //charges.add()
        listDataChild.put(listDataHeader.get(0), policy); // Header, Child data
        listDataChild.put(listDataHeader.get(1), clinfo);
        listDataChild.put(listDataHeader.get(2), clproc);
        listDataChild.put(listDataHeader.get(3), charges);
        //   policy.
    }
}