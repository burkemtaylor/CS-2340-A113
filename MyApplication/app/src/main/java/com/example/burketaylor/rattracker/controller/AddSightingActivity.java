package com.example.burketaylor.rattracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.burketaylor.rattracker.R;
import com.example.burketaylor.rattracker.model.Database;
import com.example.burketaylor.rattracker.model.RatSighting;
import com.example.burketaylor.rattracker.model.RatSightingDatabase;
import com.example.burketaylor.rattracker.model.RatSightingFacade;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddSightingActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private String dateTime;
    private EditText locationType;
    private EditText zipcode;
    private EditText address;
    private EditText city;
    private EditText borough;
    private EditText latitude;
    private EditText longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sighting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    /**
     * Adds new rat sighting
     * @param view current view
     */
    public void confirm(View view) {
        datePicker = (DatePicker) findViewById(R.id.datePicker3);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        int month = datePicker.getMonth() + 1;
        String monthString = "" + month;
        if (month < 10) {
            monthString = 0 + monthString;
        }
        int day = datePicker.getDayOfMonth();
        String dayString = "" + day;
        if (day < 10) {
            dayString = 0 + dayString;
        }
        int year = datePicker.getYear();
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        dateTime = monthString + "/" + dayString + "/" + year + " " + hour + ":" + minute;
        Log.d("datetime", "fff " + dateTime);
        locationType = (EditText) findViewById(R.id.location_type);
        zipcode = (EditText) findViewById(R.id.zipcode);
        address = (EditText) findViewById(R.id.address);
        city = (EditText) findViewById(R.id.city);
        borough = (EditText) findViewById(R.id.borough);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);

        RatSightingDatabase rsd = RatSightingDatabase.getInstance();


        //if () {
        RatSighting newRatSighting = new RatSighting("0", dateTime, locationType.getText().toString(),
                zipcode.getText().toString(), address.getText().toString(), city.getText().toString(), borough.getText().toString(),
                latitude.getText().toString(), longitude.getText().toString());

        rsd.addRatSighting(newRatSighting);

        File file = new File(this.getFilesDir(), String.valueOf(R.raw.rat_sightings));
        rsd.saveText(file);
        //RatSightingDatabase thing = new RatSightingDatabase(rsd);

        //RatSightingFacade rsf = RatSightingFacade.getInstance();

        //File file = new File(this.getFilesDir(), RatSightingFacade.DEFAULT_BINARY_FILE_NAME);
        //Log.d(String.valueOf(file.exists()), String.valueOf(file.canRead()));
        //rsf.saveBinary(file);
        Log.d("RSD", String.valueOf(rsd.getMap().size()));
        Log.d("SAVING", String.valueOf(file.length()));

        Intent intent = new Intent(this, com.example.burketaylor.rattracker.controller.ListActivity.class);
        startActivity(intent);
        //} else {
        //view.setError("Username or Password invalid.");
        //focusView = mEmailView;
        //cancel = true;
        //    focusView.requestFocus();
        //}
    }

    public void cancel (View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
