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
import com.xiaoliwu.yll.json.List_GridViewBean;

import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class Strategy_GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<List_GridViewBean.DataEntity.ChannelGroupsEntity.ChannelsEntity> list;
    private BitmapUtils bitmapUtils;

    public Strategy_GridViewAdapter(Context context,List<List_GridViewBean.DataEntity.ChannelGroupsEntity.ChannelsEntity> list) {
        this.context = context;
        this.list = list;
        bitmapUtils = MyAppliction.getUtils();
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
            viewHolder.gridView_image = (ImageView) convertView.findViewById(R.id.gridView_image);
            viewHolder.gridView_text = (TextView) convertView.findViewById(R.id.gridView_textView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        bitmapUtils.display(viewHolder.gridView_image,list.get(position).getIcon_url());
        viewHolder.gridView_text.setText(list.get(position).getName());
        // TODO: 2015/10/27  
        return convertView;
    }
    static class ViewHolder{
        private ImageView gridView_image;
        private TextView gridView_text;
    }
}
