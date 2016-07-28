package com.beauty.client.rest.model;

import com.beauty.client.model.User;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserResponse implements Serializable {
    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }
}
