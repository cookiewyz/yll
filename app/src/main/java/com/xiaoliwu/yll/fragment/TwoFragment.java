package com.xiaoliwu.yll.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.Home_Acivity.Super_Details_Activity;
import com.xiaoliwu.yll.activity.Hot_CommodityDetailActivity;
import com.xiaoliwu.yll.adapter.Adaptergong;
import com.xiaoliwu.yll.adapter.Adapterli;
import com.xiaoliwu.yll.json.HotBean;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    private int offset = 0;
    private List<HotBean> list;
    private int photoid;//拿到每一个图片的id
    private Adaptergong myhot_adapter = null;
    private SwipeRefreshLayout swipeRefreshLayout;
    Adapterli adaptergong;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout2, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.lv2);
        listView.setOnItemClickListener(this);
    }
    @Override
    public void onResume() {
        super.onResume();
        adaptergong = new Adapterli(TwoFragment.this);
        listView.setAdapter(adaptergong);
        adaptergong.notifyDataSetChanged();
        Log.e("asdfsdf", "onResume");
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HotBean item = (HotBean) adaptergong.getItem(position);
        Intent intent = new Intent(getActivity(), Super_Details_Activity.class);
        int path = item.getId();
        Log.i("test", "onItemClick ----" + path);
        Bundle bundle = new Bundle();
        bundle.putInt("init1", path);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
