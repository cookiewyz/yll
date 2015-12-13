package com.xiaoliwu.yll.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
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
import com.xiaoliwu.yll.json.Home_Comment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/10/24.
 */
public class Home_Comment_Adapter extends BaseAdapter {
    private SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
    private Context context;
    private List<Home_Comment> list;
    private BitmapUtils bitmaputils;
    public Home_Comment_Adapter(Context context, List<Home_Comment> list) {
        this.context = context;
        this.list = list;
        bitmaputils = MyAppliction.getUtils();
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
        final ViewHolderOne viewHolderOne;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comment, null);
            viewHolderOne = new ViewHolderOne();
            viewHolderOne.imageView = (ImageView) convertView.findViewById(R.id.iv_user);
            viewHolderOne.tv1 = (TextView)convertView.findViewById(R.id.tv_user);
            viewHolderOne.tv2  = (TextView) convertView.findViewById(R.id.tv_time_zhiqian);
           // viewHolderOne.tv3 = (TextView) convertView.findViewById(R.id.tv_time_now);
            viewHolderOne.tv4 = (TextView) convertView.findViewById(R.id.tv_comment);
            convertView.setTag(viewHolderOne);
        }else {
             viewHolderOne = (ViewHolderOne) convertView.getTag();
        }
        bitmaputils.display(viewHolderOne.imageView, list.get(position).getAvatar_url(), new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                Bitmap bitmap1 = toRoundBitmap(bitmap);
                viewHolderOne.imageView.setImageBitmap(bitmap1);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });        viewHolderOne.tv1.setText(list.get(position).getNickname());
        viewHolderOne.tv2.setText(format.format(new Date(Long.parseLong(String.valueOf(list.get(position).getCreated_at())) * 1000)) + "");
        viewHolderOne.tv4.setText(list.get(position).getContent());
        return convertView;
    }
    class  ViewHolderOne{
        private ImageView imageView;
        private TextView tv1,tv2,tv3,tv4;
    }
    /**
     * ת��ͼƬ��Բ��
     *
     * @param bitmap ����Bitmap����
     * @return
     */
    public Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
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
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
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
