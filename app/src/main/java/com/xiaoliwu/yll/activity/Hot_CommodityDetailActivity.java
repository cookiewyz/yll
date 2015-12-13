package com.xiaoliwu.yll.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.fragment.Hot_CommodityDetail_Fragment;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class Hot_CommodityDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView commodityDetail_action_back,commodityDetail_menu_share;
//    private IntentFilter intentFilter;
//    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_hot__commodity_detail);
        //动态注册广播
//        intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
//        myBroadcastReceiver = new MyBroadcastReceiver();
//        registerReceiver(myBroadcastReceiver, intentFilter);
        commodityDetail_action_back = (ImageView) findViewById(R.id.commodityDetail_action_back);
        commodityDetail_menu_share = (ImageView) findViewById(R.id.commodityDetail_menu_share);
        commodityDetail_action_back.setOnClickListener(this);
        commodityDetail_menu_share.setOnClickListener(this);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Hot_CommodityDetail_Fragment hot_commodityDetail_fragment = new Hot_CommodityDetail_Fragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.Hot_CommodityDetailActivity_frameLayout, hot_commodityDetail_fragment).commit();
        if (extras!=null) {
            hot_commodityDetail_fragment.setArguments(extras);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (intentFilter != null) {
//            this.unregisterReceiver(myBroadcastReceiver);
//        }
    }
    /**
     * 设置状态栏背景状态
     */
    private void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        // 创建状态栏的管理实例
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(0);//状态栏无背景
        // 设置一个颜色给系统栏
        tintManager.setTintColor(Color.parseColor("#F84E4E"));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commodityDetail_action_back:
                finish();
                break;
            case R.id.commodityDetail_menu_share:
               showShare();
                break;
        }
    }
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
