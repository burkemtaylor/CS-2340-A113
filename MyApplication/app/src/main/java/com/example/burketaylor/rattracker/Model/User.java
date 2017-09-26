package com.example.burketaylor.rattracker.model;

/**
 * Created by Neeraj on 9/19/17.
 */

public class User implements Comparable<User>{
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

    private String getUserName() {
        return userName;
    }

    private String getPassword() {
        return password;
    }

    @Override
    public int compareTo(User other) {
        if (other.getUserName().equals(this.userName)
                && other.getPassword().equals(this.password)) {
            return 0;
        } else {
            return -1;
        }
    }
}
