package com.xiaoliwu.yll.fragment.homenavbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
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
import com.xiaoliwu.yll.activity.Home_Acivity.Search_Activity;
import com.xiaoliwu.yll.activity.Hot_CommodityDetailActivity;
import com.xiaoliwu.yll.adapter.Myhot_Adapter;
import com.xiaoliwu.yll.json.HotBean;
import com.xiaoliwu.yll.myinterface.ServerUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/26.
 */
public class Search_Gift_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
    private GridView hot_gridView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int offset = 0;
    private Myhot_Adapter myhot_adapter = null;
    private boolean isCanLoad;//是否可以加载分页数据
    private boolean isLoading;//是否正在加载分页数据
    private List<HotBean> list;
    private int photoid;//拿到每一个图片的id
    private int paixu;
    private String moren;
    public void setPaixu(int paixu) {
        this.paixu = paixu;
        if (paixu==0){
            moren = ServerUrl.HOME_HOT_LIWU;
            list.clear();
            dataRequest();
        }else if (paixu==1){
         /*   moren = ServerUrl.HOME_ANREDU1;
            list.clear();
            dataRequest();*/
            Toast.makeText(getActivity(),"这个接口出了点问题暂时不能用哦",Toast.LENGTH_LONG).show();
        }else if (paixu==2){
            moren = ServerUrl.HOME_JIAGEGAO;
            list.clear();
            dataRequest();
        }else if (paixu==3){
            moren = ServerUrl.HOME_JIAGEDI;
            list.clear();
            dataRequest();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hot1, null);
        hot_gridView = (GridView) view.findViewById(R.id.hot_gridView);
        Search_Activity search_activity = new Search_Activity();
        int i = search_activity.onMainAction();
        Log.e("dd",i+"");
            moren = ServerUrl.HOME_HOT_LIWU;
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        dataRequest();
        swipeRefreshLayout.setOnRefreshListener(this);
        hot_gridView.setOnScrollListener(this);//设置滑动监听,用于分页
        hot_gridView.setOnItemClickListener(this);//设置点击每一条的监听事件
        list = new ArrayList<>();
        return view;
    }

    /**
     * 上拉刷新监听
     *
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && isCanLoad) {//代表滑动停止了并且可加载分页
            isCanLoad = false;
            isLoading = true;
            dataRequest();
        }
    }

    /**
     * 上拉刷新监听
     *
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {//代表最后一条显示出来了,但是至于是否完整显示出来 不确定
            View childView = view.getChildAt(visibleItemCount - 1);//获取屏幕内显示的最后一个 view,这个方法的参数是以当前界面显示的数量为准
            if (childView != null && childView.getBottom() <= view.getHeight() && !isLoading) {//代表最后一个控件完全显示出来
                isCanLoad = true;
            }
        }
    }

    /**
     * 请求数据
     * http://api.liwushuo.com/v2/items?limit=20&gender=2&generation=4&offset=0
     */
    public void dataRequest() {
        HttpUtils httpUtils = new HttpUtils(5000);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("search", Context.MODE_PRIVATE);
        String search = sharedPreferences.getString("search", "");
        Log.e("more1", moren + search);
        httpUtils.send(HttpRequest.HttpMethod.GET, moren + search,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        JSONArray items = JSON.parseObject(responseInfo.result).getJSONObject("data").getJSONArray("items");
                        HotBean bean = null;
                        for (int i = 0; i < items.size(); i++) {
                            JSONObject data = (items.getJSONObject(i));
                            int favorites_count = data.getInteger("favorites_count");
                            String name = data.getString("name");
                            String image = data.getString("cover_image_url");
                            String price = data.getString("price");
                            photoid = data.getInteger("id");
                            bean = new HotBean();
                            bean.setFavorites_count(favorites_count);
                            bean.setImage(image);
                            bean.setName(name);
                            bean.setPrice(price);
                            bean.setId(photoid);
                            list.add(bean);
                        }
                        if (myhot_adapter == null) {
                            myhot_adapter = new Myhot_Adapter(getActivity(), list);
                            hot_gridView.setAdapter(myhot_adapter);
                        } else {//如果不是第一页 则给 adapter 追加数据然后刷新界面
                            myhot_adapter.notifyDataSetChanged();
                        }
                        isLoading = false;//把正在加载数据改为 false
                        offset += 20;
                    }

                    /**
                     * 请求失败执行
                     * @param error
                     * @param msg
                     */
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        error.printStackTrace();
                        if (getActivity() != null) {
                            Toast.makeText(getActivity(), "网络错误......", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 刷新再次请求数据
     */
    @Override
    public void onRefresh() {
        dataRequest();
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 设置没一条上面的点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String path = ServerUrl.HOTDETAIL +list.get(position).getId();//每一条详情页的地址
        Bundle bundle = new Bundle();
        bundle.putString("path",path);
        Log.d("HotFagment", path);
        Intent intent = new Intent(getActivity(), Hot_CommodityDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public interface OnMainListener {

        public int onMainAction();

    }
}
