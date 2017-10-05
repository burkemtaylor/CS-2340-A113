package com.example.burketaylor.rattracker.model;

import java.io.IOException;
import java.util.HashMap;

/**
 * Stores the Rat Sightings already reported in a hashmap
 * Created by burketaylor on 10/3/17.
 */

public class RatSightingDatabase {
    private static HashMap<Integer, RatSighting> ratSightingMap= new HashMap<>();

    public RatSightingDatabase() {
        try {
            ratSightingMap = RatScanner.scan("dummy");
        } catch (IOException e) {
            ratSightingMap = new HashMap<>(1);
            ratSightingMap.put(0, new RatSighting());
        }
    }

    public static HashMap<Integer, RatSighting> getMap() {
        return ratSightingMap;
    }
}
