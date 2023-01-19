package com.feng.mytaounion.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.feng.mytaounion.R;
import com.feng.mytaounion.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.base
 * @作者：FENG
 * @类名：BaseFragment
 * @创建时间：2022/10/268:58
 * @描述：
 **/
public abstract class BaseFragment extends Fragment {

    private State currentState = State.NONE;
    private View mLoadingView;
    private View mSuccessView;
    private View mErrorView;
    private View mEmptyView;

    public enum State {
        NONE,LOADING,SUCCESS,ERROR,EMPTY
    }

    private Unbinder mBind;
    private FrameLayout mBaseContainer;

    @OnClick(R.id.network_error_tips)
    public void retry(){
        LogUtils.d(this, "on retry...");
        onRetryClick();
    }

    protected void onRetryClick() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int resId = getRootViewResId();
        View rootView = loadRootView(inflater,container);
        mBaseContainer = rootView.findViewById(R.id.base_container);
        loadStatesView(inflater,container);
        mBind = ButterKnife.bind(this, rootView);
        initView(rootView);
        initListener();
        initPresenter();
        loadData();
        return rootView;
    }

    /**
     * 如果子类需要去设置相关的事件，覆盖此方法
     */
    protected void initListener() {};

    protected View loadRootView(LayoutInflater inflater,ViewGroup container) {
        return inflater.inflate(R.layout.base_fragment_layout,container,false);
    }

    /**
     * 加载各种状态的View
     *
     * @param inflater
     * @param container
     */
    private void loadStatesView(LayoutInflater inflater,ViewGroup container) {
        //成功的view
        mSuccessView = loadSuccessView(inflater,container);
        mBaseContainer.addView(mSuccessView);
        //Loading的View
        mLoadingView = loadLoadingView(inflater,container);
        mBaseContainer.addView(mLoadingView);
        //错误页面
        mErrorView = loadErrorView(inflater,container);
        mBaseContainer.addView(mErrorView);
        //内容为空的页面
        mEmptyView = loadEmptyView(inflater,container);
        mBaseContainer.addView(mEmptyView);
        setUpState(State.NONE);
    }

    protected View loadErrorView(LayoutInflater inflater,ViewGroup container) {
        return inflater.inflate(R.layout.fragment_error,container,false);
    }


    protected View loadEmptyView(LayoutInflater inflater,ViewGroup container) {
        return inflater.inflate(R.layout.fragment_empty,container,false);
    }

    protected View loadLoadingView(LayoutInflater inflater,ViewGroup container) {
        return inflater.inflate(R.layout.fragment_loading,container,false);
    }

    protected View loadSuccessView(LayoutInflater inflater,ViewGroup container) {
        int resId = getRootViewResId();
        return inflater.inflate(resId,container,false);
    }

    /**
     * 子类通过这个方法来切换状态页面即可
     *
     * @param state
     */
    public void setUpState(State state) {
        this.currentState = state;
        mSuccessView.setVisibility(currentState == State.SUCCESS ? View.VISIBLE : View.GONE);
        mLoadingView.setVisibility(currentState == State.LOADING ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(currentState == State.ERROR ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(currentState == State.EMPTY ? View.VISIBLE : View.GONE);
    }


    protected void initView(View rootView) {
        //初始化视图数据
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBind != null) {
            mBind.unbind();
        }
        release();
    }

    protected void release() {
        //释放资源
    }

    protected void initPresenter() {
        //创建Presenter
    }

    protected void loadData() {
        //加载数据
    }

    protected abstract int getRootViewResId();
}
