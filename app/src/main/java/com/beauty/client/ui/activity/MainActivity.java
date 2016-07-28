package com.beauty.client.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import com.beauty.client.R;
import com.beauty.client.app.App;
import com.beauty.client.model.User;
import com.beauty.client.rest.model.SimpleRequest;
import com.beauty.client.rest.model.SimpleResponse;
import com.beauty.client.rest.model.UserResponse;
import com.beauty.client.utils.GestureListener;
import com.beauty.client.utils.VolleyLoadPicture;
import com.google.gson.Gson;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "TEST";

    private ImageView imageView;
    private VolleyLoadPicture vlp;

    // private static final String picUrl = "http://192.168.1.107:5000/static/img/2013532067.jpg";

    private static String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        //用Volley加载图片
        vlp = new VolleyLoadPicture(this, imageView);

        random_user();

        // vlp.getmImageLoader().get(picUrl, vlp.getOne_listener());

        //需要监听左右滑动事件的view => imageView
        //setLongClickable是必须的
        imageView.setLongClickable(true);
        imageView.setOnTouchListener(new MyGestureListener(this));
    }

    private void random_user() {
        Call<UserResponse> call = App.getRestClient().getUserService().randomUser();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // Log.i(TAG, "Success : " + response.body().getUser().getPhoto());
                User user = response.body().getUser();
                String photo = user.getPhoto();
                user_id = user.getId();
                vlp.getmImageLoader().get(photo, vlp.getOne_listener());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e(TAG, "Error : " + t.getMessage());
            }
        });
    }

    /**
     * 继承GestureListener，重写left和right方法
     */
    private class MyGestureListener extends GestureListener {
        public MyGestureListener(Context context) {
            super(context);
        }

        @Override
        public boolean left() {
            Log.e(TAG, "向左滑");
            random_user();
            return false;
        }

        @Override
        public boolean right() {
            Log.e(TAG, "向右滑");
            random_user();

            SimpleRequest bean = new SimpleRequest(user_id);
            Gson gson = new Gson();
            String request = gson.toJson(bean);//通过Gson将Bean转化为Json字符串形式
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
            Call<SimpleResponse> call = App.getRestClient().getBeautyService().create_beauty(body);
            call.enqueue(new Callback<SimpleResponse>() {
                @Override
                public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                    Log.i(TAG, "Success : " + response.body().getStatus());
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t) {
                    Log.e(TAG, "Error : " + t.getMessage());
                }
            });
            return false;
        }
    }
}
