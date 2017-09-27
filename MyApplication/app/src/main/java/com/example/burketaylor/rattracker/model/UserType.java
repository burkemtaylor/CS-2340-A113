package com.example.burketaylor.rattracker.model;

import java.io.Serializable;

/**
 * Created by Neeraj on 9/26/17.
 */

public enum UserType implements Serializable {
    USER ("UR"),
    ADMIN ("AD");
    private final String userType;
    /**
     * Constructs the User type
     * @param type: what the userType will be set to
     */
    UserType(String type) {
        userType = type;
    }
    /**
     * gets the userType
     */
    public String getUserType() {
        return userType;
    }
}
