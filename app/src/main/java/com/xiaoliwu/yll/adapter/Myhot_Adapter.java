package com.xiaoliwu.yll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.MyAppliction;
import com.xiaoliwu.yll.json.HotBean;

import java.util.List;

/**
 * Created by Administrator on 2015/10/17.
 */
public class Myhot_Adapter extends BaseAdapter {
    private Context context;
    private List<HotBean> list;//数据源
    BitmapUtils bitmapUtils;

    public Myhot_Adapter(Context context, List<HotBean> list) {
        this.context = context;
        this.list = list;
        bitmapUtils = MyAppliction.getUtils();
    }



    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_item, null);
            viewHolder = new ViewHolder();
            viewHolder.hot_item_image = (ImageView) convertView.findViewById(R.id.hot_item_image);
            viewHolder.hot_item_name = (TextView) convertView.findViewById(R.id.hot_item_name);
            viewHolder.hot_item_price = (TextView) convertView.findViewById(R.id.hot_item_price);
            viewHolder.hot_item_favorites_count = (TextView) convertView.findViewById(R.id.hot_item_favorites_count);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //给控件上设置内容
        bitmapUtils.display(viewHolder.hot_item_image,list.get(position).getImage());
        viewHolder.hot_item_name.setText(list.get(position).getName());
        viewHolder.hot_item_price.setText(list.get(position).getPrice());
        viewHolder.hot_item_favorites_count.setText(list.get(position).getFavorites_count() + "");
        return convertView;
    }

    static class ViewHolder {
        ImageView hot_item_image;
        TextView hot_item_name, hot_item_price, hot_item_favorites_count;
    }

}
