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

    @Test
    public void testUserCompareTo() {
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
