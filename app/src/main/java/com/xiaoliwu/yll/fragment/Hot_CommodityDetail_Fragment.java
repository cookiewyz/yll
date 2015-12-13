package com.xiaoliwu.yll.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.CommodityDetail_BuyActivity;
import com.xiaoliwu.yll.adapter.CommodityDetail_PagerAdapter;
import com.xiaoliwu.yll.json.HotDetailBean;
import com.xiaoliwu.yll.utils.MyScrollView;
import com.xiaoliwu.yll.utils.MySqlOpenHelper;
import com.xiaoliwu.yll.utils.SlideShowView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Hot_CommodityDetail_Fragment extends Fragment implements MyScrollView.OnScrollListener, View.OnClickListener {
    String path;//获取传过来的商品详情地址
    private SlideShowView commodityDetail_slide;
    private String request;
    //自定义轮播图的资源
    private String[] imageUrls;
    private TextView commodityDetail_name, commodityDetail_price, commodityDetail_description, commodityDetail_button_title;
    private MyScrollView commodityDetail_myScrollView;
    private LinearLayout linearLayout1, linearLayout2, shangfang;
    private int searchLayoutTop;//上方的高度
    private TabLayout commodityDetail_tabLayout;
    private ViewPager commodityDetail_viewPager;
    private List<Fragment> list = new ArrayList();
    private List<String> list_title = new ArrayList<>();
    String purchase_url;//购买的地址
    private LinearLayout commodityDetail_like;
    private MySqlOpenHelper mySqlOpenHelper;
    private boolean isshoucang;//是否收藏
    private ImageView like_image;
    private String cover_image_url;
    private String title;
    private HttpUtils httpUtils;
    private SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot__commodity_detail, null);
        sharedPreferences=getActivity().getSharedPreferences("state",getActivity().MODE_PRIVATE);
        mySqlOpenHelper = new MySqlOpenHelper(getActivity());
        like_image = (ImageView) view.findViewById(R.id.like_image);
        commodityDetail_slide = (SlideShowView) view.findViewById(R.id.commodityDetail_slide);
        commodityDetail_name = (TextView) view.findViewById(R.id.commodityDetail_name);
        commodityDetail_price = (TextView) view.findViewById(R.id.commodityDetail_price);
        commodityDetail_description = (TextView) view.findViewById(R.id.commodityDetail_description);
        commodityDetail_button_title = (TextView) view.findViewById(R.id.commodityDetail_button_title);
        commodityDetail_myScrollView = (MyScrollView) view.findViewById(R.id.commodityDetail_myScrollView);
        commodityDetail_like = (LinearLayout) view.findViewById(R.id.commodityDetail_like);
        linearLayout1 = (LinearLayout) view.findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayout2);
        shangfang = (LinearLayout) view.findViewById(R.id.shangfang);
        commodityDetail_myScrollView.setOnScrollListener(this);
        commodityDetail_button_title.setOnClickListener(this);
        commodityDetail_like.setOnClickListener(this);
        commodityDetail_tabLayout = (TabLayout) view.findViewById(R.id.commodityDetail_tabLayout);
        commodityDetail_viewPager = (ViewPager) view.findViewById(R.id.commodityDetail_viewPager);
        final IntroduceFragment introduceFragment = new IntroduceFragment();
        final CommentFragment commentFragment = new CommentFragment();
        Bundle bundle = getArguments();
        if (bundle != null) {
            path = bundle.getString("path");
        }
        commentFragment.setPath(path);
        httpUtils = new HttpUtils();
        Log.e("aaaa",""+path);
        httpUtils.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {

                request = (String) responseInfo.result;
                HotDetailBean hotDetailBean = new Gson().fromJson(request, HotDetailBean.class);
                imageUrls = new String[hotDetailBean.getData().getImage_urls().size()];
                for (int i = 0; i < hotDetailBean.getData().getImage_urls().size(); i++) {
                    String s = hotDetailBean.getData().getImage_urls().get(i);
                    imageUrls[i] = s;
                }
                commodityDetail_slide.setImageUrls(imageUrls);
                TextPaint paint = commodityDetail_name.getPaint();
                TextPaint paint1 = commodityDetail_price.getPaint();
                TextPaint paint2 = commodityDetail_description.getPaint();
                paint.setFakeBoldText(true);
                paint1.setFakeBoldText(true);
                paint2.setFakeBoldText(true);
                title = hotDetailBean.getData().getName();
                if (sharedPreferences.getBoolean(title,false)){
                    like_image.setImageResource(R.mipmap.ic_action_compact_favourite_selected);
                }
                commodityDetail_name.setText(title);
                commodityDetail_price.setText("￥" + hotDetailBean.getData().getPrice());
                commodityDetail_description.setText(hotDetailBean.getData().getDescription());
                commodityDetail_button_title.setText(hotDetailBean.getData().getSource().getButton_title());
                purchase_url = hotDetailBean.getData().getPurchase_url();
                cover_image_url = hotDetailBean.getData().getCover_image_url();//收藏时候的图片的地址
                int comments_count = hotDetailBean.getData().getComments_count();//评论数量
                String detail_html = hotDetailBean.getData().getDetail_html();
                introduceFragment.setDetail_html(detail_html);

                list.add(introduceFragment);
                list.add(commentFragment);
                list_title.add("图文介绍");
                list_title.add("评论" + "(" + comments_count + ")");

                CommodityDetail_PagerAdapter commodityDetail_pagerAdapter = new CommodityDetail_PagerAdapter(getChildFragmentManager(), list, list_title);
                commodityDetail_viewPager.setAdapter(commodityDetail_pagerAdapter);
                commodityDetail_tabLayout.setTabMode(TabLayout.MODE_FIXED);
                commodityDetail_tabLayout.setupWithViewPager(commodityDetail_viewPager);//关联viewpager和tabLayout

            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "网络错误......", Toast.LENGTH_SHORT).show();
                }
            }
        });
        shangfang.measure(0, 0);
        searchLayoutTop = shangfang.getMeasuredHeight();//获取searchLayout的顶部位置
        return view;
    }

    //监听滚动Y值变化，通过addView和removeView来实现悬停效果
    @Override
    public void onScroll(int scrollY) {
        if (scrollY >= searchLayoutTop) {
            if (commodityDetail_tabLayout.getParent() != linearLayout2) {
                linearLayout1.removeView(commodityDetail_tabLayout);
                linearLayout2.addView(commodityDetail_tabLayout);
            }
        } else {
            if (commodityDetail_tabLayout.getParent() != linearLayout1) {
                linearLayout2.removeView(commodityDetail_tabLayout);
                linearLayout1.addView(commodityDetail_tabLayout);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commodityDetail_button_title://购买
                Intent intent = new Intent(getActivity(), CommodityDetail_BuyActivity.class);
                intent.putExtra("purchase_url", purchase_url);
                startActivity(intent);
                break;
            case R.id.commodityDetail_like://收藏
                SQLiteDatabase writableDatabase = mySqlOpenHelper.getWritableDatabase();
                //// TODO: 2015/10/27  
                Cursor yuhaibo = writableDatabase.query("yuhaibo", null, " imagepath =?", new String[]{cover_image_url}, null, null, null);//查找
                isshoucang = (yuhaibo.getCount() > 0);//如果有数据就表示已经收藏了吧
                yuhaibo.close();
                if (!isshoucang) {
                    ContentValues contentValues = new ContentValues();

                    contentValues.put("imagepath", cover_image_url);
                    contentValues.put("title",title);
                    contentValues.put("path",path);

                    long yuhaibo1 = writableDatabase.insert("yuhaibo", null, contentValues);
                    like_image.setImageResource(R.mipmap.ic_action_compact_favourite_selected);
                    Toast.makeText(this.getActivity(), yuhaibo1 > 0 ? "收藏成功" : "收藏失败", Toast.LENGTH_SHORT).show();
                    if (yuhaibo1 > 0) {
                        sharedPreferences.edit().putBoolean(title,true).apply();
                        isshoucang = true;
                    }
                } else {
                    int i = writableDatabase.delete("yuhaibo", "imagepath =?", new String[]{cover_image_url});//从数据库中删除当前收藏
                    like_image.setImageResource(R.mipmap.ic_action_compact_favourite_normal);
                    Toast.makeText(this.getActivity(), i > 0 ? "取消收藏成功" : "取消收藏失败", Toast.LENGTH_SHORT).show();
                    if (i > 0) {
                        sharedPreferences.edit().putBoolean(title,false).apply();
                        isshoucang = false;
                    }
                }
                writableDatabase.close();
                break;
        }
    }
}
