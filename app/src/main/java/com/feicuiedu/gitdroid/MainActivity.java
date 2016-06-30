package com.feicuiedu.gitdroid;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.feicuiedu.gitdroid.commons.ActivityUtils;

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

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //你想改变这个ActionBar的效果
//        getActionBar().setBackgroundDrawable(R.drawable.bubble1);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setTitle("GitDroid");

        //设置navigationView的监听器
        mNavigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,mDrawerLayout,mToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close
        );
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //默认第一个menu项为选中（最热门）
        mMenuItem = mNavigationView.getMenu().findItem(R.id.github_hot_repo);
        mMenuItem.setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //将默认选中项“手动”设置为false
        if (mMenuItem.isChecked()) {
            mMenuItem.setChecked(false);
        }
        switch (item.getItemId()){
            case R.id.github_hot_repo :
                mActivityUtils.showToast(R.id.github_hot_repo);
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
        super.onBackPressed();
        //如navigationView是开着的->关闭
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            //如navigationView是关着的->退出当前Activity
            super.onBackPressed();
        }

    }
}