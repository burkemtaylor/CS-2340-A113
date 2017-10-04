package com.example.burketaylor.rattracker.model;
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

}
