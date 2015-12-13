package com.xiaoliwu.yll.adapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.MyAppliction;
import com.xiaoliwu.yll.fragment.OneFragment;
import com.xiaoliwu.yll.fragment.TwoFragment;
import com.xiaoliwu.yll.json.HotBean;
import com.xiaoliwu.yll.utils.MySqlOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/7.
 */
public class Adaptergong extends BaseAdapter {
    private Fragment context;
    BitmapUtils bitmapUtils;
    private MySqlOpenHelper mySqlOpenHelper;
    private Cursor cursor;
    private SQLiteDatabase yuhaiDatabase;
    List<HotBean> list;

    public Adaptergong(Fragment context) {
        this.context = context;
        list=new ArrayList<>();
        bitmapUtils = MyAppliction.getUtils();
        mySqlOpenHelper=new MySqlOpenHelper(context.getActivity());
        yuhaiDatabase= mySqlOpenHelper.getWritableDatabase();
            cursor = yuhaiDatabase.query("yuhaibo", null, null, null, null, null, null);


        HotBean hotBean;
        while (cursor.moveToNext()) {
            int title = cursor.getColumnIndex("title");// cursor.getColumnIndex() 获取指定名称的列的id 在第几列
            String name = cursor.getString(title);//  cursor.getstring 获取当前行的指定列的值
            String imagepath = cursor.getString(cursor.getColumnIndex("imagepath"));
            String path = cursor.getString(cursor.getColumnIndex("path"));
            Log.e("adssadfasd", path);
            hotBean=new HotBean();
            hotBean.setImage(imagepath);
            hotBean.setPath(path);

            hotBean.setName(name);
            list.add(hotBean);

        }
        cursor.close();//关闭 cursor
        yuhaiDatabase.close();//关闭数据库

    }

    @Override
    public int getCount() {
        int  count= cursor.getCount();
        Log.e("sdefsdef", count + "");
        return cursor.getCount();

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
        ViewHolder viewHolder ;
        if(convertView == null) {
            convertView = LayoutInflater.from(context.getActivity()).inflate(R.layout.gonglu_item,null);
            viewHolder = new ViewHolder();
            viewHolder.img1 = (ImageView) convertView.findViewById(R.id.yv11);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position).getName());
        bitmapUtils.display(viewHolder.img1,list.get(position).getImage());
        return convertView;
    }
    static class ViewHolder {
        ImageView img1;
        TextView textView;
    }
}
