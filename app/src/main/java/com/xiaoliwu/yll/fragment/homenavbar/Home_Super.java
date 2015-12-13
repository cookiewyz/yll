package com.xiaoliwu.yll.fragment.homenavbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.xiaoliwu.yll.adapter.Home_SuperAdapter;
import com.xiaoliwu.yll.json.Home_SuperBean;
import com.xiaoliwu.yll.utils.RefreshableView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/21.
 */
public abstract class Home_Super extends Fragment implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
   // private HomeBean homeBean;
    protected List<Home_SuperBean> list;
    private boolean isCanload;
    private boolean isLoading;
    //private String path = "&offset=";
    private int page = 0;
    private Home_SuperAdapter home_superAdapter;
    private String path = "&gender=1&generation=4";
    protected String pathone;
    protected ListView  listView;
    private RefreshableView refreshableView;//下拉刷新
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.super_fragment,container,false);
        listView= (ListView)view.findViewById(R.id.ll_home_happy);
        refreshableView = (RefreshableView)view.findViewById(R.id.yll);
        list = new ArrayList<>();
        init();
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
        httpUtils.send(HttpRequest.HttpMethod.GET, pathone + "&offset=" + page + path, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                JSONArray items = JSON.parseObject((String) responseInfo.result).getJSONObject("data").getJSONArray("items");
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
                    //Log.e("data", list.toString());
                }
                if (home_superAdapter==null) {
                    home_superAdapter = new Home_SuperAdapter(list, getActivity());
                    listView.setAdapter(home_superAdapter);
                }else {
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



    protected abstract void init();
}
