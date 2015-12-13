    package com.xiaoliwu.yll.fragment.homenavbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.Home_Acivity.Home_Happy;
import com.xiaoliwu.yll.activity.Home_Acivity.Sign_Activity;
import com.xiaoliwu.yll.activity.Home_Acivity.Special_Activity;
import com.xiaoliwu.yll.activity.Home_Acivity.Super_Details_Activity;
import com.xiaoliwu.yll.adapter.Home_SuperAdapter;
import com.xiaoliwu.yll.json.HomeBean;
import com.xiaoliwu.yll.json.Home_SuperBean;
import com.xiaoliwu.yll.myinterface.ServerUrl;
import com.xiaoliwu.yll.utils.RefreshableView;
import com.xiaoliwu.yll.utils.SlideShowView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/10/19.
 */
public class Home_GiftFragment extends Fragment implements View.OnClickListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
    private SlideShowView slideShowView;
    private ImageView iv1,iv2,iv3,iv4;
    private List<Home_SuperBean> list;
    private boolean isCanload;
    private boolean isLoading;
    private int page = 0;
    private Super_Details_Activity details_fragment = new Super_Details_Activity();
    private  JSONArray items;
    private  HttpUtils httpUtils;
    private Home_SuperAdapter home_superAdapter;
    private TextView textView;
    private ListView  listView;
    Home_SuperBean bean = null;
    private TextView textView1;
    private RefreshableView refreshableView;//下拉刷新
    private int week;
    private SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
    private String request;
    private HomeBean homeBean;
    //自定义轮播图的资源
    private String[] imageUrls;
    int year;
    int month ;
    int day;
    private String s;
    public static final int WEEKDAYS = 7;

    public static String[] WEEK = {
    "周日",
            "周一",
            "周二",
            "周三",
            "周四",
            "周五",
            "周六"
};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.siftfragment,container,false);
        View view1 = inflater.inflate(R.layout.list_head,null);
        slideShowView = (SlideShowView) view1.findViewById(R.id.slide);
        list = new ArrayList<Home_SuperBean>();
        refreshableView = (RefreshableView) view.findViewById(R.id.yll_ref);
        textView = (TextView) view1.findViewById(R.id.tv_time_main);
        textView1 = (TextView) view1.findViewById(R.id.tv_time);
        listView = (ListView) view.findViewById(R.id.lv_interface);
        iv1 = (ImageView) view1.findViewById(R.id.iv_home_happy);
        iv2 = (ImageView) view1.findViewById(R.id.iv_home_spcial);
        iv3 = (ImageView) view1.findViewById(R.id.iv_home_everyday);
        iv4 = (ImageView) view1.findViewById(R.id.iv_home_Lottery);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);

        listView.addHeaderView(view1);
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
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, "http://api.liwushuo.com/v2/banners", new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                request = (String) responseInfo.result;
                homeBean = new Gson().fromJson(request, HomeBean.class);
                imageUrls = new String[homeBean.getData().getBanners().size()];
                for (int i = 0; i < homeBean.getData().getBanners().size(); i++) {
                    s = homeBean.getData().getBanners().get(i).getImage_url();
                    imageUrls[i] = s;
                }
                slideShowView.setImageUrls(imageUrls);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "网络错误......", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Data();
        init();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Time time = new Time("GMT+8");
        time.setToNow();
        int year = time.year;
        int month = time.month+1;
        int day = time.monthDay;
        textView1.setText(year + "-" + month + "-" + day + " " + DateToWeek(date));
        return view;
    }


    @Override
    public void onClick (View v){
            switch (v.getId()){
            case R.id.iv_home_happy:
                startActivity(new Intent(getActivity(), Home_Happy.class));
                break;
                case R.id.iv_home_spcial:
                    startActivity(new Intent(getActivity(), Special_Activity.class));
                    break;
                case R.id.iv_home_everyday:
                    startActivity(new Intent(getActivity(), Sign_Activity.class));
                    break;
            case R.id.iv_home_Lottery:
                Toast.makeText(getActivity(), "这个接口被小怪兽吃了", Toast.LENGTH_SHORT).show();
                break;

        }
    }
    protected void Data(){
        httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, ServerUrl.HOME_ZHUJIEMIAN + "&offset=" + page + "&generation=4", new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                 items = JSON.parseObject((String) responseInfo.result).getJSONObject("data").getJSONArray("items");
                for (int i = 0; i < items.size(); i++) {
                    int likes_count;
                    JSONObject data = (items.getJSONObject(i));
                    String cover_image_url = data.getString("cover_image_url");
                    if (data.getInteger("likes_count")!=null) {
                        likes_count = data.getInteger("likes_count");
                    }else {
                        likes_count=0;
                    }
                    String title = data.getString("title");
                    int id = data.getInteger("id");
                    bean = new Home_SuperBean();
                  bean.setCover_image_url(cover_image_url);
                    bean.setId(id);
                    bean.setLikes_count(likes_count);
                    bean.setShare_msg(title);
                    list.add(bean);
                    //Log.e("data", list.toString());
                }
                if (home_superAdapter==null) {
                    home_superAdapter = new Home_SuperAdapter(list, getActivity());
                    listView.setAdapter(home_superAdapter);
                }else {
                    home_superAdapter.notifyDataSetChanged();
                }
                isLoading=false;
                page+=20;
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
    private void init(){
        httpUtils.send(HttpRequest.HttpMethod.GET, "http://api.liwushuo.com/v2/content_schedules?gender=1&generation=4", new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
               JSONArray items = JSON.parseObject((String) responseInfo.result).getJSONObject("data").getJSONObject("schedules").getJSONArray("item");
                String time = (String) items.get(0);
               // Log.e("data",time);
                textView.setText(time);
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
       int aa= list.get((int) id).getId();
        Log.e("aa", aa + "");
        Intent  intent = new Intent(getActivity(),Super_Details_Activity.class);
        intent.putExtra("init", aa);
        startActivity(intent);
    }
    public static String DateToWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }

        return WEEK[dayIndex - 1];
    }
}
