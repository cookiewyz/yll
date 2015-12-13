package com.xiaoliwu.yll.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

public class ZhuCeActivity extends AppCompatActivity {
    private EditText editText, editText1, editText2;
    private Button bt;
    private SharedPreferences sharedPreferences;
    private String text,text1;
    private ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_zhu_ce);

        getSupportActionBar().hide();
        editText = (EditText) findViewById(R.id.register_username);
        editText1 = (EditText) findViewById(R.id.register_passwd);
        editText2 = (EditText) findViewById(R.id.register_passwd2);
        img1= (ImageView) findViewById(R.id.img1);
        bt = (Button) findViewById(R.id.bt);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    if (editText.getText().toString().trim().length() < 11) {
                        Toast.makeText(ZhuCeActivity.this, "手机号不能小于11个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    if (editText1.getText().toString().trim().length() < 6 && editText1.getText().toString().trim().length() > 18) {
                        Toast.makeText(ZhuCeActivity.this, "密码需要满足6--18位", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    if (!editText1.getText().toString().equals((editText2.getText().toString()))) {
                        Toast.makeText(ZhuCeActivity.this, "密码需要相同", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text = editText.getText().toString();
                text1 = editText1.getText().toString();
                //实例化SharedPreferences对象（第二步）
                SharedPreferences mySharedPreferences = getSharedPreferences("zhanghao",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = mySharedPreferences.edit();
//用putString的方法保存数据
                editor.putString("username", text);
                editor.putString("passwd", text1);
//提交当前数据
                editor.apply();

                if (editText.getText().toString().trim().length() != 11 && !editText.getText().toString().trim().equals((editText.getText().toString().trim()))) {
                    Toast.makeText(ZhuCeActivity.this, "手机号输入错误", Toast.LENGTH_SHORT).show();
                } else if (editText1.getText().toString().trim().equals("")) {
                    Toast.makeText(ZhuCeActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (!editText1.getText().toString().equals((editText2.getText().toString()))) {
                    Toast.makeText(ZhuCeActivity.this, "密码需要相同", Toast.LENGTH_SHORT).show();
                } else {
//                    Intent i3 = new Intent(ZhuCeActivity.this, Denglu.class);
//                    startActivity(i3);
                }
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
