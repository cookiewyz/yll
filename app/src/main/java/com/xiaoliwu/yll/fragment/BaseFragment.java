package com.xiaoliwu.yll.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiaoliwu.yll.R;


public abstract class BaseFragment extends Fragment {

    protected ImageView bg;
    protected ImageButton bt;
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment,container,false);
        bg= (ImageView) view.findViewById(R.id.bg);
        bt= (ImageButton) view.findViewById(R.id.bt);
        init();
        return view;
    }

    protected abstract void init();


}
