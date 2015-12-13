package com.xiaoliwu.yll.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.xiaoliwu.yll.R;

/**
 * Created by Administrator on 2015/10/22.
 */
public class IntroduceFragment extends Fragment {
    private WebView introduce_webView;
    private String detail_html;

    public void setDetail_html(String detail_html) {
        this.detail_html = detail_html;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introduce, null);
        introduce_webView = (WebView) view.findViewById(R.id.introduce_webView);
        introduce_webView.loadData(detail_html, "text/html;charset=UTF-8", null);//在WebView控件上设置内容
        return view;
    }

}
