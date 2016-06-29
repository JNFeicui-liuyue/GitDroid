package com.feicuiedu.gitdroid.Splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicuiedu.gitdroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by liuyue on 2016/6/28.
 */
public class SplashPagerFragment extends Fragment {

    @Bind(R.id.viewPager) ViewPager mViewPager;//滑动动画
    @Bind(R.id.indicator) CircleIndicator mIndicator;//指示器，采用第三方

    private SplashPagerAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_pager,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        mAdapter = new SplashPagerAdapter(getContext());
        //填充数据
        mViewPager.setAdapter(mAdapter);
        //指示器作用在ViewPager上，跟着一起动
        mIndicator.setViewPager(mViewPager);
    }
}
