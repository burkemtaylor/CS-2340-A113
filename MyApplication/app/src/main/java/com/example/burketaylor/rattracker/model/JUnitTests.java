package com.example.burketaylor.rattracker.model;

/**
 * Created by burketaylor on 11/14/17.
 */

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class JUnitTests {
    @Before

    @Test
    public void getUserTest() {

    }

    @Test
    public void test2() {

    }

    @Test
    public void test3() {

    }

    /**
     * Gregory Cage's test
     * Tests the sortReports() method in MapEntryManager. Creates an arraylist in sorted order and compares it to the
     * result from the method
     */
    @Test
    public void sortReportsTest() {
        HashMap<String, RatSighting> testMap = new HashMap<>();
        testMap.put("Sighting1", new RatSighting("1",
                "11/15/2015 12:00:00 AM", "loc",
                "00000", "10 address", "New York", "Manhatten",
                "40.70777155363643", "-74.01296309970473"));
        testMap.put("Sighting2", new RatSighting("2",
                "09/05/2015 12:00:00 AM", "loc",
                "00000", "10 address", "New York", "Manhatten",
                "40.70777155363643", "-74.01296309970473"));
        testMap.put("Sighting3", new RatSighting("3",
                "10/04/2014 12:00:00 AM", "loc",
                "00000", "10 address", "New York", "Manhatten",
                "40.70777155363643", "-74.01296309970473"));
        testMap.put("Sighting4", new RatSighting("4",
                "09/04/2017 12:00:00 AM", "loc",
                "00000", "10 address", "New York", "Manhatten",
                "40.70777155363643", "-74.01296309970473"));
        testMap.put("Sighting5", new RatSighting("5",
                "06/04/2016 12:00:00 AM", "loc",
                "00000", "10 address", "New York", "Manhatten",
                "40.70777155363643", "-74.01296309970473"));

        ArrayList<RatSighting>  listTest = new ArrayList<>();
        listTest.add(new RatSighting("3",
                "10/04/2014 12:00:00 AM", "loc",
                "00000", "10 address", "New York", "Manhatten",
                "40.70777155363643", "-74.01296309970473"));
        listTest.add(new RatSighting("2",
                "09/05/2015 12:00:00 AM", "loc",
                "00000", "10 address", "New York", "Manhatten",
                "40.70777155363643", "-74.01296309970473"));
        listTest.add(new RatSighting("1",
                "11/15/2015 12:00:00 AM", "loc",
                "00000", "10 address", "New York", "Manhatten",
                "40.70777155363643", "-74.01296309970473"));
        listTest.add(new RatSighting("5",
                "06/04/2016 12:00:00 AM", "loc",
                "00000", "10 address", "New York", "Manhatten",
                "40.70777155363643", "-74.01296309970473"));
        listTest.add(new RatSighting("4",
                "09/04/2017 12:00:00 AM", "loc",
                "00000", "10 address", "New York", "Manhatten",
                "40.70777155363643", "-74.01296309970473"));

        Assert.assertEquals("These array list should be equal", listTest, MapEntryManager.sortReports(testMap));


    }
}
