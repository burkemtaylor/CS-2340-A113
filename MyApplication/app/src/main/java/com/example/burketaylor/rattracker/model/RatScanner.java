package com.example.burketaylor.rattracker.model;

/**
 * Created by gecag on 10/3/2017.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class RatScanner {

    public static HashMap<Integer, RatSighting> scan(String file) throws FileNotFoundException, IOException {


        BufferedReader reader = new BufferedReader(new FileReader(file));

        // read file line by line
        String line = null;
        Scanner scanner = null;

        int index = 0;


        HashMap<Integer, RatSighting> ratSightingMap = new HashMap<Integer, RatSighting>();


        RatSighting rs = new RatSighting();

        while ((line = reader.readLine()) != null) {
            //rs stands for rat sighting

            scanner = new Scanner(line);
            scanner.useDelimiter(",");


            while (scanner.hasNext()) {
                String data = scanner.next();

                if (index == 0) {
                    rs.setUniqueKey(Integer.parseInt(data));

                } else if (index == 1) {
                    rs.setDateTime(data);

                } else if (index == 7) {
                    rs.setLoctype(data);

                } else if (index == 8) {
                    rs.setZipCode(Integer.parseInt(data));

                } else if (index ==9) {
                    rs.setAddress(data);

                } else if (index == 16) {
                    rs.setCity(data);

                } else if (index == 23) {
                    rs.setBorough(data);

                } else if (index == 49) {
                    rs.setLat(Double.parseDouble(data));

                } else if (index == 50) {
                    rs.setLon(Double.parseDouble(data));

                }


                index++;
            }
            index = 0;
        }

        ratSightingMap.put(rs.getUniqueKey(), rs);


        //close reader
        reader.close();

        return ratSightingMap;


    }



}
