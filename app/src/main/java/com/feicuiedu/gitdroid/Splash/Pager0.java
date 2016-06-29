package com.feicuiedu.gitdroid.Splash;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.feicuiedu.gitdroid.R;

/**
 * Created by liuyue on 2016/6/28.
 */
public class Pager0 extends FrameLayout {
    public Pager0(Context context) {
        super(context);
        init();
    }

    public Pager0(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Pager0(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //初始化布局
    private void init() {
        /**
         * true:直接把布局添加进去，避免了实例化View，再去add
         */
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_0,this,true);
    }
}
