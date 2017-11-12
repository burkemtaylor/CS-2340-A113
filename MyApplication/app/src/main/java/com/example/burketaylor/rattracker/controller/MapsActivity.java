package com.example.burketaylor.rattracker.controller;

import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.burketaylor.rattracker.model.MapEntryManager;
import com.example.burketaylor.rattracker.model.RatSighting;
import com.example.burketaylor.rattracker.model.RatSightingDatabase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.burketaylor.rattracker.R;


import java.util.ArrayList;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final Integer[] SPINNER_RANGE = {10, 25, 50, 100, 150, 300, 500};
    private final LatLng DEFAULT_LOC = new LatLng(40.73, -73.94);
    private GoogleMap mainMap;
    private PopupWindow optionsWindow;
    private Spinner numRangeSpinner;
    private TextView startDateText;
    private TextView endDateText;
    private TextView rangeText;
    private DatePicker startPicker;
    private DatePicker endPicker;
    private RadioGroup optionRadioGroup;
    private ProgressBar mapProgressBar;
    private int lastChecked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        lastChecked = R.id.shownByNum;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapProgressBar = (ProgressBar) findViewById(R.id.mapProgressBar);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mainMap = googleMap;

        mainMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(latLng);
                markerOptions.title("ID");
                markerOptions.snippet("Info");

                mainMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                //mainMap.addMarker(markerOptions);
            }
        });
        UiSettings mapUiSettings = mainMap.getUiSettings();
        mapUiSettings.setZoomControlsEnabled(true);


        new EntryLoader().execute("numRange", "100");



        mainMap.moveCamera(CameraUpdateFactory.newLatLng(DEFAULT_LOC));
        mainMap.moveCamera(CameraUpdateFactory.zoomTo(10.0f));

        mainMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }
    private class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final View myContentsView;

        CustomInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(Marker marker){
            TextView tTitle = (TextView) myContentsView.findViewById(R.id.infoTitle);
            tTitle.setText(marker.getTitle());
            TextView tSnippet = (TextView) myContentsView.findViewById(R.id.infoSnippet);
            tSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker){
            return null;
        }
    }

    public void showOptions(View view){
        View optionsView = getLayoutInflater().inflate(R.layout.map_options, null);

        optionsWindow = new PopupWindow(optionsView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        optionsWindow.setFocusable(true);
        optionsWindow.setBackgroundDrawable(new ColorDrawable());
        numRangeSpinner = (Spinner) optionsView.findViewById(R.id.numRangeSpinner);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, SPINNER_RANGE);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numRangeSpinner.setAdapter(adapter);

        startDateText = (TextView) optionsView.findViewById(R.id.startDateView);
        endDateText = (TextView) optionsView.findViewById(R.id.endDateView);
        rangeText = (TextView) optionsView.findViewById(R.id.rangeText);
        startPicker = (DatePicker)optionsView.findViewById(R.id.startDatePicker);
        endPicker = (DatePicker) optionsView.findViewById(R.id.endDatePicker);
        optionRadioGroup = (RadioGroup) optionsView.findViewById(R.id.optionRadioGroup);
        optionRadioGroup.check(lastChecked);

        if(lastChecked == R.id.shownByNum) {
            updateOptions("numRange");
        } else if(lastChecked ==R.id.shownByDate) {
            updateOptions("dateRange");
        }
        optionsWindow.showAsDropDown(view);
    }

    public void onOptionRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.shownByDate:
                if(checked) {
                    updateOptions("dateRange");
                }
                break;
            case R.id.shownByNum:
                if(checked) {
                    updateOptions("numRange");
                }
                break;
        }
    }

    public void optionConfirm(View view) {
        switch(optionRadioGroup.getCheckedRadioButtonId()) {
            case R.id.shownByDate:
                mainMap.clear();
               int dayS = startPicker.getDayOfMonth();
               String daySString;
               if (dayS < 10){
                   daySString = "0"+Integer.toString(dayS);
               } else {
                   daySString = Integer.toString(dayS);
               }
                int monthS = startPicker.getMonth();
                String monthSString;
                if (monthS < 10){
                    monthSString = "0"+Integer.toString(monthS);
                } else {
                    monthSString = Integer.toString(monthS);
                }
                String startDateString = Integer.toString(startPicker.getYear()) + monthSString + daySString;

                int dayE = endPicker.getDayOfMonth();
                String dayEString;
                if (dayE < 10){
                    dayEString = "0"+Integer.toString(dayE);
                } else {
                    dayEString = Integer.toString(dayE);
                }
                int monthE = endPicker.getMonth();
                String monthEString;
                if (monthE < 10){
                    monthEString = "0"+Integer.toString(monthE);
                } else {
                    monthEString = Integer.toString(monthE);
                }
                String endDateString = Integer.toString(endPicker.getYear()) + monthEString + dayEString;
                Log.d("startdate", startDateString);
                Log.d("enddate", endDateString);

                new EntryLoader().execute("dateRange", startDateString, endDateString);
                break;
            case R.id.shownByNum:
                mainMap.clear();
                new EntryLoader().execute("numRange", numRangeSpinner.getSelectedItem().toString());
                break;
        }
        lastChecked = optionRadioGroup.getCheckedRadioButtonId();
        optionsWindow.dismiss();
    }

    private void updateOptions(String state){
        switch (state) {
            case "numRange":
                startDateText.setVisibility(View.GONE);
                endDateText.setVisibility(View.GONE);
                startPicker.setVisibility(View.GONE);
                endPicker.setVisibility(View.GONE);
                rangeText.setVisibility(View.VISIBLE);
                numRangeSpinner.setVisibility(View.VISIBLE);
                break;
            case "dateRange":
                startDateText.setVisibility(View.VISIBLE);
                endDateText.setVisibility(View.VISIBLE);
                startPicker.setVisibility(View.VISIBLE);
                endPicker.setVisibility(View.VISIBLE);
                rangeText.setVisibility(View.GONE);
                numRangeSpinner.setVisibility(View.GONE);
                break;
            default:
                startDateText.setVisibility(View.GONE);
                endDateText.setVisibility(View.GONE);
                startPicker.setVisibility(View.GONE);
                endPicker.setVisibility(View.GONE);
                rangeText.setVisibility(View.VISIBLE);
                numRangeSpinner.setVisibility(View.VISIBLE);
                break;
        }
    }

    private class EntryLoader extends AsyncTask<String, Void, Void>{

        ArrayList<RatSighting> entries;

        @Override
        protected void onPreExecute(){
            mapProgressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(String[] params){
            String state = params[0];

            switch (state) {
                case "numRange":
                    int range = Integer.parseInt(params[1]);
                    entries = MapEntryManager.getReportsByRange(range);
                    break;
                case "dateRange":
                    int startDate = Integer.parseInt(params[1]);
                    int endDate = Integer.parseInt(params[2]);
                    entries = MapEntryManager.getReportsByDate(startDate, endDate);

            }


            return null;
        }

        @Override
        protected void onPostExecute(Void stuff) {
            double offset = .001;
            Log.d("size", Integer.toString(entries.size()));
            for (RatSighting entry: entries){
                Log.d("DateSortTest", Integer.toString(entry.getTimeValue()));
                LatLng loc;
                try {
                    loc = new LatLng(Double.parseDouble(entry.getLat()), Double.parseDouble(entry.getLon()));

                } catch (NumberFormatException e){
                    loc = new LatLng(40.7306 + offset, -73.9352 + offset);
                    offset =  offset + .001;
                }
                mainMap.addMarker(new MarkerOptions().position(loc).title(entry.getUniqueKey()).snippet(entry.getAddress()));
            }
            mapProgressBar.setVisibility(View.GONE);





        }


    }

}
