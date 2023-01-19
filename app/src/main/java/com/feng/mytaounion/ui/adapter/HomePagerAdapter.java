package com.feng.mytaounion.ui.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.feng.mytaounion.model.domain.Categories;
import com.feng.mytaounion.ui.fragment.HomePagerFragment;
import com.feng.mytaounion.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.ui.adapter
 * @作者：FENG
 * @类名：HomePagerAdapter
 * @创建时间：2022/10/2714:00
 * @描述：
 **/
public class HomePagerAdapter extends FragmentPagerAdapter {

    private List<Categories.DataBean> categoryList = new ArrayList<>();

    public HomePagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        LogUtils.d(this,"getItem - > " + position);
        Categories.DataBean dataBean = categoryList.get(position);
        HomePagerFragment homePagerFragment = new HomePagerFragment().newInstance(dataBean);
        return homePagerFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categoryList.get(position).getTitle();
    }

    public void setCategories(Categories categories) {
        categoryList.clear();
        List<Categories.DataBean> data = categories.getData();
        categoryList.addAll(data);
        LogUtils.d(this,"size -- > " + this.categoryList.size());
        notifyDataSetChanged();
    }
}
