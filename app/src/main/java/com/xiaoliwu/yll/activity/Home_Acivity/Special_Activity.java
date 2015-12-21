package com.xiaoliwu.yll.activity.Home_Acivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.adapter.SpecialAdapter;
import com.xiaoliwu.yll.json.Special_Bean;
import com.xiaoliwu.yll.myinterface.ServerUrl;
import com.xiaoliwu.yll.utils.RefreshableView;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;


public class Special_Activity extends AppCompatActivity implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private List<Special_Bean> list;
    private boolean isCanload;
    private boolean isLoading;
    //private String path = "&offset=";
    private int page = 0;
    private SpecialAdapter home_superAdapter;
    protected String pathone;
    private ListView listView;
    private RefreshableView refreshableView;//下拉刷新
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_special_);
        getSupportActionBar().hide();
        listView = (ListView) findViewById(R.id.ll_home_special_one);
        refreshableView = (RefreshableView)findViewById(R.id.yll_special);
        imageView = (ImageView)findViewById(R.id.iv_fanhui_special);
        list = new ArrayList<Special_Bean>();
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

    /**
     * 加载数据
     */
    protected void Data() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, ServerUrl.HOME_SPECIAL + "offset=" + page + "&limit=20", new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                JSONArray items = JSON.parseObject((String) responseInfo.result).getJSONObject("data").getJSONArray("collections");
                Special_Bean special_bean = null;
                for (int i = 0; i < items.size(); i++) {
                    JSONObject data = (items.getJSONObject(i));
                    String title = data.getString("title");
                    int id = data.getInteger("id");
                    String subtitle = data.getString("subtitle");
                    String cover_image_url = data.getString("cover_image_url");
                    special_bean = new Special_Bean();
                    special_bean.setTitle(title);
                    special_bean.setSubtitle(subtitle);
                    special_bean.setId(id);
                    special_bean.setCover_image_url(cover_image_url);
                    list.add(special_bean);
                    //Log.e("data", list.toString());
                }
                if (home_superAdapter == null) {
                    home_superAdapter = new SpecialAdapter(list, Special_Activity.this);
                    listView.setAdapter(home_superAdapter);
                } else {
                    home_superAdapter.notifyDataSetChanged();
                }
                isLoading = false;
                page += 20;
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && isCanload) {
            isCanload = false;
            isLoading = true;
            Data();

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
