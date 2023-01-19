package com.feng.mytaounion.view;

import com.feng.mytaounion.base.IBaseCallback;
import com.feng.mytaounion.model.domain.HomePagerContent;

import java.util.List;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.view
 * @作者：FENG
 * @类名：ICategoryPagerCallback
 * @创建时间：2022/10/319:29
 * @描述：
 **/
public interface ICategoryPagerCallback extends IBaseCallback {
    /**
     * 数据加载加回来
     *
     * @param contents
     */
    void onContentLoaded(List<HomePagerContent.DataBean> contents);

    int getCategoryId();

    /**
     * 加更多时网络错误
     */
    void onLoaderMoreError();

    /**
     * 没有更多内容
     */
    void onLoaderMoreEmpty();

    /**
     * 加到了更多内容
     *
     * @param contents
     */
    void onLoaderMoreLoaded(List<HomePagerContent.DataBean> contents);

    /**
     * 轮播图内容加载到了
     *
     * @param contents
     */
    void onLooperListLoaded(List<HomePagerContent.DataBean> contents);
}
