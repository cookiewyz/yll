package com.xiaoliwu.yll.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;
import com.umeng.update.UmengUpdateAgent;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.broadcast.MyBroadcastReceiver;
import com.xiaoliwu.yll.fragment.ClassifyFagment;
import com.xiaoliwu.yll.fragment.HomeFagment;
import com.xiaoliwu.yll.fragment.HotFagment;
import com.xiaoliwu.yll.fragment.ProfileFagment;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 主界面
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private FrameLayout mHomeContent;
    private RadioGroup mHomeRadioGroup;
    private RadioButton mHomeHomeRb;
    private RadioButton mHomeHotRb;
    private RadioButton mHomeClassifyRb;
    private RadioButton mHomeProfileRb;
    private SharedPreferences sharedPreferences;
    private String sex;
    private boolean isExit = false;//是否退出
    private IntentFilter intentFilter;
    private MyBroadcastReceiver myBroadcastReceiver;
    private int i =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTranslucentStatus();//状态栏背景必须在setContentView之前
        setContentView(R.layout.activity_main);
        isDirExist();
        UmengUpdateAgent.setUpdateOnlyWifi(false);//什么网路状态都可以
        UmengUpdateAgent.update(this);//只在wifi下可以
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.enable();
        PushAgent.getInstance(this).onAppStart();
        String device_token = UmengRegistrar.getRegistrationId(this);
        Log.e("aaaaaaa", "" + device_token);


        if (i==0) {
            getZhuangtai();
        }
        //动态注册广播
        intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, intentFilter);

        sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
        sex = sharedPreferences.getString("sex", null);
        initView();
        //判断根据选择男生还是女生来确定图像
        if (!TextUtils.isEmpty(sex) && sex.equals("男")) {
            Drawable drawable = getResources().getDrawable(R.drawable.bg_checkbox_menu4nan);
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mHomeProfileRb.setCompoundDrawables(null, drawable, null, null);
        }
        if (!TextUtils.isEmpty(sex) && sex.equals("女")) {
            Drawable drawable = getResources().getDrawable(R.drawable.bg_checkbox_menu4nv);
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mHomeProfileRb.setCompoundDrawables(null, drawable, null, null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intentFilter != null) {
            this.unregisterReceiver(myBroadcastReceiver);
        }
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

    /**
     * View初始化
     */
    protected void initView() {
        mHomeContent = (FrameLayout) findViewById(R.id.mHomeContent); //tab上方的区域
        mHomeRadioGroup = (RadioGroup) findViewById(R.id.mHomeRadioGroup);  //底部的四个tab
        mHomeHomeRb = (RadioButton) findViewById(R.id.mHomeHomeRb);
        mHomeHotRb = (RadioButton) findViewById(R.id.mHomeHotRb);
        mHomeClassifyRb = (RadioButton) findViewById(R.id.mHomeClassifyRb);
        mHomeProfileRb = (RadioButton) findViewById(R.id.mHomeProfileRb);
        mHomeHomeRb.setOnClickListener(this);
        mHomeHotRb.setOnClickListener(this);
        mHomeClassifyRb.setOnClickListener(this);
        mHomeProfileRb.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.mHomeContent,new HomeFagment()).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mHomeHomeRb:
                getSupportFragmentManager().beginTransaction().replace(R.id.mHomeContent, new HomeFagment()).commit();
                break;
            case R.id.mHomeHotRb:
                getSupportFragmentManager().beginTransaction().replace(R.id.mHomeContent, new HotFagment()).commit();
                break;
            case R.id.mHomeClassifyRb:
                getSupportFragmentManager().beginTransaction().replace(R.id.mHomeContent, new ClassifyFagment()).commit();
                break;
            case R.id.mHomeProfileRb:
                getSupportFragmentManager().beginTransaction().replace(R.id.mHomeContent, new ProfileFagment()).commit();
                break;
        }
    }


    /**
     * 首次进入界面会判断是否为WiFi,如果是则不提醒,,如果不是(为数据流量)会弹一个对话框提醒用户是否切换到WiFi模式
     */
    private void getZhuangtai() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo.State gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (gprs == NetworkInfo.State.CONNECTED || gprs == NetworkInfo.State.CONNECTING) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("温馨提示");
            builder.setMessage("继续浏览会消耗流量,是否切换为wifi模式");
            builder.setPositiveButton("切换", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("不切换", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.create();
            builder.show();
            i++;
        }
    }

    /**
     * 触摸键盘按钮是会提醒在按一次退出
     */
    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "在按一次退「小礼物」", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
        }
        return false;
    }
    //原来是非有此文件夹
    public void isDirExist(){
        //存储路径
        File file = new File("/sdcard/song/");
        if(!file.exists()){
            file.mkdirs();
            File file1=new File("/sdcard/song/xuanzhuan.png");
            if (!file1.exists()){
                try {
                    file1.createNewFile();
                    Log.i("test1", "isDirExist ");
                    Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ig_profile_photo_default);
                    FileOutputStream fileOutputStream = new FileOutputStream(file.getPath() + "/xuanzhuan.png");
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.close();
                    System.out.println("saveBmp is here");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
