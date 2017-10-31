package com.example.burketaylor.rattracker.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Stores the Rat Sightings already reported in a hashmap
 * Created by burketaylor on 10/3/17.
 */

public class RatSightingDatabase implements Serializable {
    private List<RatSighting> rsList = new ArrayList<>();
    private static HashMap<String, RatSighting> ratSightingMap = new HashMap<>();
    private static String lastSelected = null;
    public static int nextKey = 50000000;

    private static RatSightingDatabase instance = new RatSightingDatabase();

    /**
     * Constructor for Rat Sighting Database
     * @param in input data stream
     * @throws IOException if input stream is invalid
     */
    public RatSightingDatabase(InputStream in) throws IOException {
        //try {
            ratSightingMap = RatScanner.scan(in);
            Log.d("finished", "scanning");
            //rsList = Arrays.asList((RatSighting[]) ratSightingMap.values().toArray());
            Log.d("list", "populated");
        //} catch (IOException e) {
        //    ratSightingMap = new HashMap<>(1);
        //    ratSightingMap.put(0, new RatSighting());
        //}

        lastSelected = "31464015";
    }

    public RatSightingDatabase() {
        ratSightingMap = ratSightingMap;
        rsList = rsList;
    }

    public RatSightingDatabase(RatSightingDatabase instance) {
        ratSightingMap = instance.getMap();
        rsList = instance.getList();
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

    public boolean addRatSighting(RatSighting r) {
        r.setUniqueKey(Integer.toString(nextKey + 1));

        try {
            ratSightingMap.put(Integer.toString(nextKey + 1), r);
            rsList.add(r);
            nextKey++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void addToList(RatSighting r) {
        rsList.add(r);
    }

    public List getList() {
        return rsList;
    }

    public static boolean isEmpty() {
        return ratSightingMap.isEmpty();
    }

    /*/**
     *
     * @param writer
     */
    /*void saveAsText(PrintWriter writer) {
        System.out.println("RSDatabase saving: " + ratSightingMap.size() + " sightings" );
        writer.println(ratSightingMap.values().toArray().length);

        for(Object r : ratSightingMap.values().toArray()) {
            ((RatSighting) r).saveAsText(writer);
        }
    }

    /**
     * load the model from a custom text file
     *
     * @param reader  the file to read from
     */
    /*void loadFromText(BufferedReader reader) {
        System.out.println("Loading Text File");
        //studentMap.clear();
        ratSightingMap.clear();
        try {
            String countStr = reader.readLine();
            assert countStr != null;
            int count = Integer.parseInt(countStr);

            //then read in each user to model
            for (int i = 0; i < count; ++i) {
                String line = reader.readLine();
                RatSighting r = RatSighting.parseEntry(line);
                ratSightingMap.put(r.getUniqueKey(), r);
                //studentMap.put(s.getName(), s);
            }
            //be sure and close the file
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done loading text file with " + ratSightingMap.size() + " sightings");

    }

    public boolean loadText(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            this.loadFromText(reader);
        } catch (FileNotFoundException e) {
            Log.e("ModelSingleton", "Failed to open file");
            return false;
        }

        return true;
    }

    public boolean saveText(File file) {
        System.out.println("Saving as a text file");
        try {
            PrintWriter pw = new PrintWriter(file);
            saveAsText(pw);
            pw.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            Log.d("Database", "Error opening the text file for save!");
            return false;
        }

        return true;
    }*/

    void regenMap() {
        if (ratSightingMap != null) {
            ratSightingMap.clear();
        } else {
            ratSightingMap = new HashMap<String, RatSighting>();
        }
        for (RatSighting r : rsList)
            ratSightingMap.put(r.getUniqueKey(), r);
            Log.d("regen", "REGEN");
    }

    public static RatSightingDatabase getInstance() {
        return instance;
    }
}
