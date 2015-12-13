package com.xiaoliwu.yll.fragment.homenavbar;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

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
import com.xiaoliwu.yll.activity.Home_Acivity.Search_Activity;
import com.xiaoliwu.yll.adapter.TagAdapter;
import com.xiaoliwu.yll.fragment.homenavbar.library.FlowTagLayout;
import com.xiaoliwu.yll.fragment.homenavbar.library.OnTagClickListener;
import com.xiaoliwu.yll.json.Home_Hot;
import com.xiaoliwu.yll.json.Home_Select;
import com.xiaoliwu.yll.myinterface.ServerUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class Select_picpwindows extends PopupWindow implements OnTagClickListener {
    private View view;
    private HttpUtils httpUtils;
    private int id;
    private FlowTagLayout flowTagLayout;
    private TagAdapter tagAdapter;
    private Home_Select home_select;
    private int isid=0;
    private List<Home_Select> list1 = new ArrayList<>();
    private List<Home_Select> list2 = new ArrayList<>();

    public Select_picpwindows(Context context, View.OnClickListener onClickListener,OnTagClickListener listener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.select_dialog, null);
        flowTagLayout = (FlowTagLayout) view.findViewById(R.id.color_flow_layout);
        flowTagLayout.setOnTagClickListener(this);
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        httpUtils = new HttpUtils();
        //设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
       // Data();
        flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE);
        tagAdapter = new TagAdapter(context);
        flowTagLayout.setAdapter(tagAdapter);
        init();
       // tagAdapter.onlyAddAll(list);
       flowTagLayout.setOnTagClickListener(listener);
    }
    private void init(){
        List list = new ArrayList();
        if (isid==0){
            list.add("全部");
            list.add("男票");
            list.add("女盆友");
            list.add("闺蜜们");
            list.add("基友");
            list.add("爸爸妈妈");
            list.add("小盆友");
            list.add("同事");
            tagAdapter.onlyAddAll(list);
        }
    }

    @Override
    public void onItemClick(FlowTagLayout parent, View view, int position) {

    }
}



