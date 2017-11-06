package com.example.burketaylor.rattracker.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burketaylor on 11/5/17.
 */

public class GraphDataManager {
    private static ArrayList<GraphData> data = new ArrayList<>();

    public static void generateGraphData(int startDate, int endDate) {
        //date in format YYYYMMDD
        //ArrayList<RatSighting> ratSightingRange = MapEntryManager.getReportsByDate(startDate, endDate);

        int startMonth = (startDate / 100) % 100;
        int startYear = startDate / 10000;
        int endMonth = (endDate / 100) % 100;
        int endYear = endDate / 10000;

        int months = (endMonth - startMonth) + (endYear - startYear) * 12 + 1;

        ArrayList<RatSighting> temp = new ArrayList<>();

        for (int i = 0; i < months; i++) {
            temp = MapEntryManager.getReportbyMonth(startYear, startMonth);

            int monthYear = startMonth * 10000 + startYear;

            data.add(new GraphData(monthYear, temp.size()));
            Log.d("ADDING ENTRY", String.valueOf(monthYear));

            if (startMonth == 12) {
                startMonth = 1;
                startYear++;
            } else {
                startMonth ++;
            }
        }
        //GraphData : monthYear, sightings
    }

    public static List<GraphData> getDataList() {
        return data;
    }

    public static void clear() {
        data = new ArrayList<>();
    }
}
