package com.xiaoliwu.yll.activity.Home_Acivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.xiaoliwu.yll.activity.MyAppliction;
import com.xiaoliwu.yll.myinterface.ServerUrl;
import com.xiaoliwu.yll.utils.MySqlOpenHelper;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class Super_Details_Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView,iv1,iv_like;
    private TextView textView,tv1,tv2,tv3;
    private WebView webView;
    private BitmapUtils bitmapUtils;
    private LinearLayout linearLayout,l1,l2;
    private int  id1;
    private String title;
    private String cover_image_url;
    private LinearLayout ll_home_share1,ll_home_likes1;
    private boolean isshoucang;//是否收藏
    private SharedPreferences sharedPreferences;
    private String content_url;
    private MySqlOpenHelper mySqlOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_super__details_);
        getSupportActionBar().hide();
        imageView = (ImageView)findViewById(R.id.iv_details);
        mySqlOpenHelper = new MySqlOpenHelper(this);
        sharedPreferences=this.getSharedPreferences("gonglue",this.MODE_PRIVATE);
        ll_home_likes1 = (LinearLayout) findViewById(R.id.ll_home_likes1);
        ll_home_share1 = (LinearLayout) findViewById(R.id.ll_home_share1);
        textView  = (TextView)findViewById(R.id.tv_details);
        tv1 = (TextView)findViewById(R.id.tv_details_likes);
        tv2 = (TextView)findViewById(R.id.tv_details_share);
        tv3 = (TextView)findViewById(R.id.tv_details_comment);
        iv1 = (ImageView)findViewById(R.id.iv_fanhui_special_two);
        iv_like = (ImageView) findViewById(R.id.iv_like);
        linearLayout = (LinearLayout)findViewById(R.id.ll_home_comment);
        l1 = (LinearLayout) findViewById(R.id.ll_home_share);
        l2 = (LinearLayout) findViewById(R.id.ll_home_likes);
        Intent intent = getIntent();
        id1 = intent.getIntExtra("init",0);
        int id2 = intent.getIntExtra("init1",0);
        if (id2!=0){
            id1=id2;
            Data();
        }
        Log.e("ee",id1+"");
        linearLayout.setOnClickListener(this);
        iv_like.setOnClickListener(this);
        iv1.setOnClickListener(this);
        webView = (WebView)findViewById(R.id.wv_details);
        ll_home_likes1.setOnClickListener(this);
        ll_home_share1.setOnClickListener(this);
        Data();
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
    private void Data(){
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET,ServerUrl.HOME_DETAILS + id1, new RequestCallBack<Object>() {

            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                bitmapUtils = MyAppliction.getUtils();
                JSONObject data = JSON.parseObject((String) responseInfo.result).getJSONObject("data");
                cover_image_url = data.getString("cover_image_url");
                Log.e("ee",cover_image_url);
                title = data.getString("title");
                int likes_count = data.getInteger("likes_count");
                int shares_count = data.getInteger("shares_count");
                int comments_count = data.getInteger("comments_count");
                content_url = data.getString("content_url");
                textView.setText(title);
                webView.loadUrl(content_url);
                bitmapUtils.display(imageView, cover_image_url);
                tv1.setText(likes_count + "");
                tv2.setText(shares_count + "");
                tv3.setText(comments_count + "");
                if (sharedPreferences.getBoolean(title,false)){
                    iv_like.setImageResource(R.mipmap.ic_action_compact_favourite_selected);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                    Toast.makeText(Super_Details_Activity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
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
            case R.id.ll_home_share1:
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
            case R.id.ll_home_likes1:
                SQLiteDatabase writableDatabase = mySqlOpenHelper.getWritableDatabase();
                // TODO: 2015/10/27
//                String substring = cover_image_url.substring(cover_image_url.lastIndexOf("/") + 1);
                Cursor yuhaibo = writableDatabase.query("yuhaibo1", null, " imagepath =?", new String[]{cover_image_url}, null, null, null);//查找
                isshoucang = (yuhaibo.getCount() > 0);//如果有数据就表示已经收藏了吧
                yuhaibo.close();
                if (!isshoucang) {
                    ContentValues contentValues = new ContentValues();

                    contentValues.put("imagepath", cover_image_url);
                    contentValues.put("title",title);
                    contentValues.put("path",id1 );
                    long yuhaibo1 = writableDatabase.insert("yuhaibo1", null, contentValues);
                    iv_like.setImageResource(R.mipmap.ic_action_compact_favourite_selected);
                    Toast.makeText(this, yuhaibo1 > 0 ? "收藏成功" : "收藏失败", Toast.LENGTH_SHORT).show();
                    if (yuhaibo1 > 0) {
                        sharedPreferences.edit().putBoolean(title,true).apply();
                        isshoucang = true;
                    }
                } else {
                    int i = writableDatabase.delete("yuhaibo1", "imagepath =?", new String[]{cover_image_url});//从数据库中删除当前收藏
                    iv_like.setImageResource(R.mipmap.ic_action_compact_favourite_normal);
                    Toast.makeText(this, i > 0 ? "取消收藏成功" : "取消收藏失败", Toast.LENGTH_SHORT).show();
                    if (i > 0) {
                        sharedPreferences.edit().putBoolean(title,false).apply();
                        isshoucang = false;
                    }
                }
                writableDatabase.close();
                break;
        }
    }

}
