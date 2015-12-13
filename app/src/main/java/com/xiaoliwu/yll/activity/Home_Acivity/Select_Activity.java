package com.xiaoliwu.yll.activity.Home_Acivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.Hot_CommodityDetailActivity;
import com.xiaoliwu.yll.adapter.Myhot_Adapter;
import com.xiaoliwu.yll.fragment.homenavbar.Paixu_picpwindows;
import com.xiaoliwu.yll.fragment.homenavbar.Select_picpwindows;
import com.xiaoliwu.yll.fragment.homenavbar.library.FlowTagLayout;
import com.xiaoliwu.yll.fragment.homenavbar.library.OnTagClickListener;
import com.xiaoliwu.yll.json.HotBean;
import com.xiaoliwu.yll.myinterface.ServerUrl;
import com.xiaoliwu.yll.utils.SystemBarTintManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Select_Activity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
    Select_picpwindows select_picpwindows;
    private LinearLayout ll1,ll2,ll3,ll4;
    private GridView hot_gridView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int offset = 0;
    private Myhot_Adapter myhot_adapter = null;
    private boolean isCanLoad;//是否可以加载分页数据
    private boolean isLoading;//是否正在加载分页数据
    private List<HotBean> list;
    private int photoid;//拿到每一个图片的id
    private List list1 = new ArrayList();
    private String ss  = ServerUrl.HOME_TIAOXUAN  + offset;
    private Paixu_picpwindows  paixu_picpwindows;
    private ImageView imageView,imageView1;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_select_);
       init();

    }
    private void init(){
        getSupportActionBar().hide();
        ll1= (LinearLayout) findViewById(R.id.ll_select_one);
        ll2= (LinearLayout) findViewById(R.id.ll_select_two);
        ll3= (LinearLayout) findViewById(R.id.ll_select_three);
        ll4 = (LinearLayout) findViewById(R.id.ll_select_four);
        hot_gridView = (GridView) findViewById(R.id.hot_gridView);
        imageView = (ImageView) findViewById(R.id.iv_select_three);
        imageView1  = (ImageView) findViewById(R.id.iv_select_two);
        textView  = (TextView) findViewById(R.id.tv_xuanzhe);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        dataRequest();
        swipeRefreshLayout.setOnRefreshListener(this);
        imageView.setOnClickListener(this);
        hot_gridView.setOnScrollListener(this);//设置滑动监听,用于分页
        hot_gridView.setOnItemClickListener(this);//设置点击每一条的监听事件
        list = new ArrayList<>();
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        ll4.setOnClickListener(this);
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
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        select_picpwindows = new Select_picpwindows(this,itemsOnClick,onTagClickListener);
        paixu_picpwindows = new Paixu_picpwindows(this, itemsOnClick1);
        switch (v.getId()){
            case R.id.ll_select_one:
                select_picpwindows.showAsDropDown(Select_Activity.this.findViewById(R.id.ll_select_zhuyao), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.ll_select_two:
                select_picpwindows.showAsDropDown(Select_Activity.this.findViewById(R.id.ll_select_zhuyao), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.ll_select_three:
                select_picpwindows.showAsDropDown(Select_Activity.this.findViewById(R.id.ll_select_zhuyao), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.ll_select_four:
                select_picpwindows.showAsDropDown(Select_Activity.this.findViewById(R.id.ll_select_zhuyao), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.iv_select_three:
                paixu_picpwindows.showAsDropDown(Select_Activity.this.findViewById(R.id.iv_select_three), Gravity.BOTTOM, 0, 0);
               Log.e("ddat","点击了");
                break;
            case R.id.iv_select_two:
                this.finish();
                break;
        }
    }
    private View.OnClickListener itemsOnClick = new View.OnClickListener(){

        public void onClick(View v) {
            select_picpwindows.dismiss();
            switch (v.getId()) {
              /*  case R.id.btn_take_photo:
                    break;
                case R.id.btn_pick_photo:
                    break;
                default:
                    break;*/
            }
        }
    };
    private View.OnClickListener itemsOnClick1 = new View.OnClickListener(){

        public void onClick(View v) {
            paixu_picpwindows.dismiss();
            switch (v.getId()) {
                case R.id.tv_search_paixu1:
                    ss = ServerUrl.HOME_TIAOXUAN2+offset;
                    list.clear();
                    dataRequest();
                    break;
                case R.id.tv_search_paixu2:
                    Toast.makeText(Select_Activity.this,"这两个接口服务器还没有搭建好哦",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_search_paixu3:
                    ss = ServerUrl.HOME_REDU+offset;
                    list.clear();
                    dataRequest();
                    break;
                case R.id.tv_search_paixu4:
                    Toast.makeText(Select_Activity.this,"这两个接口服务器还没有搭建好哦",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private OnTagClickListener onTagClickListener = new OnTagClickListener() {
        @Override
        public void onItemClick(FlowTagLayout parent, View view, int position) {
            if (position==1&&position==0){
            if (list!=null) {
                ss = ServerUrl.HOME_TIAOXUAN2+offset;
                list.clear();
                dataRequest();

            }
            }else if (position==2){
             ss   =  ServerUrl.HOEM_NV  + offset;
                list.clear();
                dataRequest();
            }else if (position==3){
                ss = ServerUrl.HOME_GUIMI+offset;
                list.clear();
                dataRequest();
            }else if (position==4){
                ss = ServerUrl.HOME_JIYOU+offset;
                list.clear();
                dataRequest();
            }else if (position==5){
                ss = ServerUrl.HOME_BABAMAMA+offset;
                list.clear();
                dataRequest();
            }
            if (position==0){
                textView.setText("全部");
            }else if (position==1){
                textView.setText("男票");
            }else if (position==2){
                textView.setText("女盆友");
            }else if (position==3){
                textView.setText("闺蜜们");
            }else if (position==4){
                textView.setText("基友");
            }else if (position==5){
                textView.setText("爸爸妈妈");
            }else if (position==6){
                textView.setText("小盆友");
            }else if (position==7){
                textView.setText("同事");
            }
        }
    };
    /**
     * 上拉刷新监听
     *
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && isCanLoad) {//代表滑动停止了并且可加载分页
            isCanLoad = false;
            isLoading = true;
            dataRequest();
        }
    }

    /**
     * 上拉刷新监听
     *
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {//代表最后一条显示出来了,但是至于是否完整显示出来 不确定
            View childView = view.getChildAt(visibleItemCount - 1);//获取屏幕内显示的最后一个 view,这个方法的参数是以当前界面显示的数量为准
            if (childView != null && childView.getBottom() <= view.getHeight() && !isLoading) {//代表最后一个控件完全显示出来
                isCanLoad = true;
            }
        }
    }

    /**
     * 请求数据
     * http://api.liwushuo.com/v2/items?limit=20&gender=2&generation=4&offset=0
     */
    public void dataRequest() {
        HttpUtils httpUtils = new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, ss,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Log.e("sss",ss);
                        JSONArray items = JSON.parseObject(responseInfo.result).getJSONObject("data").getJSONArray("items");
                        HotBean bean = null;
                        for (int i = 0; i < items.size(); i++) {
                            JSONObject data = (items.getJSONObject(i));
                            int favorites_count = data.getInteger("favorites_count");
                            String name = data.getString("name");
                            String image = data.getString("cover_image_url");
                            String price = data.getString("price");
                            photoid = data.getInteger("id");
                            bean = new HotBean();
                            bean.setFavorites_count(favorites_count);
                            bean.setImage(image);
                            bean.setName(name);
                            bean.setPrice(price);
                            bean.setId(photoid);
                            list.add(bean);
                        }
                        if (myhot_adapter == null) {
                            myhot_adapter = new Myhot_Adapter(Select_Activity.this, list);
                            hot_gridView.setAdapter(myhot_adapter);
                        } else {//如果不是第一页 则给 adapter 追加数据然后刷新界面
                            if (list!=null) {
                                myhot_adapter.notifyDataSetChanged();
                            }
                        }
                        isLoading = false;//把正在加载数据改为 false
                        offset += 20;
                    }

                    /**
                     * 请求失败执行
                     * @param error
                     * @param msg
                     */
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        error.printStackTrace();
                            Toast.makeText(Select_Activity.this, "网络错误......", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 刷新再次请求数据
     */
    @Override
    public void onRefresh() {
        dataRequest();
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 设置没一条上面的点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String path = ServerUrl.HOTDETAIL +list.get(position).getId();//每一条详情页的地址
        Bundle bundle = new Bundle();
        bundle.putString("path",path);
        Log.d("HotFagment", path);
        Intent intent = new Intent(Select_Activity.this, Hot_CommodityDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
