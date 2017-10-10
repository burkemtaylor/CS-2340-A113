package com.example.burketaylor.rattracker.controller;

import android.content.Intent;
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

import com.example.burketaylor.rattracker.R;
import com.example.burketaylor.rattracker.model.RatSighting;
import com.example.burketaylor.rattracker.model.RatSightingDatabase;

import java.io.IOException;

import static com.example.burketaylor.rattracker.model.RatSightingDatabase.setLastSelected;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            new RatSightingDatabase(this.getResources().openRawResource(R.raw.rat_sightings));
        } catch (IOException e) {
            Log.d("Scan error", e.getLocalizedMessage());
        }

        Object[] ratArray = RatSightingDatabase.getMap().values().toArray();
        String[] mobileArray = new String[RatSightingDatabase.getMap().size()];
        for (int i = 0; i < mobileArray.length; i++) {
            mobileArray[i] = ((RatSighting) ratArray[i]).getUniqueKey();
        }

        final ArrayAdapter adapter = new ArrayAdapter<String>(this,
               android.R.layout.simple_list_item_1, mobileArray);

        final ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                setLastSelected((String) listView.getItemAtPosition(position));
                selected();
            }
        });

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

    public void selected() {
        Intent intent = new Intent(this, com.example.burketaylor.rattracker.controller.RatInfoActivity.class);
        startActivity(intent);

    }
}
