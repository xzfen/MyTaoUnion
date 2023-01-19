package com.feng.mytaounion.model;

import com.feng.mytaounion.model.domain.Categories;
import com.feng.mytaounion.model.domain.HomePagerContent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.model
 * @作者：FENG
 * @类名：Api
 * @创建时间：2022/10/2616:43
 * @描述：
 **/
public interface Api {
    @GET("discovery/categories")
    Call<Categories> getCategories();

    @GET
    Call<HomePagerContent> getHomePageContent(@Url String url);
}
