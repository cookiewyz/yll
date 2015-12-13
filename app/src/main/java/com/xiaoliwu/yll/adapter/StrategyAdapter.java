package com.xiaoliwu.yll.adapter;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.json.List_GridViewBean;

import java.util.List;

/**
 * Created by Administrator on 2015/10/26.
 */
public class StrategyAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private Fragment context;
    List<List_GridViewBean.DataEntity.ChannelGroupsEntity> list;

    public StrategyAdapter(Fragment context,List<List_GridViewBean.DataEntity.ChannelGroupsEntity> list) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if (convertView == null) {
            viewHodler = new ViewHodler();
            convertView = LayoutInflater.from(context.getActivity()).inflate(R.layout.list_item, null);
            viewHodler.gridView = (GridView) convertView.findViewById(R.id.gridView);
            viewHodler.list_name = (TextView) convertView.findViewById(R.id.list_name);
            viewHodler.gridView.setOnItemClickListener(this);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        // TODO: 2015/10/27
        List<List_GridViewBean.DataEntity.ChannelGroupsEntity.ChannelsEntity> list1 = list.get(position).getChannels();
        viewHodler.list_name.setText(list.get(position).getName());//设置内容
        Strategy_GridViewAdapter strategy_GridViewAdapter = new Strategy_GridViewAdapter(context.getActivity(),list1);
        viewHodler.gridView.setAdapter(strategy_GridViewAdapter);
        viewHodler.gridView.setTag(position);
        return convertView;
    }

    /**
     * gridView上的每条的点击事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int tag = (int) parent.getTag();
        if (tag == 0 && position == 0) {
            // TODO: 2015/10/30  gridView上的每条的点击事件
        }
        Toast.makeText(context.getActivity(), "点击了" + tag + position, Toast.LENGTH_SHORT).show();
    }

    static class ViewHodler {
        private GridView gridView;
        private TextView list_name;
    }
}
