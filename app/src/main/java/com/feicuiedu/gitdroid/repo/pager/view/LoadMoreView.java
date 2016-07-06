package com.feicuiedu.gitdroid.repo.pager.view;

/**下拉加载更多视图抽象接口
 * Created by liuyue on 2016/7/2.
 */
public interface LoadMoreView<T>{

    //添加更多数据
    void addMoreData(T data);
    //隐藏加载更多的视图
    void hideLoadMore();
    //加载更多---加载中
    void showLoadMoreLoading();
    //加载更多---加载发生错误
    void showLoadMoreErro(String msg);
    //没有更多数据
    void showLoadMoreEnd();



}
