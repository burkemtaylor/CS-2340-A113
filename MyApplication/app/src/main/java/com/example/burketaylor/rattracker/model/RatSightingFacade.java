package com.example.burketaylor.rattracker.model;

import android.util.Log;

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
    public static final String DEFAULT_BINARY_FILE_NAME = "data.bin";

    private static RatSightingFacade instance = new RatSightingFacade();

    public boolean loadBinary(File file) {
        boolean success = true;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            rsd = (RatSightingDatabase) in.readObject();
            Log.d("read", "READ");
            if (rsd != null) {
                rsd.regenMap();
                Log.d("notNULL", "notNULL");
            }
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
            Log.d("write", "WRITE");
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
