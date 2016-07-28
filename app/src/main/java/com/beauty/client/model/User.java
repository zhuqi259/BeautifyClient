package com.beauty.client.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("gender")
    private int gender;
    @SerializedName("department")
    private String department;
    @SerializedName("major")
    private String major;
    @SerializedName("teacher")
    private String teacher;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("email")
    private String email;
    @SerializedName("uri")
    private String uri;
    @SerializedName("photo")
    private String photo;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public String getMajor() {
        return major;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getUri() {
        return uri;
    }

    public String getPhoto() {
        return photo;
    }
}
