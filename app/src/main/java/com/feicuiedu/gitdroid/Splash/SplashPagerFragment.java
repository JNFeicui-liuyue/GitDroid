package com.feicuiedu.gitdroid.Splash;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.Splash.pager.Pager2;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by liuyue on 2016/6/28.
 */
public class SplashPagerFragment extends Fragment {

    @Bind(R.id.viewPager) ViewPager mViewPager;//滑动动画
    @Bind(R.id.indicator) CircleIndicator mIndicator;//指示器，采用第三方

    //ViewPager页面对应背景色
    @BindColor(R.color.colorGreen) int colorGreen;
    @BindColor(R.color.colorRed) int colorRed;
    @BindColor(R.color.colorYellow) int colorYellow;

    @Bind(R.id.layoutPhone) FrameLayout layoutPhone;//手机Layout
    @Bind(R.id.ivPhoneBlank) ImageView ivPhoneBlank;//手机内容
    @Bind(R.id.ivPhoneFont) ImageView ivPhoneFont;//手机Layout

    @Bind(R.id.content) FrameLayout mFrameLayout;//当前页面布局，用于显示背景色的渐变

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
        mViewPager.setAdapter(mAdapter);//填充数据
        //指示器作用在ViewPager上，跟着一起动
        mIndicator.setViewPager(mViewPager);

        //为ViewPager设置滑动时背景颜色改变的监听
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        //由0滑动到1手机图片显示的监听
        mViewPager.addOnPageChangeListener(phoneViewHandle);
    }

    //此监听主要负责viewpager在scroll过程中,当前布局上layoutPhone布局的平移、缩放、渐变的处理
    private final ViewPager.OnPageChangeListener phoneViewHandle = new ViewPager.OnPageChangeListener() {
        /**
         *
         * @param position 代表当前正在显示的第一个页面的索引，
         *                如果positionOffset不等于０，那么第position+1个页面将被显示。
         * @param positionOffset 代表position页面的偏移，取值范围是[0,1)。这是一个偏移百分比。
         * @param positionOffsetPixels 和第二个意义差不多，只不过单位是像素，偏移的像素值。
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            //当ViewPager在第一个页面和第二个页面之间时，
//            // 执行手机图片的缩放，由不可见变为可见
//            if (position == 0){
//                //在移动过程中，实时scale
//                float scale = 0.3f + positionOffset * 0.7f;
//                layoutPhone.setScaleX(scale);
//                layoutPhone.setScaleY(scale);
//                //在移动过程中，pone实时的变化
//                ivPhoneFont.setAlpha(positionOffset);
//                //在移动过程中，有一个平移的动画
//                int scroll = (int) (positionOffset - 1) * 200;
//                layoutPhone.setTranslationX(scroll);
//                return;
//            }
//
//            /**
//             * 当ViewPager在第二个页面和第三个页面之间时（总是为1）
//             手机要和ViewPager一起平移
//             */
//            if (position == 1){
//                layoutPhone.setTranslationX(-positionOffsetPixels);
//                return;
//
//            }
        }
//
        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    // 此监听器主要负责背景颜色的渐变，和最后一个页面视图动画的显示
    private final ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {

        //ARGB插值器
        final ArgbEvaluator mEvaluator = new ArgbEvaluator();

        /**
         *
         * @param position
         * @param positionOffset:当前scroll的比例
         * @param positionOffsetPixels
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            /**
             * start颜色：做一个就行
             * end颜色：做一个就行
             */
//            int color = mEvaluator.evaluate(0.5，红色，黄色);
            // 第一个页面到第二个页面之间
            if (position == 0){
                // 颜色取绿色到红色的中间值，偏移量positionOffset是[0, 1]
                int color = (int) mEvaluator.evaluate(positionOffset,colorGreen,colorRed);
                mFrameLayout.setBackgroundColor(color);
                return;
            }
            // 第二个页面到第三个页面之间
            if (position == 1){
                int color = (int) mEvaluator.evaluate(positionOffset,colorRed,colorYellow);
                mFrameLayout.setBackgroundColor(color);
                return;
            }

        }

        @Override
        public void onPageSelected(int position) {
            // 显示最后一个页面的视图动画
            if (position == 2){
                Pager2 pager2 = (Pager2) mAdapter.getView(2);
                pager2.showAnimation();
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
