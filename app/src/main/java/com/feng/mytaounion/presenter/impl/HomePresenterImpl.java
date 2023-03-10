package com.feng.mytaounion.presenter.impl;

import com.feng.mytaounion.model.Api;
import com.feng.mytaounion.model.domain.Categories;
import com.feng.mytaounion.presenter.IHomePresenter;
import com.feng.mytaounion.utils.LogUtils;
import com.feng.mytaounion.utils.RetrofitManager;
import com.feng.mytaounion.view.IHomeCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.presenter.impl
 * @作者：FENG
 * @类名：HomePresenterImpl
 * @创建时间：2022/10/2616:05
 * @描述：
 **/
public class HomePresenterImpl implements IHomePresenter {

    private IHomeCallback mCallback = null;

    @Override
    public void getCatetories() {
        if(mCallback != null) {
            mCallback.onLoading();
        }
        //加载分类数据
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<Categories> task = api.getCategories();
        task.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                //数据结果
                int code = response.code();
                LogUtils.d(HomePresenterImpl.this,"result code is -- > " + code);
                if(code == HttpURLConnection.HTTP_OK) {
                    //请求成功
                    Categories categories = response.body();
                    if(mCallback != null) {
                        if(categories == null || categories.getData().size() == 0) {
                            mCallback.onEmpty();
                        } else {
                            LogUtils.d(HomePresenterImpl.this,categories.toString());
                            mCallback.onCategoriesLoaded(categories);
                        }
                    }
                } else {
                    //请求失败
                    LogUtils.i(HomePresenterImpl.this,"请求失败...");
                    if(mCallback != null) {
                        mCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                //加载失败的结果
                LogUtils.e(HomePresenterImpl.this,"请求错误..." + t);
                if(mCallback != null) {
                    mCallback.onError();
                }
            }
        });
    }


    @Override
    public void registerViewCallback(IHomeCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IHomeCallback callback) {
        mCallback = null;
    }
}
