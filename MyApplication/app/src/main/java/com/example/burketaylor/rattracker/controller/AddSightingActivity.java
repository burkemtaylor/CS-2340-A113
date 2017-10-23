package com.example.burketaylor.rattracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.burketaylor.rattracker.R;
import com.example.burketaylor.rattracker.model.RatSighting;
import com.example.burketaylor.rattracker.model.RatSightingDatabase;

public class AddSightingActivity extends AppCompatActivity {

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
     * Logs user out and returns to welcome screen
     * @param view current view
     */
    public void confirm(View view) {
        EditText dateTime = (EditText) findViewById(R.id.date_time);
        EditText locationType = (EditText) findViewById(R.id.location_type);
        EditText zipcode = (EditText) findViewById(R.id.zipcode);
        EditText address = (EditText) findViewById(R.id.address);
        EditText city = (EditText) findViewById(R.id.city);
        EditText borough = (EditText) findViewById(R.id.borough);
        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);

        RatSighting newRatSighting = new RatSighting("0", dateTime.getText().toString(), locationType.getText().toString(),
                zipcode.getText().toString(), address.getText().toString(), city.getText().toString(), borough.getText().toString(),
                latitude.getText().toString(), longitude.getText().toString());

        RatSightingDatabase.addRatSighting(newRatSighting);

        Intent intent = new Intent(this, com.example.burketaylor.rattracker.controller.ListActivity.class);
        startActivity(intent);
    }

    public void cancel (View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
