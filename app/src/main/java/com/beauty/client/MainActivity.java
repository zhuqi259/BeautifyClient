package com.beauty.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.beauty.client.utils.VolleyLoadPicture;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    public static final String picUrl = "http://img.quwenjiemi.com/2014/0701/thumb_420_234_20140701112917406.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        //用Volley加载图片
        VolleyLoadPicture vlp = new VolleyLoadPicture(this, imageView);
        vlp.getmImageLoader().get(picUrl, vlp.getOne_listener());
    }
}
