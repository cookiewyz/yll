package com.xiaoliwu.yll.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.Home_Acivity.Search_Is;
import com.xiaoliwu.yll.adapter.Classify_FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 分类界面
 */
public class ClassifyFagment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private ViewPager classify_viewPager;
    private TextView classify_strategy, classify_gift;
    private List<Fragment> list=new ArrayList<>();
    private ImageView classify_search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_classify,null);
        classify_viewPager = (ViewPager) view.findViewById(R.id.classify_viewPager);
        classify_strategy = (TextView) view.findViewById(R.id.classify_strategy);
        classify_gift = (TextView) view.findViewById(R.id.classify_gift);
        classify_search = (ImageView) view.findViewById(R.id.classify_search);

        list.add(new StrategyFragment());
        list.add(new GiftFragment());
        Classify_FragmentPagerAdapter classify_fragmentPagerAdapter = new Classify_FragmentPagerAdapter(getChildFragmentManager(), list);
        classify_viewPager.setAdapter(classify_fragmentPagerAdapter);
        classify_strategy.setOnClickListener(this);
        classify_gift.setOnClickListener(this);
        classify_search.setOnClickListener(this);
        classify_viewPager.addOnPageChangeListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.classify_strategy:
                classify_viewPager.setCurrentItem(0);

                break;
            case R.id.classify_gift:
                classify_viewPager.setCurrentItem(1);
                break;
            case R.id.classify_search:
                startActivity(new Intent(getActivity(), Search_Is.class));
                // TODO: 2015/10/28
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                classify_strategy.setBackgroundResource(R.drawable.miaobian6);
                classify_strategy.setTextColor(Color.parseColor("#F96565"));
                classify_gift.setBackgroundResource(R.drawable.miaobian5);
                classify_gift.setTextColor(Color.parseColor("#ffffff"));
                break;
            case 1:
                classify_gift.setBackgroundResource(R.drawable.miaobian6);
                classify_gift.setTextColor(Color.parseColor("#F96565"));
                classify_strategy.setBackgroundResource(R.drawable.miaobian5);
                classify_strategy.setTextColor(Color.parseColor("#ffffff"));
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
