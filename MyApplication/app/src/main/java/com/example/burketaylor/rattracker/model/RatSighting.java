package com.example.burketaylor.rattracker.model;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.io.Serializable;
/**
 * Created by Ankit on 10/3/17.
 */

public class RatSighting implements Comparable<RatSighting>, Serializable {
    private String uniquekey;
    private String datetime;
    private String loctype;
    private String zipcode;
    private String address;
    private String city;
    private String borough;
    private String latitude;
    private String longitude;
    //private int timeValue;
    /**
     * Constructs a user
     * @param key: uniquekey
     * @param dt: datetime
     * @param lt: loctype;
     * @param zc: zipcode
     * @param addy: address
     * @param ct: city
     * @param bor: borough
     * @param lat: latitude;
     * @param lon: longitude;
     */
    public RatSighting(String key, String dt, String lt, String zc, String addy, String ct, String bor, String lat, String lon) {
        uniquekey = key;
        datetime = dt;
        loctype = lt;
        zipcode = zc;
        address = addy;
        city = ct;
        borough = bor;
        latitude = lat;
        longitude = lon;
        //timeValue = calculateTimeValue(dt);
    }
    public RatSighting() {
        this("", "", "", "", "", "", "", "","");
    }

    /**
     * Returns unique key
     * @return unique key
     */
    public String getUniqueKey() {
        return uniquekey;
    }

    /**
     * sets uniquekey to a new key
     * @param newkey: new key to set key to
     */
    public void setUniqueKey(String newkey) {
        uniquekey = newkey;
    }

    /**
     * Returns date and time
     * @return date time
     */
    public String getDateTime() {
        return datetime;
    }

    /**
     * Returns 8-digit numeric value of date (format: YYYYMMDD)
     * @return numeric value of date
     */
    private int calculateTimeValue(String date) {
        try {
            String[] arr = date.split("/");

            //trim off time details from end of string, leaving only year
            arr[2] = arr[2].substring(0, 4);
            String numericDate = (arr[2].concat(arr[0])).concat(arr[1]);
            return Integer.parseInt(numericDate);
        } catch (Exception e){
            Log.d("RatSightingTimeError", e.getStackTrace().toString());
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            int dateInt = Integer.parseInt(dateFormat.format(Calendar.getInstance()));
            Log.d("FixedSightingError", Integer.toString(dateInt));
            return dateInt;
        }

    }

    /**
     * Returns numeric time value
     * @return time value
     */
    public int getTimeValue() {
        return calculateTimeValue(datetime);
    }

    /**
     * sets uniquekey to a new key
     * @param dt: new datetime to set datetime to
     */
    public void setDateTime(String dt) {
        datetime = dt;
        //timeValue = calculateTimeValue(datetime);
    }

    /**
     * Returns location type
     * @return location type
     */
    public String getLoctype() {
        return loctype;
    }

    /**
     * sets loctype to a new lt
     * @param lt: new loctype to set loctype to
     */
    public void setLoctype(String lt) {
        loctype = lt;
    }

    /**
     * Returns zip code
     * @return zipcode
     */
    public String getZipCode() {
        return zipcode;
    }

    /**
     * sets zipcode to a new zip
     * @param newzip: new zip to set zip to
     */
    public void setZipCode(String newzip) {
        zipcode = newzip;
    }

    /**
     * Returns address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets address to a new address
     * @param addy: new address to set address to
     */
    public void setAddress(String addy) {
        address = addy;
    }

    /**
     * Returns city name
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * sets city to a new city
     * @param cit: new city to set city to
     */
    public void setCity(String cit) {
        city = cit;
    }

    /**
     * Returns borough name
     * @return borough
     */
    public String getBorough() {
        return borough;
    }

    /**
     * sets borough to a new borough
     * @param bor: new borough to set borough to
     */
    public void setBorough(String bor) {
        borough = bor;
    }

    /**
     * Returns latitude
     * @return latitude
     */
    public String getLat() {
        return latitude;
    }

    /**
     * sets latitude to a new latitude
     * @param lat: new lat to set lat to
     */
    public void setLat(String lat) {
        latitude = lat;
    }

    /**
     * Returns longitude
     * @return longitude
     */
    public String getLon() {
        return longitude;
    }

    /**
     * sets longitude to a new key
     * @param lon: new longitude to set longitude to
     */
    public void setLon(String lon) {
        longitude = lon;
    }


    @Override
    public int compareTo(@NonNull RatSighting other) {

        return Integer.compare(this.calculateTimeValue(datetime), other.calculateTimeValue(other.getDateTime()));
    }

    @Override
    public String toString() {
        return "Key: " + uniquekey + "\nTime Added: " + datetime + "\nLocation Type: "
                + loctype + "\nZipcode: " + zipcode + "\nAddress: " + address + "\nBorough: " + borough
                + "\nLatitude: " + latitude + "\nLongitude" + longitude;
    }

    public void saveAsText(PrintWriter writer) {
        System.out.println("RatSighting saving sighting: " + uniquekey);
        writer.println(uniquekey + "," + datetime + ",,,,,," + loctype + "," + zipcode + "," + address + ",,,,,,," + city + ",,,,,,," + borough + ",,,,,,,,,,,,,,,,,,,,,,,,,," + latitude + "," + longitude + ", \n");
    }

    public static RatSighting parseEntry(String line) {
        assert line != null;
        String[] tokens = line.split("\t");
        assert tokens.length == 9;
        return new RatSighting(tokens[0], tokens[1], tokens[7], tokens[8], tokens[9],
                tokens[16], tokens[23], tokens[49], tokens[50]);
        //return r;
    }


}
