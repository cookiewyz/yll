package com.xiaoliwu.yll.activity.Home_Acivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.adapter.Home_SuperAdapter;
import com.xiaoliwu.yll.json.Home_SuperBean;
import com.xiaoliwu.yll.myinterface.ServerUrl;
import com.xiaoliwu.yll.utils.RefreshableView;
import com.xiaoliwu.yll.utils.SystemBarTintManager;
import java.util.ArrayList;
import java.util.List;

public class Home_Happy extends AppCompatActivity implements View.OnClickListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
    private List<Home_SuperBean> list;
    private boolean isCanload;
    private boolean isLoading;
    //private String path = "&offset=";
    private int page = 0;
    private Home_SuperAdapter home_superAdapter;
    protected String pathone;
    private ListView listView;
    private SharedPreferences sharedPreferences;
    private RefreshableView refreshableView;//下拉刷新
    private ImageView imageView;//返回键
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_home__happy);
        getSupportActionBar().hide();
        list = new ArrayList<Home_SuperBean>();
        listView = (ListView)findViewById(R.id.ll_home_happy_one);
        sharedPreferences=this.getSharedPreferences("gonglue",this.MODE_PRIVATE);
        refreshableView = (RefreshableView)findViewById(R.id.yll_one);
        imageView = (ImageView)findViewById(R.id.iv_fanhui);
        imageView.setOnClickListener(this);
        listView.setOnScrollListener(this);
        listView.setOnItemClickListener(this);
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Data();
                refreshableView.finishRefreshing();
            }
        }, 1);
        Data();
    }
    protected void Data() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, ServerUrl.HOME_HAPPY + "&offset=" + page, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                JSONArray items = JSON.parseObject((String) responseInfo.result).getJSONObject("data").getJSONArray("posts");
                Home_SuperBean bean = null;
                for (int i = 0; i < items.size(); i++) {
                    JSONObject data = (items.getJSONObject(i));
                    int id = data.getInteger("id");
                    int likes_count = data.getInteger("likes_count");
                    String cover_image_url = data.getString("cover_image_url");
                    String title = data.getString("title");
                    bean = new Home_SuperBean();
                    bean.setLikes_count(likes_count);
                    bean.setId(id);
                    bean.setCover_image_url(cover_image_url);
                    bean.setShare_msg(title);
                    list.add(bean);
                    //Log.e("data", list.toString());
                }
                if (home_superAdapter == null) {
                    home_superAdapter = new Home_SuperAdapter(list, Home_Happy.this);
                    listView.setAdapter(home_superAdapter);
                } else {
                    home_superAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(Home_Happy.this,"网络连接超时",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && isCanload) {
            isCanload = false;
            isLoading = true;
            Data();
            isLoading = false;
            page += 20;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        View childat = view.getChildAt(visibleItemCount - 1);
        if (childat != null && childat.getBottom() <= view.getHeight() && !isLoading) {
            isCanload = true;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int aa= list.get((int) id).getId();
        Log.e("aa", aa + "");
        Intent intent = new Intent(this,Home_Good_Acitvity.class);
        intent.putExtra("init", aa);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        this.finish();
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
