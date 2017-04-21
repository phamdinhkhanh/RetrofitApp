package com.example.laptoptcc.myapplication.services;

import com.example.laptoptcc.myapplication.database.model.DeleteResponse;
import com.example.laptoptcc.myapplication.database.model.LoginCredentials;
import com.example.laptoptcc.myapplication.database.model.RegisterResponse;
import com.example.laptoptcc.myapplication.database.model.TaskResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by laptopTCC on 4/19/2017.
 */

public interface TaskServices {
    @POST("login")
    Call<RegisterResponse> login(@Body LoginCredentials body);

    @POST("tasks")
    Call<TaskResponse> addNewTask(@Body TaskResponse task);

    @GET("tasks")
    Call<List<TaskResponse>> getTasks();

    @DELETE("tasks/{id}")
    Call<DeleteResponse> deleteTask(@Path("id") String Id);
}
