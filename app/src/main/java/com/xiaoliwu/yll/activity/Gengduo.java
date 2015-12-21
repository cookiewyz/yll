package com.xiaoliwu.yll.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.update.UmengUpdateAgent;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.utils.DataCleanManager;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * @author wyz
 */
public class Gengduo extends AppCompatActivity {
    //
    private TextView tx2, textView2, textView4;
    SharedPreferences sharedPreferences;
    private String sex, job;
    boolean aBoolean = true;
    private ImageView image10, image12, tv1;
    private RelativeLayout ll1, ll3, ll4, ll5, ll6, ll7, ll8, ll9, ll10;

    @Override
    protected void onRestart() {
        super.onRestart();
        sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
        sex = sharedPreferences.getString("sex", null);
        job = sharedPreferences.getString("xueli", null);
        if (!TextUtils.isEmpty(sex) && sex.equals("男")) {
            textView2.setText("男");
        }
        if (!TextUtils.isEmpty(sex) && sex.equals("女")) {
            textView2.setText("女");
        }
        if (!TextUtils.isEmpty(job) && job.equals("初中生")) {
            textView4.setText("初中生");
        }
        if (!TextUtils.isEmpty(job) && job.equals("高中生")) {
            textView4.setText("高中生");
        }
        if (!TextUtils.isEmpty(job) && job.equals("大学生")) {
            textView4.setText("大学生");
        }
        if (!TextUtils.isEmpty(job) && job.equals("职场新人")) {
            textView4.setText("职场新人");
        }
        if (!TextUtils.isEmpty(job) && job.equals("职场资深人")) {
            textView4.setText("职场资深人");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_gengduo);
        tx2 = (TextView) findViewById(R.id.tx2);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView4 = (TextView) findViewById(R.id.textView4);
        sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
        sex = sharedPreferences.getString("sex", null);
        job = sharedPreferences.getString("xueli", null);
        if (!TextUtils.isEmpty(sex) && sex.equals("男")) {
            textView2.setText("男");
        }
        if (!TextUtils.isEmpty(sex) && sex.equals("女")) {
            textView2.setText("女");
        }
        if (!TextUtils.isEmpty(job) && job.equals("初中生")) {
            textView4.setText("初中生");
        }
        if (!TextUtils.isEmpty(job) && job.equals("高中生")) {
            textView4.setText("高中生");
        }
        if (!TextUtils.isEmpty(job) && job.equals("大学生")) {
            textView4.setText("大学生");
        }
        if (!TextUtils.isEmpty(job) && job.equals("职场新人")) {
            textView4.setText("职场新人");
        }
        if (!TextUtils.isEmpty(job) && job.equals("职场资深人")) {
            textView4.setText("职场资深人");
        }

        image10 = (ImageView) findViewById(R.id.image10);
        image12 = (ImageView) findViewById(R.id.image12);
        tv1 = (ImageView) findViewById(R.id.tv1);
        ll1 = (RelativeLayout) findViewById(R.id.ll1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        ll3 = (RelativeLayout) findViewById(R.id.ll3);
        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gengduo.this, FankuiActivity.class);
                startActivity(intent);

            }
        });
        ll4 = (RelativeLayout) findViewById(R.id.ll4);
        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gengduo.this, TouGaoActivity.class);
                startActivity(intent);

            }
        });
        ll5 = (RelativeLayout) findViewById(R.id.ll5);
        ll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Gengduo.this, ShenFenActivity.class);
                startActivity(intent);

            }
        });
        ll6 = (RelativeLayout) findViewById(R.id.ll6);
        ll6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aBoolean) {

                    image10.setBackgroundResource(R.mipmap.pic_mode);
                    aBoolean = false;
                    Toast.makeText(Gengduo.this, "开启推送", Toast.LENGTH_SHORT).show();
                } else {
                    image10.setBackgroundResource(R.mipmap.list_mode);
                    Toast.makeText(Gengduo.this, "关闭推送", Toast.LENGTH_SHORT).show();
                    aBoolean = true;
                }
            }
        });
        ll7 = (RelativeLayout) findViewById(R.id.ll7);
        ll7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aBoolean) {

                    image12.setBackgroundResource(R.mipmap.pic_mode);
                    aBoolean = false;
                    Toast.makeText(Gengduo.this, "深夜模式开启", Toast.LENGTH_SHORT).show();
                } else {
                    image12.setBackgroundResource(R.mipmap.list_mode);
                    Toast.makeText(Gengduo.this, "深夜模式关闭", Toast.LENGTH_SHORT).show();
                    aBoolean = true;
                }
            }
        });
        ll8 = (RelativeLayout) findViewById(R.id.ll8);
        ll8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UmengUpdateAgent.forceUpdate(Gengduo.this);
//                Toast.makeText(Gengduo.this, "当前已经是最新版本,不需要等新", Toast.LENGTH_SHORT).show();
            }
        });
        ll9 = (RelativeLayout) findViewById(R.id.ll9);
        ll9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx2.setText("0B");
                Toast.makeText(Gengduo.this, "已经清理", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(Gengduo.this);
                //清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
                DataCleanManager.cleanInternalCache(Gengduo.this);
//清除本应用所有数据库(/data/data/com.xxx.xxx/databases)
                DataCleanManager.cleanDatabases(Gengduo.this);
//清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
                DataCleanManager.cleanSharedPreference(Gengduo.this);
            }
        });
        ll10 = (RelativeLayout) findViewById(R.id.ll10);
        ll10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gengduo.this, GuanYuActivity.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().hide();
//
//
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

    public void showShare() {
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
//            getSupportFragmentManager().beginTransaction().replace(R.id.mHomeContent,new ProfileFagment()).commit();
//            Intent intent=new Intent(Gengduo.this,MainActivity.class);
//            startActivity(intent);
//            if (!TextUtils.isEmpty(sex) && sex.equals("男")) {
//                Drawable drawable = getResources().getDrawable(R.drawable.bg_checkbox_menu4nan);
//                // 这一步必须要做,否则不会显示.
//                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                mHomeProfileRb.setCompoundDrawables(null, drawable, null, null);
//            }
//            if (!TextUtils.isEmpty(sex) && sex.equals("女")) {
//                Drawable drawable = getResources().getDrawable(R.drawable.bg_checkbox_menu4nv);
//                // 这一步必须要做,否则不会显示.
//                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                mHomeProfileRb.setCompoundDrawables(null, drawable, null, null);
//            }

//            // 创建退出对话框
//            AlertDialog isExit = new AlertDialog.Builder(this).create();
//            // 设置对话框标题
//            isExit.setTitle("系统提示");
//            // 设置对话框消息
//            isExit.setMessage("确定要退出吗");
//            // 添加选择按钮并注册监听
//            isExit.setButton("确定", listener);
//            isExit.setButton2("取消", listener);
//            // 显示对话框
//            isExit.show();

        }

        return false;

    }
}
