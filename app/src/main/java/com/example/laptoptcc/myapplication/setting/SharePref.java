package com.example.laptoptcc.myapplication.setting;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.laptoptcc.myapplication.database.model.LoginCredentials;
import com.google.gson.Gson;

/**
 * Created by laptopTCC on 4/18/2017.
 */

public class SharePref {

    private static final String SHARED_PREFS_NAME = "SP";
    private static final String LOGIN_KEY = "LOGIN";

    private static final String SETTING_KEY = "SETTING";
    private SharedPreferences sharedPreferences;
    Gson gson ;
    private String accessToken;
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static SharePref instance;

    public static  void init(Context context){
        instance  = new SharePref(context);
    }
    private SharePref(Context context) {
        this.sharedPreferences = context.getSharedPreferences(
                SHARED_PREFS_NAME,
                Context.MODE_PRIVATE
        );
        gson = new Gson();
    }
    public void put(LoginCredentials loginCredentials){

        String loginJson = gson.toJson(loginCredentials);
        sharedPreferences.edit().putString(LOGIN_KEY, loginJson).commit();
    }
    public void putSave(){

    }
    //    public void putUsernamePass(LoginCredentials loginCredentials){
//        String loginJson  = gson.toJson(loginCredentials);
//    }
    public LoginCredentials getLoginCredentials(){
        String loginJson = sharedPreferences.getString(LOGIN_KEY, null);
        if (loginJson == null)
        {
            return null;
        }
        LoginCredentials loginCredentials  = gson.fromJson(loginJson, LoginCredentials.class);

        return  loginCredentials;
    }


    public String getAccessToken() {
        if (getLoginCredentials() != null)
        {
            return getLoginCredentials().getAccessToken();
        }
        return null;
    }
}
