package com.xiaoliwu.yll.adapter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.MyAppliction;
import com.xiaoliwu.yll.json.CommentBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/10/23.
 */
public class CommentAdapter extends BaseAdapter {
    private Fragment context;
    private List<CommentBean> list;
    private BitmapUtils bitmapUtils;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间的
    private int width, height;


    public CommentAdapter(Fragment context, List<CommentBean> list) {
        this.context = context;
        this.list = list;
        bitmapUtils = MyAppliction.getUtils();
    }

    public CommentAdapter() {

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
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context.getActivity()).inflate(R.layout.comment_item, null);
            viewHolder = new ViewHolder();
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            viewHolder.nickname = (TextView) convertView.findViewById(R.id.nickname);
            viewHolder.content = (TextView) convertView.findViewById(R.id.content);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //给控件上设置内容
        bitmapUtils.display(viewHolder.avatar, list.get(position).getAvatar_url(), new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                Bitmap bitmap1 = toRoundBitmap(bitmap);
                viewHolder.avatar.setImageBitmap(bitmap1);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });
        viewHolder.nickname.setText(list.get(position).getNickname());
        viewHolder.content.setText(list.get(position).getContent());
        viewHolder.time.setText(format.format(new Date(Long.parseLong(String.valueOf(list.get(position).getCreated_at())) * 1000)));
        return convertView;
    }
    static class ViewHolder {
        ImageView avatar;
        TextView nickname,time, content;
    }
    /**
     * 转换图片成圆形
     * @param bitmap 传入Bitmap对象
     * @return
     */
    public Bitmap toRoundBitmap(Bitmap bitmap) {
        if (bitmap!=null){
             width = bitmap.getWidth();
             height = bitmap.getHeight();
        }
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        if (width!=0&&height!=0){

        }
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int)left, (int)top, (int)right, (int)bottom);
        final Rect dst = new Rect((int)dst_left, (int)dst_top, (int)dst_right, (int)dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }
}
