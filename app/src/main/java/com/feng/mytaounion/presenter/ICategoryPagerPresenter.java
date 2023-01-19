package com.feng.mytaounion.presenter;

import com.feng.mytaounion.base.IBasePresenter;
import com.feng.mytaounion.view.ICategoryPagerCallback;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.presenter
 * @作者：FENG
 * @类名：ICategoryPagerPresenter
 * @创建时间：2022/10/319:21
 * @描述：
 **/
public interface ICategoryPagerPresenter extends IBasePresenter<ICategoryPagerCallback> {
    /**
     * 根据分类id去获取内容
     *
     * @param categoryId
     */
    void getContentByCategoryId(int categoryId);

    void loaderMore(int categoryId);

    void reload(int categoryId);
}
