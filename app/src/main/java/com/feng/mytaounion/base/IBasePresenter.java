package com.feng.mytaounion.base;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.base
 * @作者：FENG
 * @类名：IBasePresenter
 * @创建时间：2022/10/319:41
 * @描述：
 **/
public interface IBasePresenter<T> {
    /**
     * 注册UI通知接口
     *
     * @param callback
     */
    void registerViewCallback(T callback);

    /**
     * 取消UI通知的接口
     *
     * @param callback
     */
    void unregisterViewCallback(T callback);
}
