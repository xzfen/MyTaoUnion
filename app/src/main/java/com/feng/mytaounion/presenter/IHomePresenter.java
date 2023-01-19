package com.feng.mytaounion.presenter;

import com.feng.mytaounion.base.IBasePresenter;
import com.feng.mytaounion.view.IHomeCallback;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.presenter
 * @作者：FENG
 * @类名：HomePresenter
 * @创建时间：2022/10/2615:30
 * @描述：
 **/
public interface IHomePresenter extends IBasePresenter<IHomeCallback> {
    /**
     * 获取商品分类
     */
    void getCatetories();
}
