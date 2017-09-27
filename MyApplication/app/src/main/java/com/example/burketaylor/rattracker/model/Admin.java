package com.example.burketaylor.rattracker.model;

/**
 * Created by Neeraj on 9/26/17.
 */

public class Admin extends User {
    /**
     * Constructor for an admin user
     * @param uN username
     * @param pass password
     */
    public Admin (String uN, String pass) {
        super(uN, pass, false);
    }
}
