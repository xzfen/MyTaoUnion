package com.feng.mytaounion.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.utils
 * @作者：FENG
 * @类名：RetrofitManager
 * @创建时间：2022/10/2710:22
 * @描述：
 **/
public class RetrofitManager {
    private static final RetrofitManager ourInstance = new RetrofitManager();
    private final Retrofit mRetrofit;

    public static RetrofitManager getInstance() {
        return ourInstance;
    }

    private RetrofitManager() {
        //创建retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
