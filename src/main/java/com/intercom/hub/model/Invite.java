package com.intercom.hub.model;

import java.io.Serializable;

/**
 * @Author Varsha Chandrashekar on @CreatedOn 19/10/2020
 * Invite is the POJO class for holding the output
 */
public class Invite implements Serializable {

    private Integer user_id;
    private String name;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Invite() {
    }

    public Invite(Integer user_id, String name) {
        this.user_id = user_id;
        this.name = name;
    }
}
