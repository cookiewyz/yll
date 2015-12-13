package com.xiaoliwu.yll.fragment.homenavbar;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xiaoliwu.yll.R;

/**
 * Created by Administrator on 2015/10/27.
 */
public class Paixu_picpwindows extends PopupWindow {
    private View view;
    private TextView paxu1,paxu2,paxu3,paxu4;
    public Paixu_picpwindows(Context context, View.OnClickListener onClickListener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.paixu_pubwind, null);
        paxu1 = (TextView) view.findViewById(R.id.tv_search_paixu1);
        paxu2 = (TextView) view.findViewById(R.id.tv_search_paixu2);
        paxu3 = (TextView) view.findViewById(R.id.tv_search_paixu3);
        paxu4 = (TextView) view.findViewById(R.id.tv_search_paixu4);
        paxu1.setOnClickListener(onClickListener);
        paxu2.setOnClickListener(onClickListener);
        paxu3.setOnClickListener(onClickListener);
        paxu4.setOnClickListener(onClickListener);
        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(280);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(380);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
    }
    }

