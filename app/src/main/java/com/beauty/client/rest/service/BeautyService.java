package com.beauty.client.rest.service;

import com.beauty.client.rest.model.SimpleResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BeautyService {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/v1.0/beauties")
    Call<SimpleResponse> create_beauty(@Body RequestBody body);
}
