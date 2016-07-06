package com.feicuiedu.gitdroid.repo.pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.components.FooterView;
import com.feicuiedu.gitdroid.repo.pager.view.PtrPageView;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 只有视图操作
 */
//必须指定泛型，不然默认继承父类的MvpView
public class RepoListFragment extends MvpFragment<PtrPageView,ReopListPresenter> implements PtrPageView {

    private static final String KEY_LANGUAGE = "key_language";
    public static RepoListFragment getInstance(String language){
        RepoListFragment fragment = new RepoListFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_LANGUAGE,language);
        fragment.setArguments(args);
        return fragment;
    }
    @Bind(R.id.ptrClassicFrameLayout)PtrClassicFrameLayout mPtrClassicFrameLayout;
    @Bind(R.id.lvRepos)ListView listView;
    @Bind(R.id.emptyView)TextView emptyView;
    @Bind(R.id.errorView)TextView errorView;

    private ArrayAdapter<String> adapter ;

    private FooterView mFooterView;//上拉加载更多的自定义视图

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repo_list,container,false);
    }

    @Override
    public ReopListPresenter createPresenter() {
        return new ReopListPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        //设置Ptr,重写开始刷新的方法，下拉刷新
        mPtrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //加载数据方法，传入的是0-10的随机数，不包括10
                getPresenter().loadData();
            }
        });
        autoReflash();

        mFooterView = new FooterView(getContext());

        //上拉加载更多（listView滑动到最后的位置了，就可以loadMore）
        Mugen.with(listView, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                Toast.makeText(getContext(), "loadmore", Toast.LENGTH_SHORT).show();
                getPresenter().loadMore();
            }

            //是否正在加载，避免重复加载
            @Override
            public boolean isLoading() {
                return listView.getFooterViewsCount() > 0 && mFooterView.isLoading();
            }

            //是否所有数据都已加载
            @Override
            public boolean hasLoadedAllItems() {
                return listView.getFooterViewsCount() > 0 && mFooterView.isComplete();
            }
        }).start();

    }

    //当显现空视图或错误视图时也能重新加载数据
    @OnClick({R.id.emptyView , R.id.errorView})
    public void autoReflash(){
        mPtrClassicFrameLayout.autoRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

//这是下拉刷新视图的实现---------------------------------------------------------------
    @Override
    public void showContentView() {
        mPtrClassicFrameLayout.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);

    }

    @Override
    public void showErroView(String msg) {
        mPtrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        mPtrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void refreshData(List<String> datas) {

        adapter.clear();
        adapter.addAll(datas);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void stopReflash() {

        mPtrClassicFrameLayout.refreshComplete();//下拉刷新完成
    }

    @Override
    public void showMessage(String msg) {

        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

//这是上拉加载更多视图的实现---------------------------------------------------------------
    @Override
    public void addMoreData(List<String> data) {
        //不需要clear，直接在底部添加
        adapter.addAll(data);
    }

    @Override
    public void hideLoadMore() {

        listView.removeFooterView(mFooterView);
    }

    @Override
    public void showLoadMoreLoading() {
        if (listView.getFooterViewsCount() == 0) {
            listView.addFooterView(mFooterView);
        }
        mFooterView.showLoading();

    }

    @Override
    public void showLoadMoreErro(String msg) {
        if (listView.getFooterViewsCount() == 0) {
            listView.addFooterView(mFooterView);
        }
            mFooterView.showError(msg);

    }

    @Override
    public void showLoadMoreEnd() {
        if (listView.getFooterViewsCount() == 0) {
            listView.addFooterView(mFooterView);
        }
            mFooterView.showComplete();

    }
}
