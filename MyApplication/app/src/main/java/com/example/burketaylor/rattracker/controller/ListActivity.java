package com.example.burketaylor.rattracker.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.burketaylor.rattracker.R;
import com.example.burketaylor.rattracker.model.RatSighting;
import com.example.burketaylor.rattracker.model.RatSightingDatabase;

import java.io.IOException;
import java.io.InputStream;

import static com.example.burketaylor.rattracker.model.RatSightingDatabase.setLastSelected;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    String[] mobileArray;
    ArrayAdapter adapter;
    ProgressBar scanProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        scanProgressBar = (ProgressBar) findViewById(R.id.scanProgressBar);


        listView = (ListView) findViewById(R.id.mobile_list);

        new ScanTask().execute(RatSightingDatabase.isEmpty());

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
    public void logout(View view) {
        Intent intent = new Intent(this, com.example.burketaylor.rattracker.controller.WelcomeActivity.class);
        startActivity(intent);
    }

    /**
     * Takes user to map view
     * @param view current view
     */
    public void mapView(View view) {
        Intent intent = new Intent(this, com.example.burketaylor.rattracker.controller.MapsActivity.class);
        startActivity(intent);
    }

    /**
     * Takes user to screen displaying details on selected rat sighting
     */
    public void selected() {
        Intent intent = new Intent(this, com.example.burketaylor.rattracker.controller.RatInfoActivity.class);
        startActivity(intent);



    }

    private class ScanTask extends AsyncTask<Boolean, Void, Void> {

        protected void onPreExecute() {
            scanProgressBar.setVisibility(View.VISIBLE);
        }

        protected Void doInBackground(Boolean... in) {
            boolean notLoaded = in[0];
            if (notLoaded) {
                try {
                    new RatSightingDatabase(ListActivity.this.getResources().openRawResource(R.raw.rat_sightings));

                } catch (IOException e) {
                    Log.d("Scan error", e.getLocalizedMessage());
                }
            }

            Object[] ratArray = RatSightingDatabase.getMap().values().toArray();
            mobileArray = new String[RatSightingDatabase.getMap().size()];
            for (int i = 0; i < mobileArray.length; i++) {
                mobileArray[i] = ((RatSighting) ratArray[i]).getUniqueKey();
            }



            return null;
        }


        protected void onPostExecute(Void v) {
            scanProgressBar.setVisibility(View.GONE);

            adapter = new ArrayAdapter<String>(ListActivity.this,
                    android.R.layout.simple_list_item_1, mobileArray);


            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    setLastSelected((String) listView.getItemAtPosition(position));

                    selected();
                }
            });


        }
    }
}