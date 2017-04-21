package com.example.laptoptcc.myapplication.database;

import android.util.Log;

import com.example.laptoptcc.myapplication.database.model.TaskResponse;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by laptopTCC on 4/19/2017.
 */

public class DbContext {
    private String TAG = "DbContext";
    private List<TaskResponse> tasks;
    public static final DbContext instance = new DbContext();
    public DbContext() {
        tasks = new ArrayList<>();
    }

    public List<TaskResponse> getTask(){
        return tasks;
    }

    public void addTask(TaskResponse newTask){
        tasks.add(newTask);
    }

    public void removeTask(TaskResponse removeTask){
        if(removeTask.getLocal_id() == null){
            Log.d(TAG,String.format("null local_id to removed"));
        } else {
            for(TaskResponse task: tasks){
                if(removeTask.getLocal_id() == task.getLocal_id()){
                    tasks.remove(removeTask);
                } else {
                    Log.d(TAG,String.format("Can't remove task"));
                }
            }
        }
    }

    public void editTask(TaskResponse editTask) {
        if (editTask.getLocal_id() == null) {
            Log.d(TAG, String.format("null local_id to removed"));
        } else {
            for (TaskResponse task : tasks) {
                if (editTask.getLocal_id() == task.getLocal_id()) {
                    task.setColor(editTask.getColor());
                    task.setName(editTask.getName());
                    task.setDone(editTask.getDone());
                } else {
                    Log.d(TAG, String.format("Can't edit task"));
                }
            }
        }
    }

}

