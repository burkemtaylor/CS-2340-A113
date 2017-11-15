package com.example.burketaylor.rattracker.model;

/**
 * Created by burketaylor on 11/14/17.
 */

import android.util.Log;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.testng.annotations.Test;

public class JUnitTests {

    Database database = new Database();
    User user;

    /*
     * Test getUser within Database
     * author: Burke Taylor
     */
    @Test
    public void getUserTest() {
        database.addUser(new User("Jeff", "pass1234"));

        //user Exists
        try {
            User testUser = database.getUser("Jeff", "pass1234");
            Assert.assertEquals("Usernames not equal", "Jeff", testUser.getUserName());
            Assert.assertEquals("Passwords not equal", "pass1234", testUser.getPassword());
        } catch (Exception e) {
            Log.d("getUserError", "Should be no exception getting User");
        }

        //user doesn't exist
        try {
            User testUser = database.getUser("Jef", "pass1234");
            Assert.assertEquals("User should return null", null, testUser);
        } catch (Exception e) {
            Log.d("getUserError", "Should be no exception getting User");
        }

        //user is null
        try {
            User testUser = database.getUser(null, null);
            Assert.assertEquals("User should return null", null, testUser);
        } catch (Exception e) {
            Log.d("getUserError", "Should be no exception getting User");
        }

    }

    /**
     * Tests compareTo method for User class
     * Made by Alan Hoang, 11/14/2017
     */
    @Test
    public void testUserCompareTo() {
        User user1 = new User("joseph", "password");
        User user2 = new User("joseph", "password");
        User user3 = new User("robert","differentpassword");
        User user4 = new User("joseph", "differentpassword");

        Assert.assertEquals("Same username & password", 0, user1.compareTo(user2));
        Assert.assertEquals("Different username & password", -1, user1.compareTo(user3));
        Assert.assertEquals("Same username, different password", 0, user1.compareTo(user4));
        Assert.assertEquals("Different username, same password", -1, user3.compareTo(user4));
        Assert.assertEquals("Comparing with null", -1, user1.compareTo(null));
    }

    /*
    Tests logIn(String user, String pass) in User, returns boolean on whether or not it works
    Made By Neeraj Sabapathy
     */
    @Test
    public void logIn() {
        user = new User("user", "pass");
        Assert.assertEquals("Returns false", true, user.logIn("user", "pass"));
        Assert.assertEquals("Returns true", false, user.logIn("pass", "user"));
        Assert.assertEquals("Returns true", false, user.logIn(null, null));
    }

    @Test
    public void sortReportsTest() {

    }
}
