package com.feng.mytaounion.view;

import com.feng.mytaounion.base.IBaseCallback;
import com.feng.mytaounion.model.domain.Categories;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.view
 * @作者：FENG
 * @类名：IHomeCallback
 * @创建时间：2022/10/2615:33
 * @描述：
 **/
public interface IHomeCallback extends IBaseCallback {
    void onCategoriesLoaded(Categories categories);
}
