package com.feng.mytaounion.presenter.impl;

import com.feng.mytaounion.model.Api;
import com.feng.mytaounion.model.domain.HomePagerContent;
import com.feng.mytaounion.presenter.ICategoryPagerPresenter;
import com.feng.mytaounion.utils.LogUtils;
import com.feng.mytaounion.utils.RetrofitManager;
import com.feng.mytaounion.utils.UrlUtils;
import com.feng.mytaounion.view.ICategoryPagerCallback;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.presenter.impl
 * @作者：FENG
 * @类名：CategoryPagePresenterImpl
 * @创建时间：2022/10/319:50
 * @描述：
 **/
public class CategoryPagePresenterImpl implements ICategoryPagerPresenter {

    private Map<Integer,Integer> pagesInfo = new HashMap<>();
    public static final int DEFAULT_PAGE = 1;
    private Integer mCurrentPage;

    private CategoryPagePresenterImpl() {}

    private static ICategoryPagerPresenter sInstance = null;

    public static ICategoryPagerPresenter getsInstance() {
        if(sInstance == null) {
            sInstance = new CategoryPagePresenterImpl();
        }
        return sInstance;
    }

    @Override
    public void getContentByCategoryId(int categoryId) {
        for(ICategoryPagerCallback callback : callbacks) {
            if(callback.getCategoryId() == categoryId) {
                callback.onLoading();
            }
        }
        //根据分类ID加载内容
        Integer targetPage = pagesInfo.get(categoryId);
        if(targetPage == null) {
            targetPage = DEFAULT_PAGE;
            pagesInfo.put(categoryId,targetPage);
        }
        String homePageUrl = UrlUtils.createHomePageUrl(categoryId, targetPage);
        LogUtils.d(this, "getContentByCategoryId url--> " + homePageUrl);
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<HomePagerContent> task = api.getHomePageContent(homePageUrl);
        task.enqueue(new Callback<HomePagerContent>() {
            @Override
            public void onResponse(Call<HomePagerContent> call, Response<HomePagerContent> response) {
                int code = response.code();
                if(code == HttpURLConnection.HTTP_OK) {
                    HomePagerContent pageContent = response.body();
                    LogUtils.d(CategoryPagePresenterImpl.this,"getContentByCategoryId page content -- > " + pageContent);
                    //把数据给到UI更新
                    handleHomePageContentResult(pageContent,categoryId);
                } else {
                    handleNetworkError(categoryId);
                }
            }

            @Override
            public void onFailure(Call<HomePagerContent> call, Throwable t) {
                LogUtils.d(CategoryPagePresenterImpl.this,"onFailure -- > " + t.toString());
                handleNetworkError(categoryId);
            }
        });
    }

    private void handleNetworkError(int categoryId) {
        for(ICategoryPagerCallback callback : callbacks) {
            if(callback.getCategoryId() == categoryId) {
                callback.onError();
            }
        }
    }

    private void handleHomePageContentResult(HomePagerContent pageContent,int categoryId) {
        //通知UI层更新数据
        List<HomePagerContent.DataBean> data = pageContent.getData();
        for(ICategoryPagerCallback callback : callbacks) {
            if(callback.getCategoryId() == categoryId) {
                if(pageContent == null || pageContent.getData().size() == 0) {
                    callback.onEmpty();
                } else {
                    List<HomePagerContent.DataBean> looperData = data.subList(data.size() - 5,data.size());
                    callback.onLooperListLoaded(looperData);
                    callback.onContentLoaded(data);
                }
            }
        }
    }

    @Override
    public void loaderMore(int categoryId) {
        //拿到当前页面
        mCurrentPage = pagesInfo.get(categoryId);
        if (mCurrentPage == null) {
            mCurrentPage=DEFAULT_PAGE;
        }
        //页面++
        mCurrentPage++;
        //加载数据
        String homePageUrl = UrlUtils.createHomePageUrl(categoryId, mCurrentPage);
        LogUtils.d(this, "getContentByCategoryId url--> " + homePageUrl);
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<HomePagerContent> task = api.getHomePageContent(homePageUrl);
        //处理结果数据
        task.enqueue(new Callback<HomePagerContent>() {
            @Override
            public void onResponse(Call<HomePagerContent> call, Response<HomePagerContent> response) {
                //结果
                int code = response.code();
                if(code == HttpURLConnection.HTTP_OK) {
                    HomePagerContent result = response.body();
                    handleLoadResult(result, categoryId);
                } else {
                    handleLoadMoreError(categoryId);
                }

            }

            @Override
            public void onFailure(Call<HomePagerContent> call, Throwable t) {
                LogUtils.d(CategoryPagePresenterImpl.this, "onFailure" + t.toString());
                handleLoadMoreError(categoryId);
            }
        });
    }

    private void handleLoadResult(HomePagerContent result, int categoryId) {
        for (ICategoryPagerCallback callback : callbacks) {
            if (callback.getCategoryId() == categoryId) {
                if(result == null || result.getData().size() == 0) {
                    mCurrentPage--;
                    pagesInfo.put(categoryId,mCurrentPage);
                    callback.onLoaderMoreEmpty();
                } else {
                    pagesInfo.put(categoryId,mCurrentPage);
                    callback.onLoaderMoreLoaded(result.getData());
                }
            }
        }
    }

    private void handleLoadMoreError(int categoryId) {
        mCurrentPage--;
        pagesInfo.put(categoryId,mCurrentPage);
        for (ICategoryPagerCallback callback : callbacks) {
            if (callback.getCategoryId() == categoryId) {
                callback.onLoaderMoreError();
            }
        }
    }

    @Override
    public void reload(int categoryId) {

    }

    private List<ICategoryPagerCallback> callbacks = new ArrayList<>();

    @Override
    public void registerViewCallback(ICategoryPagerCallback callback) {
        if(!callbacks.contains(callback)) {
            callbacks.add(callback);
        }
    }

    @Override
    public void unregisterViewCallback(ICategoryPagerCallback callback) {
        callbacks.remove(callback);
    }
}
