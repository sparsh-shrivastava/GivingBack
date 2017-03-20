package com.givingback;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import customs.ViewAllSlotsCustomAdapter;
import customs.ViewAllSlotsListClass;

public class ViewAllSlotsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_slots);

        FloatingActionButton floatAddTripButton = (FloatingActionButton) findViewById(R.id.view_all_slots_activity_float);
        assert floatAddTripButton!=null;
        floatAddTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewAllSlotsActivity.this,CreateNewSlotActivity.class));
            }
        });

        final ArrayList<ViewAllSlotsListClass> slotsList = new ArrayList<>();
        slotsList.add(new ViewAllSlotsListClass("13/7/2017","01:00","03:00","Vijay Nagar"));
        slotsList.add(new ViewAllSlotsListClass("13/7/2017","01:00","03:00","Vijay Nagar"));
        slotsList.add(new ViewAllSlotsListClass("13/7/2017","01:00","03:00","Vijay Nagar"));


        ViewAllSlotsCustomAdapter itemsAdapter = new ViewAllSlotsCustomAdapter(this,slotsList);

        ListView listView = (ListView) findViewById(R.id.view_all_slots_list);

        listView.setAdapter(itemsAdapter);

    }
}
