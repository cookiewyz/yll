package com.xiaoliwu.yll.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/10/7.
 */
public class MyAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    public MyAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }
}
