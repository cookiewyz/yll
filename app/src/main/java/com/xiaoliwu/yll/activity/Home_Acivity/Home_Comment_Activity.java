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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.adapter.Home_Comment_Adapter;
import com.xiaoliwu.yll.json.Home_Comment;
import com.xiaoliwu.yll.myinterface.ServerUrl;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class Home_Comment_Activity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private List<Home_Comment> list;
    private int data;
    private Home_Comment home_comment;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_home__comment_);
        getSupportActionBar().hide();
        Intent intent = getIntent();
          data=intent.getIntExtra("data",0);
        listView= (ListView) findViewById(R.id.lv_home_comment);
        imageView = (ImageView) findViewById(R.id.iv_fanhui_special_two);
        imageView.setOnClickListener(this);
        Data();
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
        private void Data(){
            list = new ArrayList<>();
            HttpUtils httpUtils = new HttpUtils();
            httpUtils.send(HttpRequest.HttpMethod.GET, ServerUrl.HOME_COMMENT + data + "/comments?offset=0&limit=20", new RequestCallBack<Object>() {
                @Override
                public void onSuccess(ResponseInfo<Object> responseInfo) {
                    JSONArray data = JSON.parseObject((String) responseInfo.result).getJSONObject("data").getJSONArray("comments");
                   for (int i = 0; i<data.size();i++){
                     JSONObject init =  data.getJSONObject(i);
                       String  content = init.getString("content");
                       int created_at = init.getInteger("created_at");
                       JSONObject user = init.getJSONObject("user");
                       String url = user.getString("avatar_url");
                       String nickname = user.getString("nickname");
                       home_comment = new Home_Comment();
                       home_comment.setContent(content);
                       home_comment.setCreated_at(created_at);
                       home_comment.setAvatar_url(url);
                       home_comment.setNickname(nickname);
                       list.add(home_comment);
                       listView.setAdapter(new Home_Comment_Adapter(Home_Comment_Activity.this, list));
                   }
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(Home_Comment_Activity.this,"网络连接超时",Toast.LENGTH_LONG).show();
                }
            });
        }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
