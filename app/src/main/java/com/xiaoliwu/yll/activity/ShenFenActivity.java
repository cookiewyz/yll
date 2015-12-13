package com.xiaoliwu.yll.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

public class ShenFenActivity extends AppCompatActivity {
    private ImageView tv1;
    SharedPreferences sharedPreferences;
    private TextView tx1,tx2;
    private String sex,job;
    private RelativeLayout shenfen2,shenfen3;

    @Override
    protected void onRestart() {
        super.onRestart();
        sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
        sex = sharedPreferences.getString("sex", null);
        job = sharedPreferences.getString("xueli",null);
        if (!TextUtils.isEmpty(sex) && sex.equals("男")) {
            tx1.setText("男");
        }
        if (!TextUtils.isEmpty(sex) && sex.equals("女")) {
            tx1.setText("女");
        }
        if (!TextUtils.isEmpty(job) && job.equals("初中生")) {
            tx2.setText("初中生");
        }
        if (!TextUtils.isEmpty(job) && job.equals("高中生")) {
            tx2.setText("高中生");
        }
        if (!TextUtils.isEmpty(job) && job.equals("大学生")) {
            tx2.setText("大学生");
        }
        if (!TextUtils.isEmpty(job) && job.equals("职场新人")) {
            tx2.setText("职场新人");
        }
        if (!TextUtils.isEmpty(job) && job.equals("职场资深人")) {
            tx2.setText("职场资深人");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_shen_fen);
        tv1= (ImageView) findViewById(R.id.tv1);
        shenfen2= (RelativeLayout) findViewById(R.id.shenfen2);
        shenfen3= (RelativeLayout) findViewById(R.id.shenfen3);
        tx1= (TextView) findViewById(R.id.tx1);
        tx2= (TextView) findViewById(R.id.tx2);

        sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
        sex = sharedPreferences.getString("sex", null);
        job = sharedPreferences.getString("xueli",null);
        if (!TextUtils.isEmpty(sex) && sex.equals("男")) {
            tx1.setText("男");
        }
        if (!TextUtils.isEmpty(sex) && sex.equals("女")) {
            tx1.setText("女");
        }
        if (!TextUtils.isEmpty(job) && job.equals("初中生")) {
            tx2.setText("初中生");
        }
        if (!TextUtils.isEmpty(job) && job.equals("高中生")) {
            tx2.setText("高中生");
        }
        if (!TextUtils.isEmpty(job) && job.equals("大学生")) {
            tx2.setText("大学生");
        }
        if (!TextUtils.isEmpty(job) && job.equals("职场新人")) {
            tx2.setText("职场新人");
        }
        if (!TextUtils.isEmpty(job) && job.equals("职场资深人")) {
            tx2.setText("职场资深人");
        }

        shenfen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(ShenFenActivity.this,XingBieActivity.class);
                startActivity(intent);
            }
        });
        shenfen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(ShenFenActivity.this,ZhiYeActivity.class);
                startActivity(intent);
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(ShenFenActivity.this,Gengduo.class);
                startActivity(intent);

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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            finish();
            Intent intent=new Intent(ShenFenActivity.this,Gengduo.class);
            startActivity(intent);

//            // 创建退出对话框
//            AlertDialog isExit = new AlertDialog.Builder(this).create();
//            // 设置对话框标题
//            isExit.setTitle("系统提示");
//            // 设置对话框消息
//            isExit.setMessage("确定要退出吗");
//            // 添加选择按钮并注册监听
//            isExit.setButton("确定", listener);
//            isExit.setButton2("取消", listener);
//            // 显示对话框
//            isExit.show();

        }

        return false;

    }

}
