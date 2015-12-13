package com.xiaoliwu.yll.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.Home_Acivity.Select_Activity;
import com.xiaoliwu.yll.adapter.Classify_gift_GridViewAdapter;
import com.xiaoliwu.yll.adapter.Classify_gift_ListViewAdapter;
import com.xiaoliwu.yll.json.Classify_giftBean;
import com.xiaoliwu.yll.json.Classify_gift_gridViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/24.
 */
public class GiftFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private RelativeLayout gift_relativeLayout;
    private ListView gift_listView;
    private LinearLayout gift_linearLayout;
    private List<Classify_giftBean> list;
    private GridView classify_gift_gridView;
    private List<Classify_gift_gridViewBean> list1, list2, list3, list4, list5, list6, list7, list8, list9, list10, list11, list12, list13, list14, list15, list16, list17;
    private Classify_gift_ListViewAdapter classifyGiftListViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift, null);
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();
        list5 = new ArrayList<>();
        list6 = new ArrayList<>();
        list7 = new ArrayList<>();
        list8 = new ArrayList<>();
        list9 = new ArrayList<>();
        list10 = new ArrayList<>();
        list11 = new ArrayList<>();
        list12 = new ArrayList<>();
        list13 = new ArrayList<>();
        list14 = new ArrayList<>();
        list15 = new ArrayList<>();
        list16 = new ArrayList<>();
        list17 = new ArrayList<>();
        gift_relativeLayout = (RelativeLayout) view.findViewById(R.id.gift_relativeLayout);
        gift_listView = (ListView) view.findViewById(R.id.gift_listView);
        gift_listView.setDivider(null);//去掉ListView的分隔线
        gift_linearLayout = (LinearLayout) view.findViewById(R.id.gift_linearLayout);
        gift_relativeLayout.setOnClickListener(this);
        gift_listView.setOnItemClickListener(this);
        dataRequest();
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.classify_gift_gridview, null);
        //LinearLayout上加载一个gridView
        classify_gift_gridView = (GridView) view1.findViewById(R.id.classify_gift_gridView);
        gift_linearLayout.addView(view1);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gift_relativeLayout:
                startActivity(new Intent(this.getActivity(), Select_Activity.class));
                break;
        }
    }

    /**
     * 请求数据
     * http://api.liwushuo.com/v2/item_categories/tree
     */
    public void dataRequest() {
        HttpUtils httpUtils = new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, "http://api.liwushuo.com/v2/item_categories/tree",
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        JSONArray items = JSON.parseObject(responseInfo.result).getJSONObject("data").getJSONArray("categories");
                        Classify_giftBean bean = null;
                        for (int i = 0; i < items.size(); i++) {
                            String listname = (items.getJSONObject(i)).getString("name");
                            bean = new Classify_giftBean();
                            bean.setListname(listname);
                            JSONArray array = (items.getJSONObject(i)).getJSONArray("subcategories");
                            for (int j = 0; j < array.size(); j++) {
                                String icon_url = (array.getJSONObject(j)).getString("icon_url");
                                String name = (array.getJSONObject(j)).getString("name");
                                Classify_gift_gridViewBean bean1 = new Classify_gift_gridViewBean();
                                if (i == 0) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list1.add(bean1);
                                }
                                if (i == 1) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list2.add(bean1);
                                }
                                if (i == 2) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list3.add(bean1);
                                }
                                if (i == 3) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list4.add(bean1);
                                }
                                if (i == 4) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list5.add(bean1);
                                }
                                if (i == 5) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list6.add(bean1);
                                }
                                if (i == 6) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list7.add(bean1);
                                }
                                if (i == 7) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list8.add(bean1);
                                }
                                if (i == 8) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list9.add(bean1);
                                }
                                if (i == 9) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list10.add(bean1);
                                }
                                if (i == 10) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list11.add(bean1);
                                }
                                if (i == 11) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list12.add(bean1);
                                }
                                if (i == 12) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list13.add(bean1);
                                }
                                if (i == 13) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list14.add(bean1);
                                }
                                if (i == 14) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list15.add(bean1);
                                }
                                if (i == 15) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list16.add(bean1);
                                }
                                if (i == 16) {
                                    bean1.setName(name);
                                    bean1.setIcon_url(icon_url);
                                    list17.add(bean1);
                                }
                            }
                            list.add(bean);
                        }

                        classifyGiftListViewAdapter = new Classify_gift_ListViewAdapter(GiftFragment.this, list);
                        gift_listView.setAdapter(classifyGiftListViewAdapter);

                        //第一次默认显示精品分类
                        Classify_gift_GridViewAdapter classify_gift_gridViewAdapter = new Classify_gift_GridViewAdapter(GiftFragment.this.getActivity(), list1);
                        classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter);
                    }

                    /**
                     * 请求失败执行
                     * @param error
                     * @param msg
                     */
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        error.printStackTrace();
//                        if (getActivity() != null) {
//                            Toast.makeText(getActivity(), "网络错误......", Toast.LENGTH_SHORT).show();
//                        }
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        classifyGiftListViewAdapter.setSelectedPosition(position);
        classifyGiftListViewAdapter.notifyDataSetInvalidated();
        switch (position) {
            case 0:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter = new Classify_gift_GridViewAdapter(this.getActivity(), list1);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter);
                break;
            case 1:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter1 = new Classify_gift_GridViewAdapter(this.getActivity(), list2);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter1);
                break;
            case 2:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter2 = new Classify_gift_GridViewAdapter(this.getActivity(), list3);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter2);
                break;
            case 3:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter3 = new Classify_gift_GridViewAdapter(this.getActivity(), list4);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter3);
                break;
            case 4:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter4 = new Classify_gift_GridViewAdapter(this.getActivity(), list5);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter4);
                break;
            case 5:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter5 = new Classify_gift_GridViewAdapter(this.getActivity(), list6);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter5);
                break;
            case 6:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter6 = new Classify_gift_GridViewAdapter(this.getActivity(), list7);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter6);
                break;
            case 7:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter7 = new Classify_gift_GridViewAdapter(this.getActivity(), list8);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter7);
                break;
            case 8:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter8 = new Classify_gift_GridViewAdapter(this.getActivity(), list9);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter8);
                break;
            case 9:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter9 = new Classify_gift_GridViewAdapter(this.getActivity(), list10);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter9);
                break;
            case 10:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter10 = new Classify_gift_GridViewAdapter(this.getActivity(), list11);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter10);
                break;
            case 11:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter11 = new Classify_gift_GridViewAdapter(this.getActivity(), list12);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter11);
                break;
            case 12:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter12 = new Classify_gift_GridViewAdapter(this.getActivity(), list13);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter12);
                break;
            case 13:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter13 = new Classify_gift_GridViewAdapter(this.getActivity(), list14);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter13);
                break;
            case 14:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter14 = new Classify_gift_GridViewAdapter(this.getActivity(), list15);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter14);
                break;
            case 15:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter15 = new Classify_gift_GridViewAdapter(this.getActivity(), list16);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter15);
                break;
            case 16:
                Classify_gift_GridViewAdapter classify_gift_gridViewAdapter16 = new Classify_gift_GridViewAdapter(this.getActivity(), list17);
                classify_gift_gridView.setAdapter(classify_gift_gridViewAdapter16);
                break;
        }

    }

}
