package com.example.burketaylor.rattracker.model;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by gecag on 9/26/2017.
 *
 * Class to store users and admins. Has methods to add users and admins to their respective
 * arraylists and also to search the current database for a user with the specified username and
 * password
 * @author Greg
 */
public class Database {


    private static ArrayList<User> userBase = new ArrayList<>();



    /**
     * Adds a user to the database
     *
     * @param user the user that you want to add to the database of users
     */
    public static void addUser(User user) {

        userBase.add(user);
    }
    


    /**
     * Returns user with same username and password as inputted
     *
     * @param username the username of the user that you want search
     * @param password the password of the user that you want to search
     * @return the user with the same username and password as passed in or null if that user doesn't exist
     */
    public static User getUser(String username, String password) {
        User other =  new User(username, password, false);

        for (User current: userBase) {
            if (other.compareTo(current) == 0){ return current;}
        }

        return null;

    }

    /**
     *
     * @param writer
     */
    void saveAsText(PrintWriter writer) {
        System.out.println("Manager saving: " + userBase.size() + " students" );
        writer.println(userBase.size());
        for(User u : userBase) {
            u.saveAsText(writer);
        }
    }

    /**
     * load the model from a custom text file
     *
     * @param reader  the file to read from
     */
    void loadFromText(BufferedReader reader) {
        System.out.println("Loading Text File");
        //studentMap.clear();
        userBase.clear();
        try {
            String countStr = reader.readLine();
            assert countStr != null;
            int count = Integer.parseInt(countStr);

            //then read in each user to model
            for (int i = 0; i < count; ++i) {
                String line = reader.readLine();
                User s = User.parseEntry(line);
                userBase.add(s);
                //studentMap.put(s.getName(), s);
            }
            //be sure and close the file
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done loading text file with " + userBase.size() + " students");

    }

    public static ArrayList<User> getUserBase() {
        return userBase;
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
    }
}
