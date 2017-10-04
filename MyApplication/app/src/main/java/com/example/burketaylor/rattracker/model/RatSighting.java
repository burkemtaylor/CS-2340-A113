package com.example.burketaylor.rattracker.model;

/**
 * Created by Ankit on 10/3/17.
 */

public class RatSighting{
    private int uniquekey;
    private String datetime;
    private String loctype;
    private int zipcode;
    private String address;
    private String city;
    private String borough;
    private double latitude;
    private double longitude;
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
    public RatSighting(int key, String dt, String lt, int zc, String addy, String ct, String bor, double lat, double lon) {
        uniquekey = key;
        datetime = dt;
        loctype = lt;
        zipcode = zc;
        address = addy;
        city = ct;
        borough = bor;
        latitude = lat;
        longitude = lon;
    }

    /**
     * returns uniquekey
     */
    private int getUniqueKey() {
        return uniquekey;
    }

    /**
     * sets uniquekey to a new key
     * @param newkey: new key to set key to
     */
    private void setUniqueKey(int newkey) {
        uniquekey = newkey;
    }

    /**
     * returns datetime
     */
    private String getDateTime() {
        return datetime;
    }

    /**
     * sets uniquekey to a new key
     * @param dt: new datetime to set datetime to
     */
    private void setDateTime(String dt) {
        datetime = dt;
    }

    /**
     * returns loctype
     */
    private String getLoctype() {
        return loctype;
    }

    /**
     * sets loctype to a new lt
     * @param lt: new loctype to set loctype to
     */
    private void setLoctype(String lt) {
        loctype = lt;
    }

    /**
     * returns zipcode
     */
    private int getZipCode() {
        return zipcode;
    }

    /**
     * sets zipcode to a new zip
     * @param newzip: new zip to set zip to
     */
    private void setZipCode(int newzip) {
        zipcode = newzip;
    }

    /**
     * returns address
     */
    private String getAddress() {
        return address;
    }

    /**
     * sets address to a new address
     * @param addy: new address to set address to
     */
    private void setAddress(String addy) {
        address = addy;
    }

    /**
     * returns city
     */
    private String getCity() {
        return city;
    }

    /**
     * sets city to a new city
     * @param cit: new city to set city to
     */
    private void setCity(String cit) {
        city = cit;
    }

    /**
     * returns borough
     */
    private String getBorough() {
        return borough;
    }

    /**
     * sets borough to a new borough
     * @param bor: new borough to set borough to
     */
    private void setBorough(String bor) {
        borough = bor;
    }

    /**
     * returns latitude
     */
    private double getLat() {
        return latitude;
    }

    /**
     * sets latitude to a new latitude
     * @param lat: new lat to set lat to
     */
    private void setLat(double lat) {
        latitude = lat;
    }

    /**
     * returns longitude
     */
    private double getLon() {
        return longitude;
    }

    /**
     * sets longitude to a new key
     * @param lon: new longitude to set longitude to
     */
    private void setLon(double lon) {
        longitude = lon;
    }


}
