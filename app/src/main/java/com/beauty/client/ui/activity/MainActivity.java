package com.beauty.client.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import com.beauty.client.R;
import com.beauty.client.app.App;
import com.beauty.client.rest.model.UserResponse;
import com.beauty.client.utils.GestureListener;
import com.beauty.client.utils.VolleyLoadPicture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "TEST";

    private ImageView imageView;
    private VolleyLoadPicture vlp;

    public static final String picUrl = "http://192.168.1.107:5000/static/img/2013532067.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        //用Volley加载图片
        vlp = new VolleyLoadPicture(this, imageView);
        vlp.getmImageLoader().get(picUrl, vlp.getOne_listener());

        //需要监听左右滑动事件的view => imageView
        //setLongClickable是必须的
        imageView.setLongClickable(true);
        imageView.setOnTouchListener(new MyGestureListener(this));
    }

    private void left_or_right() {
        Call<UserResponse> call = App.getRestClient().getUserService().randomUser();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // Log.i(TAG, "Success : " + response.body().getUser().getPhoto());
                String photo = response.body().getUser().getPhoto();
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
            left_or_right();
            return false;
        }

        @Override
        public boolean right() {
            Log.e(TAG, "向右滑");
            left_or_right();
            return false;
        }
    }
}
