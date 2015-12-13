package com.xiaoliwu.yll.adapter;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.json.Classify_giftBean;

import java.util.List;

/**
 * Created by Administrator on 2015/10/28.
 */
public class Classify_gift_ListViewAdapter extends BaseAdapter {
    private Fragment context;
    private List<Classify_giftBean> list;
    private int selectedPosition = 0;// 选中的位置
    public Classify_gift_ListViewAdapter(Fragment context,List<Classify_giftBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context.getActivity()).inflate(R.layout.classify_gift_listview_item, null);
            viewHolder.listname = (TextView) convertView.findViewById(R.id.listname);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //ListView上item背景颜色切换
        if (selectedPosition == position) {
            viewHolder.listname.setSelected(true);
            viewHolder.listname.setPressed(true);
            convertView.setBackgroundColor(Color.WHITE);
        } else {
            viewHolder.listname.setSelected(false);
            viewHolder.listname.setPressed(false);
            convertView.setBackgroundColor(Color.parseColor("#F6F6F7"));

        }
        //设置控件
        viewHolder.listname.setText(list.get(position).getListname());
        return convertView;
    }
    static class ViewHolder{
        private TextView listname;
    }
}
