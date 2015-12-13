package com.xiaoliwu.yll.activity.Home_Acivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.Denglu;
import com.xiaoliwu.yll.activity.Gengduo;
import com.xiaoliwu.yll.activity.MyAppliction;
import com.xiaoliwu.yll.myinterface.ServerUrl;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class Home_Good_Acitvity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView,iv1;
    private TextView textView,tv1,tv2,tv3;
    private WebView webView;
    private BitmapUtils bitmapUtils;
    private LinearLayout linearLayout,linearLayout1,linearLayout2;
    private int  id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_home__good__acitvity);
        getSupportActionBar().hide();
        imageView = (ImageView)findViewById(R.id.iv_details);
        textView  = (TextView)findViewById(R.id.tv_details);
        tv1 = (TextView)findViewById(R.id.tv_details_likes);
        tv2 = (TextView)findViewById(R.id.tv_details_share);
        tv3 = (TextView)findViewById(R.id.tv_details_comment);
        iv1 = (ImageView)findViewById(R.id.iv_fanhui_special_two);
        linearLayout = (LinearLayout)findViewById(R.id.ll_home_comment);
        linearLayout1 = (LinearLayout)findViewById(R.id.ll_home_share);
        linearLayout2 = (LinearLayout)findViewById(R.id.ll_home_likes);
        Intent intent = getIntent();
        id1 = intent.getIntExtra("init",0);
        Log.e("ee", id1 + "");
        linearLayout1.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        iv1.setOnClickListener(this);
        webView = (WebView)findViewById(R.id.wv_details);
        Data();
    }
    private void Data(){
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, ServerUrl.HOME_GOOD + id1, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                bitmapUtils = MyAppliction.getUtils();
                JSONObject data = JSON.parseObject((String) responseInfo.result).getJSONObject("data");
                String cover_image_url = data.getString("cover_image_url");
                Log.e("ee",cover_image_url);
                String title = data.getString("title");
                int likes_count = data.getInteger("likes_count");
                int shares_count = data.getInteger("shares_count");
                int comments_count = data.getInteger("comments_count");
                String content_url = data.getString("content_url");
                textView.setText(title);
                webView.loadUrl(content_url);
                bitmapUtils.display(imageView, cover_image_url);
                tv1.setText(likes_count + "");
                tv2.setText(shares_count + "");
                tv3.setText(comments_count + "");
            }

            @Override
            public void onFailure(HttpException e, String s) {
                    Toast.makeText(Home_Good_Acitvity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_fanhui_special_two:
                this.finish();
                break;
            case R.id.ll_home_comment://跳转到评论界面
                Intent intent;
                intent = new Intent(this,Home_Comment_Activity.class);
                intent.putExtra("data", id1);
                startActivity(intent);
                break;
            case R.id.ll_home_share:
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
            break;
            case R.id.ll_home_likes:
                startActivity(new Intent(Home_Good_Acitvity.this, Denglu.class));
                break;
        }
    }
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
}
