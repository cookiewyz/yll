package com.xiaoliwu.yll.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.Home_Acivity.Super_Details_Activity;
import com.xiaoliwu.yll.adapter.Home_SuperAdapter;
import com.xiaoliwu.yll.json.Home_SuperBean;
import com.xiaoliwu.yll.utils.RefreshableView;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class Classify_HeadoneActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private RefreshableView classify_head_refreshableview;
    private ListView classify_head_listview;
    private ImageView classify_head_action_back;
    private TextView classify_head_textView;
    private int id;
    private List<Home_SuperBean> list;
    private Home_SuperAdapter home_superAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();//状态栏背景必须在setContentView之前
        setContentView(R.layout.activity_classify__headone);
        getSupportActionBar().hide();
        classify_head_refreshableview = (RefreshableView) findViewById(R.id.classify_head_refreshableview);
        classify_head_listview = (ListView) findViewById(R.id.classify_head_listview);
        classify_head_action_back = (ImageView) findViewById(R.id.classify_head_action_back);
        classify_head_textView = (TextView) findViewById(R.id.classify_head_textView);
        list = new ArrayList<>();
        classify_head_action_back.setOnClickListener(this);
        classify_head_listview.setOnItemClickListener(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("headID", -1);
        getData();
        classify_head_refreshableview.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getData();
                classify_head_refreshableview.finishRefreshing();
            }
        }, 1);
    }
    /**
     * 设置状态栏背景状态
     */
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

    /**
     *http://api.liwushuo.com/v2/collections/150/posts?limit=20&offset=0
     */
    protected void getData(){
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, "http://api.liwushuo.com/v2/collections/"+id+"/posts?limit=20&offset=0", new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.d("Classify_HeadoneActivit", "id:" + id);
                JSONArray items = JSON.parseObject((String) responseInfo.result).getJSONObject("data").getJSONArray("posts");
                String titlehead = JSON.parseObject((String) responseInfo.result).getJSONObject("data").getString("title");
                classify_head_textView.setText(titlehead);
                Home_SuperBean bean = null;
                for (int i = 0; i < items.size(); i++) {
                    JSONObject data = (items.getJSONObject(i));
                    int likes_count = data.getInteger("likes_count");
                    String cover_image_url = data.getString("cover_image_url");
                    String title = data.getString("title");
                    int id = data.getInteger("id");
                    bean = new Home_SuperBean();
                    bean.setLikes_count(likes_count);
                    bean.setCover_image_url(cover_image_url);
                    bean.setShare_msg(title);
                    bean.setId(id);
                    list.add(bean);
                }
                if (home_superAdapter == null) {
                    home_superAdapter = new Home_SuperAdapter(list, Classify_HeadoneActivity.this);
                    classify_head_listview.setAdapter(home_superAdapter);
                } else {
                    home_superAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.classify_head_action_back:
                finish();
                break;
        }
    }

    /**
     * listView 上的每条点击事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int aa= list.get((int) id).getId();
        Intent intent = new Intent(this,Super_Details_Activity.class);
        intent.putExtra("init", aa);
        startActivity(intent);
    }
}
