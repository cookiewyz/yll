package com.xiaoliwu.yll.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.Denglu;
import com.xiaoliwu.yll.activity.Gengduo;
import com.xiaoliwu.yll.activity.JiFenActivity;
import com.xiaoliwu.yll.activity.LiJuanActivity;
import com.xiaoliwu.yll.activity.MyZiLiaoActivity;
import com.xiaoliwu.yll.activity.TaoBaoDingDanActivity;
import com.xiaoliwu.yll.adapter.CommentAdapter;

import java.lang.reflect.Field;
import java.util.List;


/**
 * 我的界面
 */
public class ProfileFagment extends Fragment {
    private TextView tv1;
    private ImageView image2;
    private RelativeLayout ll1, ll2, ll3, ll4, textView21;
    private PagerSlidingTabStrip tabs;
    private ViewPager viewPager;
    private List<Fragment> listViews;
    private ImageView cursor, image1;
    private TextView textView4, textView5, textView2, tv2;
    private SharedPreferences sharedPreferences, sharedPreferences2;
    private String username, passwd, name, name2;
    View view;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;


    @Override
    public void onResume() {
        super.onResume();
        Log.e("ProfileFagment", "onResume");
        sharedPreferences = getActivity().getSharedPreferences("zhanghao", getActivity().MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        passwd = sharedPreferences.getString("passwd", "");
        boolean isDenglu = sharedPreferences.getBoolean("isDenglu", false);
        tv2.setText(sharedPreferences.getString("nickname", "未命名"));
        if (isDenglu) {
            Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/song/xuanzhuan.png");
            //给图片切成圆角 传入bitmap返回bitmap
            CommentAdapter commentAdapter=new CommentAdapter();
            Bitmap bitmap1 = commentAdapter.toRoundBitmap(bitmap);
            image1.setImageBitmap(bitmap1);
        } else {
            image1.setImageResource(R.mipmap.ig_profile_photo_default);
        }

        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("ProfileFagment", "position  " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("ProfileFagment", "onPageSelected  position  " + position);

                viewPager.setCurrentItem(position);
                tabs.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
//参数是固定写法
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);

        tv2 = (TextView) view.findViewById(R.id.tv2);
        Log.e("adssadfasd", "onCreateView");
        image1 = (ImageView) view.findViewById(R.id.image1);
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new myPagerAdapter(getChildFragmentManager()));
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabs.setViewPager(viewPager);
        sharedPreferences = getActivity().getSharedPreferences("zhanghao", Context.MODE_PRIVATE);
        sharedPreferences2 = getActivity().getSharedPreferences("name", Context.MODE_PRIVATE);
        name = sharedPreferences2.getString("name", null);
        username = sharedPreferences.getString("username", null);
        passwd = sharedPreferences.getString("passwd", null);
        if (!TextUtils.isEmpty(username) && username.equals(username) && !TextUtils.isEmpty(passwd) && passwd.equals(passwd)) {
            tv2.setText(name);
            Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/song/xuanzhuan.png");
            Drawable drawable = new BitmapDrawable(bitmap);
            image1.setImageBitmap(bitmap);
        }
        ll2 = (RelativeLayout) view.findViewById(R.id.ll2);
        ll1 = (RelativeLayout) view.findViewById(R.id.ll1);
        ll3 = (RelativeLayout) view.findViewById(R.id.ll3);
        ll4 = (RelativeLayout) view.findViewById(R.id.ll4);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        image2 = (ImageView) view.findViewById(R.id.image2);
        textView4 = (TextView) view.findViewById(R.id.textView4);
        textView21 = (RelativeLayout) view.findViewById(R.id.textView21);
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TaoBaoDingDanActivity.class);
                startActivity(intent);
            }
        });
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JiFenActivity.class);
                startActivity(intent);
            }
        });
        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LiJuanActivity.class);
                startActivity(intent);
            }
        });
        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LiJuanActivity.class);
                startActivity(intent);
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Gengduo.class);
                startActivity(intent);
            }
        });


        textView21.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences3 = getActivity().getSharedPreferences("name2", Context.MODE_PRIVATE);
                name2 = sharedPreferences3.getString("name2", null);

                if (getActivity().getSharedPreferences("zhanghao", Context.MODE_PRIVATE).getBoolean("isDenglu", false)) {
                    Intent intent = new Intent(getActivity(), MyZiLiaoActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), Denglu.class);
                    startActivity(intent);
                }

            }
        });
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("adssadfasd", "onViewCreated");

    }
    class myPagerAdapter extends FragmentPagerAdapter {
        String[] title = {"喜欢的礼物", "喜欢的攻略"};
        public myPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return oneFragment;
                case 1:
                    return twoFragment;
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            return title.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}







