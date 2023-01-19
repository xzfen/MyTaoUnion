package com.feng.mytaounion.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.viewpager.widget.ViewPager;

import com.feng.mytaounion.R;
import com.feng.mytaounion.base.BaseFragment;
import com.feng.mytaounion.model.domain.Categories;
import com.feng.mytaounion.model.domain.HomePagerContent;
import com.feng.mytaounion.presenter.ICategoryPagerPresenter;
import com.feng.mytaounion.presenter.impl.CategoryPagePresenterImpl;
import com.feng.mytaounion.ui.adapter.HomePageContentAdapter;
import com.feng.mytaounion.ui.adapter.LooperPagerAdapter;
import com.feng.mytaounion.utils.Constants;
import com.feng.mytaounion.utils.LogUtils;
import com.feng.mytaounion.utils.ToastUtil;
import com.feng.mytaounion.view.ICategoryPagerCallback;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import java.util.List;

import butterknife.BindView;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.ui.fragment
 * @作者：FENG
 * @类名：HomePageFragment
 * @创建时间：2022/10/2714:18
 * @描述：
 **/
public class HomePagerFragment extends BaseFragment implements ICategoryPagerCallback {

    private ICategoryPagerPresenter mCategoryPagePresenter;
    private int mMaterialId;
    private HomePageContentAdapter mContentAdapter;
    private LooperPagerAdapter mLooperPagerAdapter;

    public static HomePagerFragment newInstance(Categories.DataBean category) {
        HomePagerFragment homePagerFragment = new HomePagerFragment();
        //
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_HOME_PAGER_TITLE,category.getTitle());
        bundle.putInt(Constants.KEY_HOME_PAGER_MATERIAL_ID,category.getId());
        homePagerFragment.setArguments(bundle);
        return homePagerFragment;
    }

    @BindView(R.id.looper_pager)
    public ViewPager mLooperPager;

    @BindView(R.id.home_pager_content_list)
    public RecyclerView mContentList;

    @BindView(R.id.home_page_title)
    public TextView mCurrentCategoryTitle;

    @BindView(R.id.looper_point_container)
    public LinearLayout looperPointContainer;

    @BindView(R.id.home_page_refresh)
    public SmartRefreshLayout mSmartRefreshLayout;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initView(View rootView) {
        //设置布局管理器
        mContentList.setLayoutManager(new LinearLayoutManager(getContext()));
        mContentList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 5;
                outRect.bottom = 5;
            }
        });
        //创建适配器
        mContentAdapter = new HomePageContentAdapter();
        //设置适配器
        mContentList.setAdapter(mContentAdapter);

        //创建轮播图适配器
        mLooperPagerAdapter = new LooperPagerAdapter();
        //设置适配器
        mLooperPager.setAdapter(mLooperPagerAdapter);
        //设置关闭下拉，开启上划属性
        mSmartRefreshLayout.setEnableRefresh(false);
        mSmartRefreshLayout.setEnableLoadMore(true);
    }

    @Override
    protected void initListener() {
        mLooperPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.d(this, "onPageSelected" + position);
                if(mLooperPagerAdapter.getDataSize() == 0) {
                    return;
                }
                int targetPosition = position % mLooperPagerAdapter.getDataSize();
                //切换指示器
                updateLooperIndicator(targetPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置上划监听器
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                LogUtils.d(HomePagerFragment.this, "load more...");
                mCategoryPagePresenter.loaderMore(mMaterialId);
            }
        });
    }

    private void updateLooperIndicator(int targetPosition) {
        for(int i = 0; i < looperPointContainer.getChildCount(); i++) {
            View point = looperPointContainer.getChildAt(i);
            if(i == targetPosition) {
                point.setBackgroundResource(R.drawable.shape_indicator_point_selected);
            } else {
                point.setBackgroundResource(R.drawable.shape_indicator_point_normal);
            }
        }
    }

    @Override
    protected void initPresenter() {
        mCategoryPagePresenter = CategoryPagePresenterImpl.getsInstance();
        mCategoryPagePresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();
        String title = arguments.getString(Constants.KEY_HOME_PAGER_TITLE);
        mMaterialId = arguments.getInt(Constants.KEY_HOME_PAGER_MATERIAL_ID);
        LogUtils.d(this,"title -- > " + title);
        LogUtils.d(this,"materialId -- > " + mMaterialId);
        //加载数据
        if (mCategoryPagePresenter != null) {
            mCategoryPagePresenter.getContentByCategoryId(mMaterialId);
        }
        if(mCurrentCategoryTitle != null) {
            mCurrentCategoryTitle.setText(title);
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
    public void onContentLoaded(List<HomePagerContent.DataBean> contents) {
        //数据列表加载到了
        mContentAdapter.setData(contents);
        setUpState(State.SUCCESS);
    }

    @Override
    public int getCategoryId() {
        return mMaterialId;
    }

    @Override
    public void onLoaderMoreError() {
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishLoadMore(false);
        }
        //Toast.makeText(getContext(), "网络异常，请稍后重试", Toast.LENGTH_SHORT).show();
        ToastUtil.showToast("网络异常，请稍后重试");
    }

    @Override
    public void onLoaderMoreEmpty() {
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
        //Toast.makeText(getContext(), "没有更多的数据", Toast.LENGTH_SHORT).show();
        ToastUtil.showToast("没有更多的数据");
    }

    @Override
    public void onLoaderMoreLoaded(List<HomePagerContent.DataBean> contents) {
        //添加到适配器数据的底部
        mContentAdapter.addData(contents);
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishLoadMore();
        }
        //Toast.makeText(getContext(), "加载了" + contents.size() + "条记录", Toast.LENGTH_SHORT).show();
        ToastUtil.showToast("加载了" + contents.size() + "条记录");
    }

    @Override
    public void onLooperListLoaded(List<HomePagerContent.DataBean> contents) {
        LogUtils.d(this, "looper size--> " + contents.size());
        mLooperPagerAdapter.setData(contents);
        //中间点%数据的size不一定为0，所以显示的就不是第一个。
        //处理一下
        int dx = (Integer.MAX_VALUE / 2) % contents.size();
        int targetCenterPosition = (Integer.MAX_VALUE / 2) - dx;
        //设置到中间点
        mLooperPager.setCurrentItem(targetCenterPosition);
        //LogUtils.d(this," url  -- >" + contents.get(0).getPict_url());
        looperPointContainer.removeAllViews();
       //添加点
        for (int i = 0; i < contents.size(); i++) {
            View point = new View(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20,20);
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            point.setLayoutParams(layoutParams);
            if(i == 0) {
                point.setBackgroundResource(R.drawable.shape_indicator_point_selected);
            } else {
                point.setBackgroundResource(R.drawable.shape_indicator_point_normal);
            }
            looperPointContainer.addView(point);
        }

    }

    @Override
    protected void release() {
        if (mCategoryPagePresenter != null) {
            mCategoryPagePresenter.unregisterViewCallback(this);
        }
    }
}
