package com.xiaoliwu.yll.activity;

import android.app.Application;
import android.content.Context;

import com.lidroid.xutils.BitmapUtils;
import com.xiaoliwu.yll.utils.BitmapHelper;

/**
 * Created by Administrator on 2015/10/20.
 */
public class MyAppliction extends Application {

    private static BitmapUtils utils;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        utils = BitmapHelper.init(this);
    }


    public static  BitmapUtils getUtils(){
        if (utils == null){
            utils = BitmapHelper.init(context);
        }
        return utils;
    }

}
