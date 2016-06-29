package com.feicuiedu.gitdroid.Splash;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**用来存放引导界面的三幅视图
 * Created by liuyue on 2016/6/28.
 */
public class SplashPagerAdapter extends PagerAdapter {

    //定义三张引导视图
    private final View[] mViews;

    //在构造方法中实例化三张视图
    public SplashPagerAdapter(Context context) {
        mViews = new View[]{
                new Pager0(context),
                new Pager1(context),
                new Pager2(context)
        };
    }

    @Override
    public int getCount() {
        return mViews.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //初始化视图
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews[position];
        //加载视图
        container.addView(mViews[position]);
        return view;
    }

    //销毁不可见视图
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       //移除视图
        View view = mViews[position];
        container.removeView(view);
    }

    //方便别的类调用视图
    public View getView(int position){
        return mViews[position];
    }
}
