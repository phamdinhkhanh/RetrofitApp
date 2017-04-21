package com.example.laptoptcc.myapplication.utils;

/**
 * Created by laptopTCC on 4/19/2017.
 */

import java.util.UUID;

public class Util {
    public static final Util instance = new Util();
    private static final String TAG = "Util";
    public String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
