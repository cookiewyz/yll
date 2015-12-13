package com.xiaoliwu.yll.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
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
import com.xiaoliwu.yll.adapter.CommentAdapter;
import com.xiaoliwu.yll.json.CommentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/22.
 */
public class CommentFragment extends Fragment implements AbsListView.OnScrollListener {
    private ListView comment_listView;
    String path;
    private int offset = 0;
    private boolean isCanLoad;//是否可以加载分页数据
    private boolean isLoading;//是否正在加载分页数据
    private List<CommentBean> list;
    private CommentAdapter commentAdapter;

    public void setPath(String path) {
        this.path = path;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, null);
        comment_listView = (ListView) view.findViewById(R.id.comment_listView);
        comment_listView.setFocusable(false);//使ListView失去焦点,要不然它会给ScrollView的焦点抢去,使他内容从中间显示
        list = new ArrayList<>();
        comment_listView.setOnScrollListener(this);
        dataRequest();
        return view;
    }
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
     * http://api.liwushuo.com/v2/items/1001040/comments?limit=20&offset=0
     */
    public void dataRequest() {
        HttpUtils httpUtils = new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, path+"/comments?limit=20&offset="+offset,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        JSONArray comments = JSON.parseObject(responseInfo.result).getJSONObject("data").getJSONArray("comments");
                        CommentBean bean = null;
                        for (int i = 0; i < comments.size(); i++) {
                            JSONObject data = comments.getJSONObject(i);
                            String content = data.getString("content");
                            long created_at = data.getLong("created_at");
                            String avatar_url = data.getJSONObject("user").getString("avatar_url");
                            String nickname = data.getJSONObject("user").getString("nickname");
                            bean = new CommentBean();
                            bean.setContent(content);
                            bean.setCreated_at(created_at);
                            bean.setAvatar_url(avatar_url);
                            bean.setNickname(nickname);
                            list.add(bean);
                            list.size();
                        }
                        if (commentAdapter == null) {
                            commentAdapter = new CommentAdapter(CommentFragment.this, list);
                            comment_listView.setAdapter(commentAdapter);
                        } else {//如果不是第一页 则给 adapter 追加数据然后刷新界面
                            commentAdapter.notifyDataSetChanged();
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
                        if (getActivity() != null) {
                            Toast.makeText(getActivity(), "网络错误......", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
