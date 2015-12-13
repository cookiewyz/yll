package com.xiaoliwu.yll.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.MyAppliction;
import com.xiaoliwu.yll.json.Home_SuperBean;
import com.xiaoliwu.yll.json.Special_Bean;

import java.util.List;

/**
 * Created by Administrator on 2015/10/22.
 */
public class SpecialAdapter extends BaseAdapter {
    private Context context;
    BitmapUtils bitmapUtils;
    private List<Special_Bean> list;

    public SpecialAdapter(List<Special_Bean> list, Context context) {
        this.list = list;
        this.context = context;
        bitmapUtils = MyAppliction.getUtils();
    }

    @Override
    public int getCount() {
        return list.size();
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
        ViewHalder viewHalder;
        if (convertView==null){
            viewHalder = new ViewHalder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_special,null);
            viewHalder.iv1 = (ImageView) convertView.findViewById(R.id.iv_special);
            viewHalder.tv1 = (TextView) convertView.findViewById(R.id.tv_title);
            viewHalder.tv2 = (TextView) convertView.findViewById(R.id.tv_subtitle);
            convertView.setTag(viewHalder);
        }else {
            viewHalder = (ViewHalder) convertView.getTag();
        }
        viewHalder.tv1.setText(list.get(position).getTitle());
        viewHalder.tv2.setText(list.get(position).getSubtitle());
        bitmapUtils.display(viewHalder.iv1,list.get(position).getCover_image_url());

        return convertView;
    }
    class ViewHalder {
        ImageView iv1;
        TextView tv1,tv2;
    }
}
