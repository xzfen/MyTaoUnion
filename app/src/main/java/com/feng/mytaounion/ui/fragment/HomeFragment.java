package com.feng.mytaounion.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.feng.mytaounion.R;
import com.feng.mytaounion.base.BaseFragment;
import com.feng.mytaounion.model.domain.Categories;
import com.feng.mytaounion.presenter.IHomePresenter;
import com.feng.mytaounion.presenter.impl.HomePresenterImpl;
import com.feng.mytaounion.ui.adapter.HomePagerAdapter;
import com.feng.mytaounion.utils.LogUtils;
import com.feng.mytaounion.view.IHomeCallback;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.ui.fragment
 * @作者：FENG
 * @类名：HomeFragment
 * @创建时间：2022/10/2521:06
 * @描述：
 **/
public class HomeFragment extends BaseFragment implements IHomeCallback {

    private IHomePresenter mHomePresenter;
    //private FragmentHomeBinding mFragmentHomeBinding;

    @BindView(R.id.home_indicator)
    public TabLayout mTabLayout;
    @BindView(R.id.home_pager)
    public ViewPager mHomePager;
    public HomePagerAdapter mHomePagerAdapter;

    /*@Override
    protected void initViewBinding() {
        mFragmentHomeBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        mTabLayout = mFragmentHomeBinding.homeIndicator;
        mHomePager = mFragmentHomeBinding.homePager;
    }*/

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_home_fragment_layout,container,false);
    }

    @Override
    protected void initView(View rootView) {
        LogUtils.d(this, "initView...");
        mTabLayout.setupWithViewPager(mHomePager);
        //给ViewPager设置适配器
        mHomePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        mHomePager.setAdapter(mHomePagerAdapter);
    }

    @Override
    protected void initPresenter() {
        LogUtils.d(this, "initPresenter...");
        //创建Presenter
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        LogUtils.d(this, "loadData...");
        setUpState(State.LOADING);
        //加载数据
        mHomePresenter.getCatetories();
    }

    @Override
    public void onCategoriesLoaded(Categories categories) {
        setUpState(State.SUCCESS);
        LogUtils.d(this, "onCategoriesLoaded。。。");
        //加载的数据从这里回来
        if (mHomePagerAdapter!=null) {
            //设置预加载页面的数量
            //mHomePager.setOffscreenPageLimit(categories.getData().size());
            mHomePagerAdapter.setCategories(categories);
        }
    }

    @Override
    protected void release() {
        //取消回调注册
        if (mHomePresenter!=null) {
            mHomePresenter.unregisterViewCallback(this);
        }
    }

    @Override
    public void onError() {
        setUpState(State.ERROR);
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpState(State.EMPTY);
    }

    @Override
    protected void onRetryClick() {
        LogUtils.d(this, "onRetryClick...");
        //网络错误，点击了重试
        if (mHomePresenter != null) {
            mHomePresenter.getCatetories();
        }
    }
}
