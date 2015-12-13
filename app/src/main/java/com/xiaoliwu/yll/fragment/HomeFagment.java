package com.xiaoliwu.yll.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.Home_Acivity.ShaoYiShao;
import com.xiaoliwu.yll.activity.Home_Acivity.Search_Is;
import com.xiaoliwu.yll.adapter.MyPageAdapter;
import com.xiaoliwu.yll.fragment.homenavbar.DigitalFragment;
import com.xiaoliwu.yll.fragment.homenavbar.ExerciseFragment;
import com.xiaoliwu.yll.fragment.homenavbar.Home_GiftFragment;
import com.xiaoliwu.yll.fragment.homenavbar.GoodFoodFragment;
import com.xiaoliwu.yll.fragment.homenavbar.RecreationFragment;
import com.xiaoliwu.yll.fragment.homenavbar.SiftFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by smyh on 2015/4/28.
 */
public class HomeFagment extends Fragment implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tableLayout;
    private ImageView iv_search,imageView;
    private List<Fragment> list = new ArrayList();
    private List<String> list_title = new ArrayList<>();
    private MyPageAdapter myPageAdapter;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        init();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
            startActivity(new Intent(getActivity(), Search_Is.class));
                break;
            case R.id.iv_scan:
                startActivity(new Intent(getActivity(),ShaoYiShao.class));
                break;
        }
    }
    private void init(){
        viewPager = (ViewPager) view.findViewById(R.id.ac_tab_vp);
        iv_search = (ImageView) view.findViewById(R.id.iv_search);
        tableLayout = (TabLayout) view.findViewById(R.id.ac_tab_layout);
        imageView = (ImageView) view.findViewById(R.id.iv_scan);
        imageView.setOnClickListener(this);
        list.add(new Home_GiftFragment());
        //list.add(new Home_Super());
        list.add(new SiftFragment());
        list.add(new GoodFoodFragment());
        list.add(new DigitalFragment());
        list.add(new RecreationFragment());
        list.add(new ExerciseFragment());
        list_title.add("精选");
        list_title.add("礼物");
        list_title.add("美食");
        list_title.add("数码");
        list_title.add("娱乐");
        list_title.add("运动");
        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setOffscreenPageLimit(5);
        myPageAdapter = new MyPageAdapter(getChildFragmentManager(),list,list_title);
        viewPager.setAdapter(myPageAdapter);
        tableLayout.setupWithViewPager(viewPager);
        iv_search.setOnClickListener(this);//为搜索添加监�?
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        list.clear();
        list_title.clear();

    }
}



