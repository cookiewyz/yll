package com.xiaoliwu.yll.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.Classify_HeadoneActivity;
import com.xiaoliwu.yll.activity.Home_Acivity.Special_Activity;
import com.xiaoliwu.yll.activity.MyAppliction;
import com.xiaoliwu.yll.adapter.StrategyAdapter;
import com.xiaoliwu.yll.json.List_GridViewBean;
import com.xiaoliwu.yll.json.StrategyBean;
import com.xiaoliwu.yll.myinterface.ServerUrl;

import java.util.List;

/**
 * 攻略界面
 * Created by Administrator on 2015/10/24.
 */
public class StrategyFragment extends Fragment implements View.OnClickListener {
    private LinearLayout strategy_tupian;
    private BitmapUtils bitmapUtils;
    private ListView strategy_listView;
    private StrategyBean strategyBean;
    private View view1;
    private TextView strategy_qubuchakan;
    private  int i=-1;
    private   List<StrategyBean.DataEntity.CollectionsEntity> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_strategy, null);
        View view1 = inflater.inflate(R.layout.strategy_head, null);
        strategy_qubuchakan = (TextView) view1.findViewById(R.id.strategy_qubuchakan);
        strategy_listView = (ListView) view.findViewById(R.id.strategy_listView);
        strategy_tupian = (LinearLayout) view1.findViewById(R.id.strategy_tupian);
        bitmapUtils = MyAppliction.getUtils();
        bitmapUtils.configDefaultLoadingImage(R.drawable.miaobian6);
        dataRequest();
        strategy_listView.addHeaderView(view1);
        gridViewdataRequest();
        strategy_qubuchakan.setOnClickListener(this);
        return view;
    }

    /**
     * head数据请求展示
     */
    private void dataRequest() {
        HttpUtils httpUtils = new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, ServerUrl.STRATEGY,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        strategyBean = new Gson().fromJson(responseInfo.result, StrategyBean.class);
                        list = strategyBean.getData().getCollections();
                        for (int i = 0; i < list.size(); i++) {
                            String imageUrl = strategyBean.getData().getCollections().get(i).getBanner_image_url();
                            if (getActivity()!=null){
                                view1 = LayoutInflater.from(getActivity()).inflate(R.layout.strategy_horizontalscrollview, strategy_tupian, false);
                                ImageView image = (ImageView) view1.findViewById(R.id.strategy_image);
                                image.setTag(i);
                                image.setOnClickListener(StrategyFragment.this);
                                bitmapUtils.display(image, imageUrl);
                                strategy_tupian.addView(view1);
                            }
                        }

                    }

                    /**
                     * 请求失败执行
                     * @param error
                     * @param msg
                     */
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        error.printStackTrace();
                        if (getActivity() != null) {
                            Toast.makeText(getActivity(), "网络错误......", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    /**
     * 请求数据
     * http://api.liwushuo.com/v2/channel_groups/all
     */
    public void gridViewdataRequest() {
        HttpUtils httpUtils = new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, ServerUrl.CLASSIFYSTRATEGY,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        List_GridViewBean list_gridViewBean = new Gson().fromJson(responseInfo.result, List_GridViewBean.class);
                        List<List_GridViewBean.DataEntity.ChannelGroupsEntity> list = list_gridViewBean.getData().getChannel_groups();
                        StrategyAdapter strategyAdapter = new StrategyAdapter(StrategyFragment.this, list);
                        strategy_listView.setAdapter(strategyAdapter);
                    }

                    /**
                     * 请求失败执行
                     * @param error
                     * @param msg
                     */
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        error.printStackTrace();
//                        if (getActivity() != null) {
//                            Toast.makeText(getActivity(), "网络错误......", Toast.LENGTH_SHORT).show();
//                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.strategy_qubuchakan) {
            startActivity(new Intent(getActivity(), Special_Activity.class));
            i = -1;
        }
        if (v.getTag()!=null) {
            i = (int) v.getTag();
        }
        Intent intent = new Intent(this.getActivity(), Classify_HeadoneActivity.class);
        switch (i) {
            case 0:
                intent.putExtra("headID", list.get(0).getId());
                startActivity(intent);
                break;
            case 1:
                intent.putExtra("headID", list.get(1).getId());
                startActivity(intent);
                break;
            case 2:
                intent.putExtra("headID", list.get(2).getId());
                startActivity(intent);
                break;
            case 3:
                intent.putExtra("headID", list.get(3).getId());
                startActivity(intent);
                break;
            case 4:
                intent.putExtra("headID", list.get(4).getId());
                startActivity(intent);
                break;
            case 5:
                intent.putExtra("headID", list.get(5).getId());
                startActivity(intent);
                break;
            case 6:
                intent.putExtra("headID", list.get(6).getId());
                startActivity(intent);
                break;
            case 7:
                intent.putExtra("headID", list.get(7).getId());
                startActivity(intent);
                break;
            case 8:
                intent.putExtra("headID", list.get(8).getId());
                startActivity(intent);
                break;
            case 9:
                intent.putExtra("headID", list.get(9).getId());
                startActivity(intent);
                break;

        }
    }

}
