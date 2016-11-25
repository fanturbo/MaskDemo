package com.test.maskdemo;

import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private WindowManager windowManager;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initMask();
            }
        });
    }

    //MaskDemo第二种方式
    private void initMask() {
        windowManager = getWindowManager();
        view = LayoutInflater.from(this).inflate(R.layout.layout_dialog_second, null);
        // 设置LayoutParams参数
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        //兼容6.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
        } else {
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        // 设置显示格式
        params.format = PixelFormat.RGBA_8888;
        // 设置对齐方式
        params.gravity = Gravity.LEFT | Gravity.TOP;
        // 设置宽高
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        // 添加到当前的窗口上
        windowManager.addView(view, params);
        // 点击图层之后，将图层移除
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                windowManager.removeView(view);
                Toast.makeText(SecondActivity.this, "Image dismiss", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
