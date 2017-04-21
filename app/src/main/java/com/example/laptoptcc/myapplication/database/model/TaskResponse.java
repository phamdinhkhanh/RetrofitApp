package com.example.laptoptcc.myapplication.database.model;

/**
 * Created by laptopTCC on 4/18/2017.
 */

public class TaskResponse {
    private String id;
    private String name;
    private String color;
    private String done;
    private String local_id;

    public TaskResponse(String name, String color, String done, String local_id) {
        this.name = name;
        this.color = color;
        this.done = done;
        this.local_id = local_id;
    }

    public TaskResponse(String id, String name, String color, String done, String local_id) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.done = done;
        this.local_id = local_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocal_id() {
        return local_id;
    }

    public void setLocal_id(String local_id) {
        this.local_id = local_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }
}
