package com.xiaoliwu.yll.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2015/10/24.
 */

    public class Mylistview extends ListView {

        public Mylistview(Context context) {
            super(context);
        }

        public Mylistview(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

    public Mylistview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }
    }

