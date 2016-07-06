package com.feicuiedu.gitdroid.repo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.feicuiedu.gitdroid.repo.pager.RepoListFragment;

import java.util.ArrayList;
import java.util.List;

/**最热门的适配器
 * Created by liuyue on 2016/6/30.
 *  <p/>
 * 此适配器用于HotRepoFragment, 每一页都是一个RepoListFragment.
 * <p/>
 * <p/>
 * 用户浏览过的所有子页面fragment都会保存在内存中，但当它们不可见时，其上的View可能被摧毁。
 * 这可能导致占用大量的内存，因为fragment实例能保存任意量的状态值。
 * <p/>
 * <p/>
 * 在我们的应用内，这是可以接收的，否则应该考虑使用FragmentStatePagerAdapter。
 */
public class HotRepoPaperAdapter extends FragmentPagerAdapter {

    //测试性代码，ViewPager中放的是语言
    private final List<String> languages = new ArrayList<>();

    public HotRepoPaperAdapter(FragmentManager fm, Context context) {
        super(fm);

        // 只是为了测试
        for (int i = 1; i < 10; i++) {
            languages.add("Java" + i);
        }
//        languages.add("JAVA 1");
//        languages.add("JAVA 2");
//        languages.add("JAVA 3");
//        languages.add("JAVA 4");
//        languages.add("JAVA 5");
//        languages.add("JAVA 6");
//        languages.add("JAVA 7");
    }

    @Override
    public Fragment getItem(int position) {
        return RepoListFragment.getInstance(languages.get(position));
    }

    @Override
    public int getCount() {
        return languages.size();
    }

    //获取语言标题
    @Override
    public CharSequence getPageTitle(int position) {
        return languages.get(position);
    }
}
