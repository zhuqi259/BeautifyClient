package com.beauty.client.rest.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SimpleResponse implements Serializable {
    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }
}
