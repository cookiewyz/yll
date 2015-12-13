package com.xiaoliwu.yll.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.lidroid.xutils.BitmapUtils;
import com.xiaoliwu.yll.R;

/**
 * Created by wyz-pc on 2015/9/29.
 */
public class BitmapHelper {

    public  static BitmapUtils init(Context context) {
        BitmapUtils utils = new BitmapUtils(context);
        utils.configDefaultLoadingImage(R.mipmap.ig_holder_image);//默认背景图片
        utils.configDefaultLoadFailedImage(R.mipmap.ig_holder_image);//加载失败的图片
        utils.configDefaultBitmapConfig(Bitmap.Config.ARGB_8888);//设置图片压缩类型
        return utils;
    }
}
