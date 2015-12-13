package com.xiaoliwu.yll.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.adapter.MyFragmentPagerAdapter;
import com.xiaoliwu.yll.fragment.FirstFragment;
import com.xiaoliwu.yll.fragment.FourFragment;
import com.xiaoliwu.yll.fragment.SecondFragment;
import com.xiaoliwu.yll.fragment.ThreeFragment;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    ViewPager viewPager;
    ImageView log;
    private SharedPreferences sharedPreferences;
    boolean isFirst = true;
    private List<Fragment> list;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private LinearLayout linearLayout;
    private ImageView lastImageView;
    private String sex,xueli;
    ArrayList<String> titleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐去状态栏部分(电池等图标和一切修饰部分)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        //隐去Actionbar
        getSupportActionBar().hide();
        linearLayout = (LinearLayout) findViewById(R.id.ll);
        sharedPreferences = getSharedPreferences("pengpeng", MODE_PRIVATE);
        isFirst = sharedPreferences.getBoolean("isFirst", true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        log = (ImageView) findViewById(R.id.log);
        handler.sendEmptyMessageDelayed(1, 3000);
        sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
        sex = sharedPreferences.getString("sex", null);
        xueli = sharedPreferences.getString("xueli",null);

    }
    private void judge() {
        if (!TextUtils.isEmpty(sex) && !TextUtils.isEmpty(xueli)) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }else {
            startActivity(new Intent(SplashActivity.this, ChooseActivity.class));
        }
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (isFirst) {
                        log.setVisibility(View.GONE);
                        init();
                    } else {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                    break;
            }
        }
    };

    /**
     * 初始化viewpager
     */
    private void init() {
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(30,20));
            imageView.setImageResource(R.drawable.aa);
            if (i == 0) {
                imageView.setImageResource(R.drawable.bb);
                lastImageView = imageView;
            }
            linearLayout.addView(imageView);
        }
        list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new SecondFragment());
        list.add(new ThreeFragment());
        list.add(new FourFragment());
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list, titleList);
        viewPager.setAdapter(myFragmentPagerAdapter);
        //viewPager的监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
//                lastImageView.setBackgroundColor(Color.RED);
                lastImageView.setImageResource(R.drawable.aa);
//                View childAt=linearLayout.getChildAt(i);
//                childAt.setBackgroundColor(Color.WHITE);
                ImageView childAt = (ImageView) linearLayout.getChildAt(i);
                childAt.setImageResource(R.drawable.bb);
                lastImageView = childAt;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }




}
