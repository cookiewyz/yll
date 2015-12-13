package com.xiaoliwu.yll.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/12.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    ArrayList<String> titleList=new ArrayList<>();

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list, ArrayList<String> titleList) {
        super(fm);
        this.titleList=titleList;
        this.list=list;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
