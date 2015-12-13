package com.xiaoliwu.yll.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.ChooseActivity;
import com.xiaoliwu.yll.activity.MainActivity;

/**
 * Created by Administrator on 2015/10/16.
 */
public class EducationFragment extends Fragment implements View.OnClickListener {
    private ImageView iv_back;
    private RelativeLayout rl_1,rl_2,rl_3,rl_4,rl_5;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View  view  = inflater.inflate(R.layout.education,container,false);
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        rl_1 = (RelativeLayout) view.findViewById(R.id.rl_eductionone);
        rl_2 = (RelativeLayout) view.findViewById(R.id.rl_eductiontwo);
        rl_3 = (RelativeLayout) view.findViewById(R.id.rl_eductionthree);
        rl_4 = (RelativeLayout) view.findViewById(R.id.rl_eductionfour);
        rl_5 = (RelativeLayout) view.findViewById(R.id.rl_eductionfive);
        iv_back.setOnClickListener(this);
        rl_1.setOnClickListener(this);
        rl_2.setOnClickListener(this);
        rl_3.setOnClickListener(this);
        rl_4.setOnClickListener(this);
        rl_5.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences;
        switch (v.getId()){
            case R.id.rl_eductionone:
                sharedPreferences = getActivity().getSharedPreferences("xinxi", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("xueli","初中生").apply();
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
                break;
            case R.id.rl_eductiontwo:
                sharedPreferences = getActivity().getSharedPreferences("xinxi", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("xueli","高中生").apply();
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
                break;
            case R.id.rl_eductionthree:
                sharedPreferences = getActivity().getSharedPreferences("xinxi", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("xueli","大学生").apply();
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
                break;
            case R.id.rl_eductionfour:
                sharedPreferences = getActivity().getSharedPreferences("xinxi", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("xueli","职场新人").apply();
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
                break;
            case R.id.rl_eductionfive:
                sharedPreferences = getActivity().getSharedPreferences("xinxi", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("xueli","职场资深人").apply();
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
                break;
            case R.id.iv_back:
                startActivity(new Intent(getActivity(),ChooseActivity.class));
                break;

        }
    }
}
