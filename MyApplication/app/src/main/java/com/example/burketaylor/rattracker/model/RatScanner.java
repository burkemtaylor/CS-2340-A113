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

public class RatScanner {


    /**
     *
     * @param in name of desired file
     * @return  map of the all scanned in RatSighting objects
     *
     * @throws IOException if there is an error while reading in the data
     */
    public static HashMap<Integer, RatSighting> scan(InputStream in) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        // read file line by line
        String line = null;
        Scanner scanner = null;

        int index = 0;


        HashMap<Integer, RatSighting> ratSightingMap = new HashMap<Integer, RatSighting>();


        RatSighting rs = new RatSighting();
        int lineIndex = 0;

        while ((line = reader.readLine()) != null) {
            //rs stands for rat sighting

            scanner = new Scanner(line);
            scanner.useDelimiter(",");


            while (scanner.hasNext() && lineIndex > 0) {
                String data = scanner.next();

                if (index == 0) {
                    rs.setUniqueKey(Integer.parseInt(data));

                } else if (index == 1) {
                    rs.setDateTime(data);

                } else if (index == 7) {
                    rs.setLoctype(data);

                } else if (index == 8) {
                    if (!data.equals("")) {
                        rs.setZipCode(Integer.parseInt(data));
                    } else {
                        rs.setZipCode(0);
                    }

                } else if (index ==9) {
                    rs.setAddress(data);

                } else if (index == 16) {
                    rs.setCity(data);

                } else if (index == 23) {
                    rs.setBorough(data);

                } else if (index == 49) {
                    if (!data.equals("")) {
                        rs.setLat(Double.parseDouble(data));
                    } else {
                        rs.setLat(0);
                    }

                } else if (index == 50) {
                    if (!data.equals("")) {
                        rs.setLon(Double.parseDouble(data));
                    } else {
                        rs.setLon(0);
                    }

                }


                index++;
            }
            index = 0;
            ratSightingMap.put(rs.getUniqueKey(), rs);
            Log.d("Current Id", Integer.toString(rs.getUniqueKey()));
            rs = new RatSighting();
            lineIndex++;
        }




        //close reader
        reader.close();

        return ratSightingMap;


    }



}
