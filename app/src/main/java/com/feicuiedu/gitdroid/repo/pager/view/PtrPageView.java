package com.feicuiedu.gitdroid.repo.pager.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by liuyue on 2016/7/3.
 */
public interface PtrPageView extends MvpView,PtrView<List<String>>,LoadMoreView<List<String>> {
}
