package com.example.burketaylor.rattracker.model;

/**
 * Created by Neeraj on 9/19/17.
 */

public class User implements Comparable<User>{
    private String userName;
    private String password;
    private boolean isLocked;
    /**
     * Constructs a user
     * @param uN: username
     * @param pass: password
     * @param locked: isLocked
     */
    public User(String uN, String pass, boolean locked) {
        userName = uN;
        password = pass;
        isLocked = locked;
    }
    /**
     * Constructs a user
     */
    public User() {
        userName = "user";
        password = "pass";
        isLocked = false;
    }
    /**
     * Logs in to account if username and password are correct
     * @param user: username
     * @param pass: password
     */
    public boolean logIn(String user, String pass) {
        if (getUserName().equals(user) && getPassword().equals(pass)) {
            return true;
        }
        return false;
    }
    /**
     * returns user name
     */
    private String getUserName() {
        return userName;
    }
    /**
     * returns password
     */
    private String getPassword() {
        return password;
    }
    /**
     * sets user to user name
     * @param user: user name to change to
     */
    private void setUserName(String user) {
        userName = user;
    }
    /**
     * sets pass to password
     * @param pass: password to change to
     */
    private void setPassword(String pass) {
        password = pass;
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
