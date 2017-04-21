package com.example.laptoptcc.myapplication.services;

import com.example.laptoptcc.myapplication.database.model.RegisterResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by laptopTCC on 4/18/2017.
 */

public interface LoginServices {
    @POST("login")
    Call<RegisterResponse> login(@Body RequestBody body);
}
