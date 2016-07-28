package com.beauty.client.rest;

import com.beauty.client.rest.service.BeautyService;
import com.beauty.client.rest.service.UserService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static final String BASE_URL = "http://192.168.1.107:5000/";
    private UserService userService;
    private BeautyService beautyService;

    public RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
        beautyService = retrofit.create(BeautyService.class);
    }


    public UserService getUserService() {
        return userService;
    }

    public BeautyService getBeautyService() {
        return beautyService;
    }
}
