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

    @Before
    public void setUp() {


    }

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

    @Test
    public void test2() {

    }

    @Test
    public void test3() {

    }

    @Test
    public void test4() {

    }
}
