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

public class ZhiYeActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private String job;
    private ImageView tv1,image1,image2,image3,image4,image5;
    private RelativeLayout zhiye1,zhiye2,zhiye3,zhiye4,zhiye5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_zhi_ye);
        tv1= (ImageView) findViewById(R.id.tv1);
        getSupportActionBar().hide();
        image1= (ImageView) findViewById(R.id.image1);
        image2= (ImageView) findViewById(R.id.image2);
        image3= (ImageView) findViewById(R.id.image3);
        image4= (ImageView) findViewById(R.id.image4);
        image5= (ImageView) findViewById(R.id.image5);
        zhiye1= (RelativeLayout) findViewById(R.id.zhiye2);
        zhiye2= (RelativeLayout) findViewById(R.id.zhiye3);
        zhiye3= (RelativeLayout) findViewById(R.id.zhiye4);
        zhiye4= (RelativeLayout) findViewById(R.id.zhiye5);
        zhiye5= (RelativeLayout) findViewById(R.id.zhiye6);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
        job = sharedPreferences.getString("xueli", null);
        if (!TextUtils.isEmpty(job) && job.equals("初中生")) {
            image2.setVisibility(View.GONE);
            image3.setVisibility(View.GONE);
            image4.setVisibility(View.GONE);
            image5.setVisibility(View.GONE);
//            tx1.setText("男");
        }
        if (!TextUtils.isEmpty(job) && job.equals("高中生")) {
            image1.setVisibility(View.GONE);
            image3.setVisibility(View.GONE);
            image4.setVisibility(View.GONE);
            image5.setVisibility(View.GONE);
//            tx1.setText("女");
        }

        if (!TextUtils.isEmpty(job) && job.equals("大学生")) {
            image1.setVisibility(View.GONE);
            image2.setVisibility(View.GONE);
            image4.setVisibility(View.GONE);
            image5.setVisibility(View.GONE);
//            tx1.setText("女");
        }
        if (!TextUtils.isEmpty(job) && job.equals("职场新人")) {
            image1.setVisibility(View.GONE);
            image2.setVisibility(View.GONE);
            image3.setVisibility(View.GONE);
            image5.setVisibility(View.GONE);
//            tx1.setText("女");
        }
        if (!TextUtils.isEmpty(job) && job.equals("职场资深人")) {
            image1.setVisibility(View.GONE);
            image2.setVisibility(View.GONE);
            image3.setVisibility(View.GONE);
            image4.setVisibility(View.GONE);
//            tx1.setText("女");
        }
        zhiye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image1.setBackgroundResource(R.mipmap.ic_dropdown_check);
                image2.setVisibility(View.GONE);
                image3.setVisibility(View.GONE);
                image4.setVisibility(View.GONE);
                image5.setVisibility(View.GONE);
                sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
                sharedPreferences.edit().putString("xueli","初中生").apply();
                finish();
                Intent intent=new Intent(ZhiYeActivity.this,ShenFenActivity.class);
                startActivity(intent);
            }
        });
        zhiye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image2.setBackgroundResource(R.mipmap.ic_dropdown_check);
                image1.setVisibility(View.GONE);
                image3.setVisibility(View.GONE);
                image4.setVisibility(View.GONE);
                image5.setVisibility(View.GONE);
                sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
                sharedPreferences.edit().putString("xueli","高中生").apply();
                finish();
                Intent intent=new Intent(ZhiYeActivity.this,ShenFenActivity.class);
                startActivity(intent);
            }
        });
        zhiye3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image3.setBackgroundResource(R.mipmap.ic_dropdown_check);
                image2.setVisibility(View.GONE);
                image1.setVisibility(View.GONE);
                image4.setVisibility(View.GONE);
                image5.setVisibility(View.GONE);
                sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
                sharedPreferences.edit().putString("xueli","大学生").apply();
                finish();
                Intent intent=new Intent(ZhiYeActivity.this,ShenFenActivity.class);
                startActivity(intent);
            }
        });
        zhiye4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image4.setBackgroundResource(R.mipmap.ic_dropdown_check);
                image1.setVisibility(View.GONE);
                image2.setVisibility(View.GONE);
                image3.setVisibility(View.GONE);
                image5.setVisibility(View.GONE);
                sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
                sharedPreferences.edit().putString("xueli","职场新人").apply();
                finish();
                Intent intent=new Intent(ZhiYeActivity.this,ShenFenActivity.class);
                startActivity(intent);
            }
        });
        zhiye5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image5.setBackgroundResource(R.mipmap.ic_dropdown_check);
                image1.setVisibility(View.GONE);
                image2.setVisibility(View.GONE);
                image4.setVisibility(View.GONE);
                image3.setVisibility(View.GONE);
                sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
                sharedPreferences.edit().putString("xueli","职场资深人").apply();
                finish();
                Intent intent=new Intent(ZhiYeActivity.this,ShenFenActivity.class);
                startActivity(intent);
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
