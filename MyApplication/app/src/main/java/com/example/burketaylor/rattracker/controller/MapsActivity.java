package com.example.burketaylor.rattracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.burketaylor.rattracker.model.RatSighting;
import com.example.burketaylor.rattracker.model.RatSightingDatabase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.burketaylor.rattracker.R;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap mainMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
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
                markerOptions.title("HELLLOOOO");
                markerOptions.snippet("WOOOORLLD!!!!");

                mainMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mainMap.addMarker(markerOptions);
            }
        });

        RatSighting rat = RatSightingDatabase.getMap().get(RatSightingDatabase.getLastSelected());
        LatLng loc = new LatLng(Double.parseDouble(rat.getLat()),Double.parseDouble(rat.getLon()));
        mainMap.addMarker(new MarkerOptions().position(loc).title(rat.getUniqueKey()).snippet(rat.getAddress()));

        mainMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        mainMap.moveCamera(CameraUpdateFactory.zoomTo(15.0f));

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

}
