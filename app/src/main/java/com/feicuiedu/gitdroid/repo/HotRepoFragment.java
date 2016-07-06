package com.feicuiedu.gitdroid.repo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicuiedu.gitdroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**最热门仓库Fragment
 * Created by liuyue on 2016/6/30.
 * <p/>最热门仓库Fragment
 *
 * <p/>本Fragment是被添加到MainActivity中。
 *
 * <p/>它上面有一个ViewPager和一个相对应的TabLayout。
 *
 * <p/>ViewPager上，每一个页面都是一个RepoListFragment
 */
public class HotRepoFragment extends Fragment {

    //两个控件
    @Bind(R.id.viewPager) ViewPager mViewPager;
    @Bind(R.id.tabLayout) TabLayout mTabLayout;

    private HotRepoPaperAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //填充最热门布局的layout：TabLayout，ViewPager
        return inflater.inflate(R.layout.fragment_hot_repo,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

//        mAdapter = new HotRepoPaperAdapter(getChildFragmentManager());
//        mViewPager.setAdapter(mAdapter);

        // 注意此处是在Fragment中添加Fragment，属于嵌套Fragment
        mViewPager.setAdapter(new HotRepoPaperAdapter(getChildFragmentManager(), getContext()));
        // 将ViewPager绑定到TabLayout上
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
