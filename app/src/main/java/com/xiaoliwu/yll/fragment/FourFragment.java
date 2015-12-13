package com.xiaoliwu.yll.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.ChooseActivity;
import com.xiaoliwu.yll.activity.MainActivity;


public class FourFragment extends BaseFragment {


    @Override
    protected void init() {
        bg.setBackgroundResource(R.mipmap.walkthrough_4);
        bt.setVisibility(View.VISIBLE);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pengpeng", Context.MODE_PRIVATE);
                sharedPreferences.edit().putBoolean("isFirst", false).apply();
                Intent intent = new Intent(getActivity(), ChooseActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

}
