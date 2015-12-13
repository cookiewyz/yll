package com.xiaoliwu.yll.activity.Home_Acivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.MainActivity;
import com.xiaoliwu.yll.adapter.TagAdapter;
import com.xiaoliwu.yll.fragment.homenavbar.library.FlowTagLayout;
import com.xiaoliwu.yll.fragment.homenavbar.library.OnTagClickListener;
import com.xiaoliwu.yll.json.Home_Hot;
import com.xiaoliwu.yll.myinterface.ServerUrl;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class Search_Is extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener {
    private FlowTagLayout flowTagLayout;
    private TagAdapter tagAdapter;
    private List<Home_Hot> list1;
    private Home_Hot home_hot;
    private TextView tv;
    private String ss;
    private RelativeLayout rl;
    private SearchView searchView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_search__is);
        getSupportActionBar().hide();
        list1 = new ArrayList<>();
        data();
        init();
    }
    private void data(){
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, ServerUrl.HOME_HOT, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                JSONObject items = JSON.parseObject((String) responseInfo.result).getJSONObject("data");
                home_hot = new Home_Hot();
                List list = items.getJSONArray("hot_words");
                home_hot.setHot_words(list);
                list1.add(home_hot);
                tagAdapter.onlyAddAll(home_hot.getHot_words());
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
    private void init() {
        flowTagLayout = (FlowTagLayout)findViewById(R.id.color_flow_layout);
        // list = new ArrayList<>();
        tv = (TextView) findViewById(R.id.tv_search_one);
        searchView = (SearchView) findViewById(R.id.tv_sv);
        rl = (RelativeLayout) findViewById(R.id.rl_search);
        imageView = (ImageView) findViewById(R.id.iv_search_two);
        tagAdapter = new TagAdapter<>(this);
        flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE);
        tv.setOnClickListener(this);
        imageView.setOnClickListener(this);
        searchView.setOnQueryTextListener(this);
        rl.setOnClickListener(this);
        flowTagLayout.setAdapter(tagAdapter);
        flowTagLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                String s =  toUtf8String(home_hot.getHot_words().get(position));
                SharedPreferences sharedPreferences = Search_Is.this.getSharedPreferences("search", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("search",s).apply();
                startActivity(new Intent(Search_Is.this,Search_Activity.class));
            }
        });
    }
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search_one:
                if (ss == null){
                    Toast.makeText(Search_Is.this, "你还没有输入要搜索的内容哦", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences sharedPreferences = Search_Is.this.getSharedPreferences("search", Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString("search", toUtf8String(ss)).apply();
                    startActivity(new Intent(Search_Is.this, Search_Activity.class));
                }
                break;
            case R.id.rl_search:
                startActivity(new Intent(Search_Is.this,Select_Activity.class));
                break;
            case R.id.iv_search_two:
                Search_Is.this.finish();
                //getActivity().getSupportFragmentManager().popBackStack();
               // startActivity(new Intent(Search_Is.this,MainActivity.class));
                break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ss = newText;
        return false;
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
