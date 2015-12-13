package com.xiaoliwu.yll.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

public class XingBieActivity extends AppCompatActivity {
    private ImageView tv1,image1,image2;
    SharedPreferences sharedPreferences;
    private String sex;
    private RelativeLayout xingbie2,xingbie3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_xing_bie);
        tv1= (ImageView) findViewById(R.id.tv1);
        image1= (ImageView) findViewById(R.id.image1);
        image2= (ImageView) findViewById(R.id.image2);
        xingbie2= (RelativeLayout) findViewById(R.id.xingbie2);
        xingbie3= (RelativeLayout) findViewById(R.id.xingbie3);

        sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
        sex = sharedPreferences.getString("sex", null);
        if (!TextUtils.isEmpty(sex) && sex.equals("男")) {
            image2.setVisibility(View.GONE);
//            tx1.setText("男");
        }
        if (!TextUtils.isEmpty(sex) && sex.equals("女")) {
            image1.setVisibility(View.GONE);
//            tx1.setText("女");
        }
        xingbie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image1.setBackgroundResource(R.mipmap.ic_dropdown_check);
                image2.setVisibility(View.GONE);
                sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
                sharedPreferences.edit().putString("sex","男").apply();
                finish();
                Intent intent=new Intent(XingBieActivity.this,ShenFenActivity.class);
                startActivity(intent);
            }
        });
        xingbie3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image2.setBackgroundResource(R.mipmap.ic_dropdown_check);
                image1.setVisibility(View.GONE);
                sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
                sharedPreferences.edit().putString("sex","女").apply();
                finish();
                Intent intent=new Intent(XingBieActivity.this,ShenFenActivity.class);
                startActivity(intent);
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().hide();
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
