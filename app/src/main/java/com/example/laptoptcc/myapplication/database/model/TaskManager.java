package com.example.laptoptcc.myapplication.database.model;

import android.util.Log;

import com.example.laptoptcc.myapplication.database.DbContext;
import com.example.laptoptcc.myapplication.networks.NetContext;
import com.example.laptoptcc.myapplication.services.TaskServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by laptopTCC on 4/19/2017.
 */

public class TaskManager {
    private static final String TAG = "TaskManager";
    private TaskServices taskServices = NetContext.instance.create(TaskServices.class);
    public static final TaskManager instance = new TaskManager();
    private TaskManager(){};

    public void login(LoginCredentials body){
        taskServices.login(body).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                String token = response.body().getAccesstoken();
                if (response.code() == 200){
                    Log.d(TAG,String.format("Login successful, token: %s",token));
                } else {
                    Log.d(TAG,String.format("Login failure"));
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.d(TAG,String.format("Login failure"));
            }
        });
    }

    public void getTaskFromServer(){
        taskServices.getTasks().enqueue(new Callback<List<TaskResponse>>() {
            @Override
            public void onResponse(Call<List<TaskResponse>> call, Response<List<TaskResponse>> response) {
                if (response.code() == 200){
                    for(TaskResponse t: response.body()){
                        Log.d(TAG,String.format("id: %s,Local_id: %s;Name: %s; Color: %s; Isdone: %s",t.getId(),t.getLocal_id(),t.getName(),t.getColor(),t.getDone()));
                    }
                } else {
                    Log.d(TAG,String.format("Not response, code: %s",response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<TaskResponse>> call, Throwable t) {
                    Log.d(TAG,"Not return task");
            }
        });
    }

    public void addNewTask(TaskResponse task){
        taskServices.addNewTask(task).enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                if(response.code() == 200){
                    TaskResponse task = response.body();
                    DbContext.instance.addTask(task);
                    Log.d(TAG,String.format("Name: %s; Color: %s",task.getName(),task.getColor()));
                } else {
                    Log.d(TAG,"Not response");
                }
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                Log.d(TAG,"Failure to add new Task");
            }
        });
    }

    public void deletask(String local_id){
        taskServices.deleteTask(local_id).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                if(response.code() == 200){
                    Log.d(TAG,"delete success");
                } else {
                    Log.d(TAG,"remove not success");
                }
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Log.d(TAG,"Remove Failure");
            }
        });
    }

}
