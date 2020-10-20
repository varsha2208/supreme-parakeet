package com.intercom.hub.model;

import java.io.Serializable;

/**
 * @Author Varsha Chandrashekar on @CreatedOn 19/10/2020
 * Customer is the POJO class holding the input details
 */
public class Customer implements Serializable {

    private String user_id;
    private String name;
    private String latitude;
    private String longitude;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Customer() {
    }

    public Customer(String user_id, String name, String latitude, String longitude) {
        this.user_id = user_id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
