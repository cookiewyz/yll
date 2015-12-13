package com.xiaoliwu.yll.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

public class Denglu extends AppCompatActivity {
    private TextView tv1;
    private EditText register_username;
    private EditText register_passwd;
    private Button register_submit;
    SharedPreferences sharedPreferences;
    private String username, passwd, text, text1;
    private ImageView img1;


    @Override

    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences("zhanghao", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        passwd = sharedPreferences.getString("passwd", "");



        register_username = (EditText) findViewById(R.id.register_username);
        register_passwd = (EditText) findViewById(R.id.register_passwd);
        register_submit = (Button) findViewById(R.id.bt);

        register_username.setText(username);
        register_passwd.setText(passwd);
        Log.e("text2",username);
        Log.e("text2",passwd);


        register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text = register_username.getText().toString();

                text1 = register_passwd.getText().toString();
//                Log.e("text2",text);
//                Log.e("text2",text1);
                if (text.equals(username) && text1.equals(passwd)) {
//                    Log.e("text", username);
//                    Log.e("text", passwd);
                    sharedPreferences.edit().putBoolean("isDenglu", true).apply();
                    startActivity(new Intent(Denglu.this, MyZiLiaoActivity.class));
                    finish();
//                    Intent intent = new Intent(Denglu.this, Gengduo.class);
//                    startActivity(intent);
                } else {
                    Toast.makeText(Denglu.this, "账号或者密码错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_denglu);
        register_username = (EditText) findViewById(R.id.register_username);
        register_passwd = (EditText) findViewById(R.id.register_passwd);
        register_submit = (Button) findViewById(R.id.bt);
        img1= (ImageView) findViewById(R.id.img1);

        sharedPreferences = getSharedPreferences("zhanghao", MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);
        passwd = sharedPreferences.getString("passwd", null);

        Log.i("test", "onCreate "+username);
//        text = register_username.getText().toString();
//
//        text1 = register_passwd.getText().toString();

        getSupportActionBar().hide();
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Denglu.this, ZhuCeActivity.class);
                startActivity(intent);
            }
        });
        register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = register_username.getText().toString();

                text1 = register_passwd.getText().toString();
                if (!TextUtils.isEmpty(username) && text.equals(username) && !TextUtils.isEmpty(passwd) && text1.equals(passwd)) {
//            Drawable drawable = getResources().getDrawable(R.drawable.bg_checkbox_menu4nan);

                    SharedPreferences mySharedPreferences = getSharedPreferences("name2",
                            MODE_PRIVATE);
                    SharedPreferences.Editor editor = mySharedPreferences.edit();
//用putString的方法保存数据
                    editor.putString("name2","----");
//提交当前数据
                    editor.apply();
//            // 这一步必须要做,否则不会显示.
//                    Log.e("text",text);
//                    Log.e("text",text1);
                    finish();
//            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//            mHomeProfileRb.setCompoundDrawables(null, drawable, null, null);
//                    Intent intent = new Intent(Denglu.this, Gengduo.class);
//                    startActivity(intent);
                } else {
                    Toast.makeText(Denglu.this, "账号或者密码错误", Toast.LENGTH_SHORT).show();
                }

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
