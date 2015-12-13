package com.xiaoliwu.yll.activity.Home_Acivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.adapter.MyPageAdapter;
import com.xiaoliwu.yll.fragment.homenavbar.Paixu1_picpwindows;
import com.xiaoliwu.yll.fragment.homenavbar.Paixu_picpwindows;
import com.xiaoliwu.yll.fragment.homenavbar.Search_Gift_Fragment;
import com.xiaoliwu.yll.fragment.homenavbar.Search_Strategy_Fragment;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class Search_Activity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener, ViewPager.OnPageChangeListener,Search_Gift_Fragment.OnMainListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> list = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    private TextView searchView;
    private String ss;
    private ImageView imageView;
    private Paixu1_picpwindows paixu1_picpwindows;
    private int i = 0;
    private Search_Strategy_Fragment search_strategy_fragment = new Search_Strategy_Fragment();
    private Paixu_picpwindows paixu_picpwindows;
    private FragmentTransaction fragmentTransaction;
    private ImageView imageView1;
    private int s;
    private Search_Gift_Fragment search_gift_fragment = new Search_Gift_Fragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_search_);
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void init(){
        getSupportActionBar().hide();
        tabLayout = (TabLayout) findViewById(R.id.ac_tab_layout_search);
        viewPager = (ViewPager) findViewById(R.id.ac_tab_vp_search);
        searchView = (TextView) findViewById(R.id.tv_sv_search);
        imageView = (ImageView) findViewById(R.id.iv_search_three);
        imageView1 = (ImageView) findViewById(R.id.iv_search_two);
        title.add("礼物");
        title.add("攻略");
        list.add(search_gift_fragment);
        list.add(search_strategy_fragment);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setOffscreenPageLimit(2);
        imageView.setOnClickListener(this);
        searchView.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        MyPageAdapter myPageAdapter = new MyPageAdapter(getSupportFragmentManager(),list,title);
        viewPager.setAdapter(myPageAdapter);
        viewPager.setOnPageChangeListener(this);
        tabLayout.setupWithViewPager(viewPager);
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
    public boolean onQueryTextSubmit(String query) {
        String s = Search_Is.toUtf8String(query);
        SharedPreferences sharedPreferences = getSharedPreferences("search",MODE_PRIVATE);
        sharedPreferences.edit().putString("search",s).apply();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ss = newText;
        return false;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_three:
            if (i == 0) {
                paixu_picpwindows = new Paixu_picpwindows(this, itemsOnClick);
                paixu_picpwindows.showAsDropDown(Search_Activity.this.findViewById(R.id.iv_search_three), Gravity.BOTTOM, 30, 30);
            } else if (i == 1) {
                paixu1_picpwindows = new Paixu1_picpwindows(this, itemsOnClick1);
                paixu1_picpwindows.showAsDropDown(Search_Activity.this.findViewById(R.id.iv_search_three), Gravity.BOTTOM, 30, 30);
            }
                break;
            case R.id.iv_search_two:
                this.finish();
                break;
        }


    }
    private View.OnClickListener itemsOnClick = new View.OnClickListener(){

        public void onClick(View v) {
            paixu_picpwindows.dismiss();
           // SharedPreferences sharedPreferences = getSharedPreferences("demo",MODE_PRIVATE);
            switch (v.getId()) {
                case R.id.tv_search_paixu1:
                    search_gift_fragment.setPaixu(0);
                    break;
                case R.id.tv_search_paixu2:
                    search_gift_fragment.setPaixu(1);
                    break;
                case R.id.tv_search_paixu3:
                    search_gift_fragment.setPaixu(2);
                    break;
                case R.id.tv_search_paixu4:
                    search_gift_fragment.setPaixu(3);
                    break;
            }
        }
    };
    private View.OnClickListener itemsOnClick1 = new View.OnClickListener(){

        public void onClick(View v) {
            paixu1_picpwindows.dismiss();
            switch (v.getId()) {
                case R.id.tv_search_paixu1:
                    search_strategy_fragment.setD(0);
                    break;
                case R.id.tv_search_paixu2:
                    search_strategy_fragment.setD(1);
                    break;
            }
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
            if (state==2){
                i = viewPager.getCurrentItem();
                Log.e("idis",i+"");
            }
    }


    @Override
    public int onMainAction() {
       return s;
    }
}
