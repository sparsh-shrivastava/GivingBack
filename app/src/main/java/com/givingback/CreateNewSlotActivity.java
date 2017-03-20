package com.givingback;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.SphericalUtil;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateNewSlotActivity extends AppCompatActivity implements
    GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{

    TextView dateSelectText,fromTimeText,toTimeText,placeText;
    private DatePickerDialog slotDate;
    private TimePickerDialog fromTimePicker,toTimePicker;
    private SimpleDateFormat dateFormatter;
    Calendar newCalendar;
    int PLACE_PICKER_REQUEST = 1;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LatLng currentLatLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_slot);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Slot");
        //toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back_arrow);

        //set the back arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        windWidgets();
        windPickers();
        windListeners();

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    private void windListeners() {
        dateSelectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slotDate.show();
            }
        });

        fromTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromTimePicker.show();
            }
        });

        toTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTimePicker.show();
            }
        });

        placeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                builder.setLatLngBounds(toBounds(currentLatLng,1.0));



                Intent intent = null;
                try {
                    intent = builder.build(CreateNewSlotActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);

                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void windPickers() {
        slotDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateSelectText.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        fromTimePicker = new TimePickerDialog(CreateNewSlotActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                fromTimeText.setText( selectedHour + ":" + selectedMinute);
            }
        }, newCalendar.HOUR_OF_DAY, newCalendar.MINUTE, true);//Yes 24 hour time
        fromTimePicker.setTitle("Select Time");

        toTimePicker = new TimePickerDialog(CreateNewSlotActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                toTimeText.setText( selectedHour + ":" + selectedMinute);
            }
        }, newCalendar.HOUR_OF_DAY, newCalendar.MINUTE, true);//Yes 24 hour time
        toTimePicker.setTitle("Select Time");


    }

    private void windWidgets() {
        toTimeText = (TextView) findViewById(R.id.new_slot_to_time);
        fromTimeText = (TextView) findViewById(R.id.new_slot_from_time);
        dateSelectText = (TextView) findViewById(R.id.new_slot_date);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        newCalendar = Calendar.getInstance();
        placeText = (TextView) findViewById(R.id.new_slot_place);
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        currentLatLng = new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());




    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("","In connection suspended");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode==PLACE_PICKER_REQUEST){
            if(resultCode == RESULT_OK ){
                Place place = PlacePicker.getPlace(data,this);
                String placeName = String.format(("%s"),place.getName());
                String placeAdd = String.format("%s",place.getAddress());
                placeText.setText(placeName);
                //checkInTextAdd.setText(placeAdd);
            }
        }

    }

    public LatLngBounds toBounds(LatLng center, double radius) {
        LatLng southwest = SphericalUtil.computeOffset(center, radius * Math.sqrt(2.0), 225);
        LatLng northeast = SphericalUtil.computeOffset(center, radius * Math.sqrt(2.0), 45);
        return new LatLngBounds(southwest, northeast);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("","on connection failed.");
    }

}
