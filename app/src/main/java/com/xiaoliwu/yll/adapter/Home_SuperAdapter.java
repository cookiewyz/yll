package com.xiaoliwu.yll.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.xiaoliwu.yll.utils.MySqlOpenHelper;

import java.util.List;

/**
 * Created by Administrator on 2015/10/20.
 */
public class Home_SuperAdapter  extends BaseAdapter {
    private Context  context;
    BitmapUtils bitmapUtils;
    private List<Home_SuperBean> list;
    private MySqlOpenHelper mySqlOpenHelper;
    public Home_SuperAdapter(List<Home_SuperBean> list, Context context) {
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
        mySqlOpenHelper= new MySqlOpenHelper(context);
        ViewHalder viewHalder;
        if (convertView==null){
            viewHalder = new ViewHalder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_super_home,null);
            viewHalder.iv1 = (ImageView) convertView.findViewById(R.id.iv_home_gift);
            viewHalder.iv2 = (ImageView) convertView.findViewById(R.id.iv_home_background);
            viewHalder.iv3 = (ImageView) convertView.findViewById(R.id.iv_home_like);
            viewHalder.tv1 = (TextView) convertView.findViewById(R.id.tv_home_title);
            viewHalder.tv2 = (TextView) convertView.findViewById(R.id.tv_home_count);
            convertView.setTag(viewHalder);
        }else {
           viewHalder = (ViewHalder) convertView.getTag();
        }
        SQLiteDatabase writableDatabase = mySqlOpenHelper.getWritableDatabase();
        Cursor yuhaibo = writableDatabase.query("yuhaibo", null, " imagepath =?", null, null, null, null);//查找
         if (yuhaibo.getCount() > 0){
            viewHalder.iv3.setImageResource(R.mipmap.ic_action_compact_favourite_selected);
        }
        viewHalder.tv1.setText(list.get(position).getShare_msg());
        viewHalder.tv2.setText(list.get(position).getLikes_count()+"");
        bitmapUtils.display(viewHalder.iv1,list.get(position).getCover_image_url());

        return convertView;
    }
    class ViewHalder {
        ImageView iv1,iv2,iv3;
        TextView tv1,tv2;
    }
}
