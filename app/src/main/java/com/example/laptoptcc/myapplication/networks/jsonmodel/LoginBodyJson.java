package com.example.laptoptcc.myapplication.networks.jsonmodel;

/**
 * Created by laptopTCC on 4/19/2017.
 */

public class LoginBodyJson {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginBodyJson(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
