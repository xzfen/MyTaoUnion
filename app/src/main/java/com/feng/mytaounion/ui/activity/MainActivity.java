package com.feng.mytaounion.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.feng.mytaounion.R;
import com.feng.mytaounion.base.BaseFragment;
import com.feng.mytaounion.databinding.ActivityMainBinding;
import com.feng.mytaounion.ui.fragment.HomeFragment;
import com.feng.mytaounion.ui.fragment.RedPacketFragment;
import com.feng.mytaounion.ui.fragment.SearchFragment;
import com.feng.mytaounion.ui.fragment.SelectedFragment;
import com.feng.mytaounion.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityMainBinding;
    private HomeFragment mHomeFragment;
    private SelectedFragment mSelectedFragment;
    private RedPacketFragment mRedPacketFragment;
    private SearchFragment mSearchFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainBinding.getRoot());
        //initView();
        initFragment();
        initListener();
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mSelectedFragment = new SelectedFragment();
        mRedPacketFragment = new RedPacketFragment();
        mSearchFragment = new SearchFragment();
        mFragmentManager = getSupportFragmentManager();
        switchFragment(mHomeFragment);
    }

    private void initListener() {
        mActivityMainBinding.mainNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                LogUtils.d(this, "Title--> " + item.getTitle() + "Id--> " +item.getItemId());

                switch (item.getItemId()) {
                    case R.id.home :
                        LogUtils.d(this, "切换到首页。。。");
                        switchFragment(mHomeFragment);
                        break;
                    case R.id.selected :
                        LogUtils.i(this, "切换到精选。。。");
                        switchFragment(mSelectedFragment);
                        break;
                    case R.id.red_packet:
                        LogUtils.w(this, "切换到特惠。。。");
                        switchFragment(mRedPacketFragment);
                        break;
                    case R.id.search:
                        LogUtils.e(this, "切换到搜索。。。");
                        switchFragment(mSearchFragment);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void switchFragment(BaseFragment targetFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(mActivityMainBinding.mainPageContainer.getId(), targetFragment);
        transaction.commit();
    }
}