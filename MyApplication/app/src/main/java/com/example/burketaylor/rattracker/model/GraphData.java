package com.example.burketaylor.rattracker.model;

/**
 * Created by burketaylor on 11/5/17.
 */

public class GraphData {
    private int monthYear;
    private int sightings;

    public GraphData() {
        monthYear = 0;
        sightings = 0;
    }

    public GraphData(int mY, int s) {
        monthYear = mY;
        sightings = s;
    }

    public int getMonthYear() {
        return monthYear;
    }

    public int getSightings() {
        return sightings;
    }
}
