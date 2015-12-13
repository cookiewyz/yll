package com.xiaoliwu.yll.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

public class GeRenActivity extends AppCompatActivity {
    private EditText et;
    private TextView tx;
    private String name;
    private ImageView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_ge_ren);
        getSupportActionBar().hide();
        et= (EditText) findViewById(R.id.et);
        tx= (TextView) findViewById(R.id.tx);
        tv1= (ImageView) findViewById(R.id.tv1);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= et.getText().toString();
                //实例化SharedPreferences对象（第二步）
                SharedPreferences mySharedPreferences = getSharedPreferences("zhanghao",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = mySharedPreferences.edit();
//用putString的方法保存数据
                editor.putString("nickname", name);
//提交当前数据
                editor.apply();
                finish();
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    private void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        // 创建状态栏的管理实例
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(0);//状态栏无背景
        // 设置一个颜色给系统栏
        tintManager.setTintColor(Color.parseColor("#F84E4E"));
    }

}
