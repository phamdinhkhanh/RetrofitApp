package com.example.laptoptcc.myapplication.database.model;

/**
 * Created by laptopTCC on 4/19/2017.
 */

public class DeleteResponse {
    private String Delete;

    public DeleteResponse(String delete) {
        Delete = delete;
    }

    public String getDelete() {
        return Delete;
    }

    public void setDelete(String delete) {
        Delete = delete;
    }
}
