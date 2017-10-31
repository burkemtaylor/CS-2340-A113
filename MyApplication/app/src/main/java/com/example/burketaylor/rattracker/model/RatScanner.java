package com.example.burketaylor.rattracker.model;

/**
 * Created by gecag on 10/3/2017.
 */

import android.util.Log;

import com.example.burketaylor.rattracker.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.HashMap;

import static com.example.burketaylor.rattracker.model.RatSightingDatabase.*;

public class RatScanner {


    /**
     *
     * @param in name of desired file
     * @return  map of the all scanned in RatSighting objects
     *
     * @throws IOException if there is an error while reading in the data
     */
    public static HashMap<String, RatSighting> scan(InputStream in) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        // read file line by line
        String line;

        HashMap<String, RatSighting> ratSightingMap = new HashMap<>();


        RatSighting rs;
        int lineIndex = 0;

        while ((line = reader.readLine()) != null) {
            if (lineIndex > 0) {
                String[] tokens = line.split(",", -1);
                rs = new RatSighting(tokens[0], tokens[1], tokens[7], tokens[8], tokens[9],
                        tokens[16], tokens[23], tokens[49], tokens[50]);
                ratSightingMap.put(rs.getUniqueKey(), rs);
                //addToList(rs);
                Log.d("Current Id", rs.getUniqueKey());
            } else {
                lineIndex++;
            }

            //rs stands for rat sighting


        }

        //close reader
        reader.close();

        return ratSightingMap;


    }



}
