package com.beauty.client;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.widget.ImageView;
import com.beauty.client.utils.GestureListener;
import com.beauty.client.utils.VolleyLoadPicture;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "TEST";

    private ImageView imageView;

    public static final String picUrl = "http://192.168.1.107:5000/static/img/2013532067.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        //用Volley加载图片
        VolleyLoadPicture vlp = new VolleyLoadPicture(this, imageView);
        vlp.getmImageLoader().get(picUrl, vlp.getOne_listener());

        //需要监听左右滑动事件的view => imageView
        //setLongClickable是必须的
        imageView.setLongClickable(true);
        imageView.setOnTouchListener(new MyGestureListener(this));
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
            return false;
        }

        @Override
        public boolean right() {
            Log.e(TAG, "向右滑");
            return false;
        }
    }
}
