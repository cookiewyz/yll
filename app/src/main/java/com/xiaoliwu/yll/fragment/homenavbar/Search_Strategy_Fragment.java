package com.xiaoliwu.yll.fragment.homenavbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
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
import com.xiaoliwu.yll.activity.Home_Acivity.Search_Activity;
import com.xiaoliwu.yll.activity.Home_Acivity.Super_Details_Activity;
import com.xiaoliwu.yll.adapter.Home_SuperAdapter;
import com.xiaoliwu.yll.json.Home_SuperBean;
import com.xiaoliwu.yll.myinterface.ServerUrl;
import com.xiaoliwu.yll.utils.RefreshableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2015/10/26.
 */
public class Search_Strategy_Fragment extends Fragment implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
    protected List<Home_SuperBean> list;
    private boolean isCanload;
    private int page=0;
    private boolean isLoading;
    private Home_SuperAdapter home_superAdapter;
    protected ListView  listView;
    private  int d;
    private String more;
    private RefreshableView refreshableView;//下拉刷新
    public void setD(int d) {
        this.d = d;
        if (d==0){
            more = ServerUrl.HOME_HOT_GONGLUE;
            list.clear();
            Data();
        }else if (d==1){
            more = ServerUrl.HOME_ANREDU;
            list.clear();
            Data();
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.super_fragment, container, false);
        listView= (ListView)view.findViewById(R.id.ll_home_happy);
        more = ServerUrl.HOME_HOT_GONGLUE;
        refreshableView = (RefreshableView)view.findViewById(R.id.yll);
        list = new ArrayList<>();
        Data();
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
        return view;
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
        if (firstVisibleItem+visibleItemCount==totalItemCount) {
            View childat = view.getChildAt(visibleItemCount - 1);
            if (childat != null && childat.getBottom() <= view.getHeight() && !isLoading) {
                isCanload = true;
            }
        }
    }

    /**
     * http://api.liwushuo.com/v2/channels/121/items?limit=20&offset=0&gender=1&generation=4
     */
    protected void Data(){
        HttpUtils httpUtils = new HttpUtils();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("search", Context.MODE_PRIVATE);
        String search = sharedPreferences.getString("search", "");
        httpUtils.send(HttpRequest.HttpMethod.GET, more+search, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                JSONArray items = JSON.parseObject((String) responseInfo.result).getJSONObject("data").getJSONArray("posts");
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
                    home_superAdapter = new Home_SuperAdapter(list, getActivity());
                    listView.setAdapter(home_superAdapter);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int aa= list.get((int) id).getId();
        Log.e("aa", aa + "");
        Intent intent = new Intent(getActivity(),Super_Details_Activity.class);
        intent.putExtra("init", aa);
        startActivity(intent);
    }
}
