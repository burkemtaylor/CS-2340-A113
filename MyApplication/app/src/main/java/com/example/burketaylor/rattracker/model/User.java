package com.example.burketaylor.rattracker.model;

import java.io.PrintWriter;

/**
 * Created by Neeraj on 9/19/17.
 */

public class User implements Comparable<User>{
    private String userName;
    private String password;
    private boolean isLocked;
    private UserType userType;

    /**
     * Constructs a user
     * @param uN: username
     * @param pass: password
     * @param locked: isLocked
     */
    public User(String uN, String pass, boolean locked, UserType type) {
        userName = uN;
        password = pass;
        isLocked = locked;
        userType = type;
    }

    /**
     * Constructs a user
     * @param uN: username
     * @param pass: password
     * @param locked: isLocked
     */
    public User(String uN, String pass, boolean locked) {
        this(uN, pass, locked, UserType.USER);
    }

    /**
     * Constructs a user
     * @param uN: username
     * @param pass: password
     */
    public User(String uN, String pass) {
        this(uN, pass, false);
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
    public String getUserName() {
        return userName;
    }
    /**
     * returns password
     */
    public String getPassword() {
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

    /**
     * returns user type
     * @return enum value of user type
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * sets userType to inputted value
     * @param userType userType to be assigned
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public int compareTo(User other) {
        if (other == null) {
            return -1;
        }
        if (other.getUserName().equals(this.userName)
                && other.getPassword().equals(this.password)) {
            return 0;
        } else {
            return -1;
        }
    }

    public void saveAsText(PrintWriter writer) {
        System.out.println("User saving user: " + userName);
        writer.println(userName + "\t" + password);
    }

    public static User parseEntry(String line) {
        assert line != null;
        String[] tokens = line.split("\t");
        assert tokens.length == 2;
        return new User(tokens[0], tokens[1]);
        //return u;
    }
}
