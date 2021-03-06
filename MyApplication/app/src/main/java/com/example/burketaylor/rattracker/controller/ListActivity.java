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
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.burketaylor.rattracker.R;
import com.example.burketaylor.rattracker.model.Database;
import com.example.burketaylor.rattracker.model.RatSighting;
import com.example.burketaylor.rattracker.model.RatSightingDatabase;
import com.example.burketaylor.rattracker.model.RatSightingFacade;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.example.burketaylor.rattracker.model.RatSightingDatabase.setLastSelected;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    String[] mobileArray;
    ArrayAdapter adapter;
    ProgressBar scanProgressBar;
    Button addSightingButton;
    Button graphButton;
    Button mapButton;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addSightingButton =(Button) findViewById(R.id.addsighting);
        graphButton =(Button) findViewById(R.id.graphButton);
        mapButton =(Button) findViewById(R.id.map);
        logoutButton =(Button) findViewById(R.id.logout);

        scanProgressBar = (ProgressBar) findViewById(R.id.scanProgressBar);


        listView = (ListView) findViewById(R.id.mobile_list);

        /*RatSightingFacade rsf = RatSightingFacade.getInstance();
        File file = new File(this.getFilesDir(), RatSightingFacade.DEFAULT_BINARY_FILE_NAME);
        Log.d(String.valueOf(file.exists()), String.valueOf(file.canRead()));
        rsf.loadBinary(file);
        Log.d("LOADING", String.valueOf(RatSightingDatabase.getMap().size()));*/

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

    public void addSighting(View view) {
        Intent intent = new Intent(this, com.example.burketaylor.rattracker.controller.AddSightingActivity.class);
        startActivity(intent);
    }
    public void goToGraph(View view){
        Intent intent = new Intent(this, com.example.burketaylor.rattracker.controller.GraphActivity.class);
        startActivity(intent);
    }

    private class ScanTask extends AsyncTask<Boolean, Void, Void> {

        protected void onPreExecute() {
            scanProgressBar.setVisibility(View.VISIBLE);
            addSightingButton.setVisibility(View.INVISIBLE);
            graphButton.setVisibility(View.INVISIBLE);
            mapButton.setVisibility(View.INVISIBLE);
            logoutButton.setVisibility(View.INVISIBLE);


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
            addSightingButton.setVisibility(View.VISIBLE);
            graphButton.setVisibility(View.VISIBLE);
            mapButton.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.VISIBLE);

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