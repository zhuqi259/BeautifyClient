package com.beauty.client.rest.service;

import com.beauty.client.rest.model.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("api/v1.0/users/random")
    Call<UserResponse> randomUser();
}