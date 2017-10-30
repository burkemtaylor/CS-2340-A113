package com.example.burketaylor.rattracker.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by burketaylor on 10/29/17.
 */

public class RatSightingFacade {
    private RatSightingDatabase rsd;
    public final static String DEFAULT_BINARY_FILE_NAME = "new_rat_sightings.bin";

    private static RatSightingFacade instance = new RatSightingFacade();

    public boolean loadBinary(File file) {
        boolean success = true;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            rsd = (RatSightingDatabase) in.readObject();
            /*if (rsd.getMap() != null) {
                rsd.regenMap(rsd.getMap());
            }*/
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }

    public boolean saveBinary(File file) {
        boolean success = true;

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(rsd);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }

    public static RatSightingFacade getInstance() {
        return instance;
    }
}
