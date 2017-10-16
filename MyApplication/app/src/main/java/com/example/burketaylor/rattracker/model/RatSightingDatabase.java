package com.example.burketaylor.rattracker.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Stores the Rat Sightings already reported in a hashmap
 * Created by burketaylor on 10/3/17.
 */

public class RatSightingDatabase {
    private static HashMap<String, RatSighting> ratSightingMap = new HashMap<>();
    private static String lastSelected = null;

    /**
     * Constructor for Rat Sighting Database
     * @param in input data stream
     * @throws IOException if input stream is invalid
     */
    public RatSightingDatabase(InputStream in) throws IOException {
        //try {
            ratSightingMap = RatScanner.scan(in);
        //} catch (IOException e) {
        //    ratSightingMap = new HashMap<>(1);
        //    ratSightingMap.put(0, new RatSighting());
        //}
    }

    public static HashMap<String, RatSighting> getMap() {
        return ratSightingMap;
    }

    public static void setLastSelected(String s) {
        lastSelected = s;
    }

    public static String getLastSelected() {
        return lastSelected;
    }

    public static boolean isEmpty() {
        return ratSightingMap.isEmpty();
    }
}
