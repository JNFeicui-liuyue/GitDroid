package com.feicuiedu.gitdroid.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.commons.ActivityUtils;
import com.feicuiedu.gitdroid.repo.HotRepoFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // https://github.com/feicui-android/GitDroid.git
    // ButterKnife 库
    // compile 'com.jakewharton:butterknife:7.0.1'

    @Bind(R.id.drawerLayout) DrawerLayout mDrawerLayout;//抽屉（包含内容+侧滑菜单视图）
    @Bind(R.id.navigationView) NavigationView mNavigationView;//侧滑菜单视图
    @Bind(R.id.toolbar) Toolbar mToolbar;
    private ActivityUtils mActivityUtils;
    private MenuItem mMenuItem;

    //创建最热门仓库的Fragment
    private HotRepoFragment mHotRepoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        mActivityUtils = new ActivityUtils(this);

        //ActionBar处理
        setSupportActionBar(mToolbar);
        //设置navigationView的监听器
        mNavigationView.setNavigationItemSelectedListener(this);
        // 设置Toolbar左上角切换侧滑菜单的按钮
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,mDrawerLayout,mToolbar,R.string.navigation_drawer_open
                ,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //默认第一个menu项为选中（最热门）
        mMenuItem = mNavigationView.getMenu().findItem(R.id.github_hot_repo);
        mMenuItem.setChecked(true);

        //默认显示的是HotRepoFragment热门仓库
        mHotRepoFragment = new HotRepoFragment();
        replaceFragment(mHotRepoFragment);

        //包装成方法
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.container,mHotRepoFragment);
//        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //将默认选中项“手动”设置为false
        if (mMenuItem.isChecked()) {
            mMenuItem.setChecked(false);
        }
        switch (item.getItemId()){
            case R.id.github_hot_repo :
                mActivityUtils.showToast("最热门");
                break;
            case R.id.arsenal_my_repo :
                mActivityUtils.showToast("我的收藏");
                break;
            case R.id.tips_daily :
                mActivityUtils.showToast("每日干货");
                break;
//            case R.id.tips_share :
//                //按下分享会关闭左侧侧滑菜单navigationView
//                mDrawerLayout.closeDrawer(GravityCompat.START);
//                break;
        }
        //返回true，代表将该菜单项变为checked状态
        return true;
    }

    @Override
    public void onBackPressed() {

        //如navigationView是开着的->关闭
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            //如navigationView是关着的->退出当前Activity
            super.onBackPressed();
        }
    }

    // 替换不同的内容Fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}