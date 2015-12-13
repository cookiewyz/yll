package com.xiaoliwu.yll.fragment.homenavbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.Home_Acivity.Super_Details_Activity;
import com.xiaoliwu.yll.activity.MyAppliction;
import com.xiaoliwu.yll.myinterface.ServerUrl;

/**
 * Created by Administrator on 2015/10/19.
 */
public class DigitalFragment extends Home_Super {
    @Override
    protected void init() {
       pathone = ServerUrl.HOME_EL;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int aa= list.get((int) id).getId();
        Log.e("aa", aa + "");
        Intent  intent = new Intent(getActivity(),Super_Details_Activity.class);
        intent.putExtra("init", aa);
        startActivity(intent);
    }

}
