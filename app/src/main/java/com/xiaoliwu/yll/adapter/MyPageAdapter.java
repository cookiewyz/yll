package com.xiaoliwu.yll.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/10/19.
 */
public class MyPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> listtitle;
    public MyPageAdapter(FragmentManager fm,List<Fragment> list,List<String> listtitle) {
        super(fm);
        this.list=list;
        this.listtitle=listtitle;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
        public CharSequence getPageTitle(int position) {
                return listtitle.get(position);
    }

}
