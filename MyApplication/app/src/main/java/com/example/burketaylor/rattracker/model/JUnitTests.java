package com.example.burketaylor.rattracker.model;

/**
 * Created by burketaylor on 11/14/17.
 */

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.testng.annotations.Test;

public class JUnitTests {
    User user;
    @Before

    @Test
    public void getUserTest() {

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
        Assert.assertEquals("Same username, different password", -1, user1.compareTo(user4));
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
