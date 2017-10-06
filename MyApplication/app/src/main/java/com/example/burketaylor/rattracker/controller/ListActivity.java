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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.burketaylor.rattracker.R;
import com.example.burketaylor.rattracker.model.RatSighting;
import com.example.burketaylor.rattracker.model.RatSightingDatabase;

import java.io.IOException;
import java.io.InputStream;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    String[] mobileArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        listView = (ListView) findViewById(R.id.mobile_list);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        new ScanTask().execute();


    }

    /**
     * Logs user out and returns to welcome screen
     * @param view current view
     */
    public void logout(View view) {
        Intent intent = new Intent(this, com.example.burketaylor.rattracker.controller.WelcomeActivity.class);
        startActivity(intent);
    }

    private class ScanTask extends AsyncTask<InputStream, Void, Void> {

        protected Void doInBackground(InputStream... in) {
            try {
                new RatSightingDatabase(ListActivity.this.getResources().openRawResource(R.raw.Rat_Sightings));
            } catch (IOException e) {
                Log.d("Scan error", e.getLocalizedMessage());
            }

            Object[] ratArray = RatSightingDatabase.getMap().values().toArray();
            String[] mobileArray = new String[RatSightingDatabase.getMap().size()];
            for (int i = 0; i < mobileArray.length; i++) {
                mobileArray[i] = i + 1 + ". " + ((RatSighting) ratArray[i]).getUniqueKey();
            }


            return null;
        }


        protected void onPostExecute(Void v) {
            ArrayAdapter adapter = new ArrayAdapter<String>(ListActivity.this,
                    android.R.layout.simple_list_item_1, mobileArray);


            listView.setAdapter(adapter);

        }
    }
}
