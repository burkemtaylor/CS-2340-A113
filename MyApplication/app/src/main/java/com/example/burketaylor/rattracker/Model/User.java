package com.example.burketaylor.rattracker.model;

/**
 * Created by Neeraj on 9/19/17.
 */

public class User {
    private String userName;
    private String password;
    private boolean isLocked;
    public User(String uN, String p, boolean x) {
        userName = uN;
        password = p;
        isLocked = x;
    }
    public User() {
        userName = "user";
        password = "pass";
        isLocked = false;
    }

    public boolean logIn(String user, String pass) {
        if (userName.equals(user) && password.equals(pass)) {
            return true;
        }
        return false;
    }
}
