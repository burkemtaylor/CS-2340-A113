package com.example.burketaylor.rattracker.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Greg
 * Manages the rat reports that will be shown on Google Maps
 */

public class MapEntryManager {

    private static ArrayList<RatSighting> lastDateEntries = new ArrayList<>();
    private static ArrayList<RatSighting> lastNumberEntries = new ArrayList<>();
    private static int lastRange = 1;
    private static int lastStartDate = 10000000;
    private static int lastEndDate = 10000000;

    public static ArrayList<RatSighting> getReportsByDate(int startDate, int endDate) {
        if (startDate == lastStartDate & endDate ==lastEndDate) {
            return lastDateEntries;
        } else {
            List<Entry<String, RatSighting>> reportList = sortReports(RatSightingDatabase.getMap());
            ArrayList<RatSighting> out = new ArrayList<>();

            for (int i = 0; i < reportList.size(); i++) {
                RatSighting sighting = reportList.get(i).getValue();
                int date = sighting.getTimeValue();
                if (date >= startDate && date <= endDate) {
                    out.add(sighting);
                }
                Log.d("FINDING", "RANGE YUH");
            }
            lastDateEntries = out;
            lastStartDate = startDate;
            lastEndDate = endDate;


            return out;
        }




    }

    public static ArrayList<RatSighting> getReportsByRange(int range) {
        if (range == lastRange) {
            return lastNumberEntries;
        } else {
            List<Entry<String, RatSighting>> reportList = sortReports(RatSightingDatabase.getMap());
            ArrayList<RatSighting> out = new ArrayList<>();

            for (int i = reportList.size() - (range + 1); i < reportList.size(); i++) {

                out.add(reportList.get(i).getValue());
            }
            lastNumberEntries = out;
            lastRange = range;
            return out;
        }

    }

    public static List<Entry<String, RatSighting>> sortReports(HashMap<String, RatSighting> mapInput){
        HashMap<String, RatSighting> map = mapInput;
        Set<Entry<String, RatSighting>> entries = map.entrySet();


        Comparator<Entry<String, RatSighting>> comparator = new Comparator<Entry<String, RatSighting>>() {
            @Override
            public int compare(Entry<String, RatSighting> e1, Entry<String, RatSighting> e2) {
                RatSighting sighting1 = e1.getValue();
                RatSighting sighting2 = e2.getValue();
                return sighting1.compareTo(sighting2);
            }
        };

        List<Entry<String, RatSighting>> reportList = new ArrayList<>(entries);
        Collections.sort(reportList, comparator);
        return reportList;

    }

    public static ArrayList<RatSighting> getReportbyMonth(int year, int month) {

        int numDays = 31;

        switch(month) {

            case 1: case 3: case 5:
            case 7: case 8: case 10:
            case 12:
                numDays = 31;
                break;
            case 4: case 6:
            case 9: case 11:
                numDays = 30;
                break;

            case 2:
                if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)) {
                    numDays = 29;
                }
                else {
                    numDays = 28;
                }
                break;

        }
        String startDate = Integer.toString(year) + Integer.toString(month) + "01";
        String endDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(numDays);
        return getReportsByDate(Integer.parseInt(startDate), Integer.parseInt(endDate));

    }
}
