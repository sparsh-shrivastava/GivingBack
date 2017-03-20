package com.givingback;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import customs.ViewAllSlotsCustomAdapter;
import customs.ViewAllSlotsListClass;


public class ViewAllSlotsFragment extends Fragment {


    public ViewAllSlotsFragment() {
        // Required empty public constructor
    }
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_view_all_slots, container, false);
        FloatingActionButton floatAddTripButton = (FloatingActionButton) rootView.findViewById(R.id.view_all_slots_activity_float);
        assert floatAddTripButton!=null;
        floatAddTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),CreateNewSlotActivity.class));
            }
        });

        final ArrayList<ViewAllSlotsListClass> slotsList = new ArrayList<>();
        slotsList.add(new ViewAllSlotsListClass("13/7/2017","01:00","03:00","Vijay Nagar"));
        slotsList.add(new ViewAllSlotsListClass("13/7/2017","01:00","03:00","Vijay Nagar"));
        slotsList.add(new ViewAllSlotsListClass("13/7/2017","01:00","03:00","Vijay Nagar"));


        ViewAllSlotsCustomAdapter itemsAdapter = new ViewAllSlotsCustomAdapter(getActivity(),slotsList);

        ListView listView = (ListView) rootView.findViewById(R.id.view_all_slots_list);

        listView.setAdapter(itemsAdapter);
        return rootView;
    }

}
