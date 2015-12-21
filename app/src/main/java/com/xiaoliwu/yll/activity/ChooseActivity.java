package com.xiaoliwu.yll.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.fragment.EducationFragment;

/**
 * @author wyz
 */

public class ChooseActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView boy,girl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseactivity);
        getSupportActionBar().hide();
        boy = (ImageView) findViewById(R.id.iv_boy);
        girl = (ImageView) findViewById(R.id.iv_girl);
        boy.setOnClickListener(this);
        girl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences;
        switch (v.getId()){
            case R.id.iv_boy:
                 sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
                sharedPreferences.edit().putString("sex","男").apply();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl, new EducationFragment()).commit();
                break;
            case R.id.iv_girl:
                 sharedPreferences = getSharedPreferences("xinxi", MODE_PRIVATE);
                sharedPreferences.edit().putString("sex","女").apply();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl, new EducationFragment()).commit();
                break;
        }
    }
}
