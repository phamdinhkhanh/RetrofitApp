package com.example.laptoptcc.myapplication.database.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by laptopTCC on 4/18/2017.
 */

public class RegisterResponse {
    @SerializedName("access_token")
    private String accesstoken;

    public String getAccesstoken() {
        return accesstoken;
    }
}
