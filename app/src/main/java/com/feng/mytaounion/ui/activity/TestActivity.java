package com.feng.mytaounion.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.feng.mytaounion.R;
import com.feng.mytaounion.databinding.ActivityTestBinding;
import com.feng.mytaounion.utils.LogUtils;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.ui.activity
 * @作者：FENG
 * @类名：TestActivity
 * @创建时间：2022/11/1610:05
 * @描述：
 **/
public class TestActivity extends Activity {
    private ActivityTestBinding mActivityTestBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mActivityTestBinding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_test);

        initListener();

    }

    private void initListener() {
        //RadioGroup navigationBar = mActivityTestBinding.testNavigationBar;
        RadioGroup navigationBar = findViewById(R.id.test_navigation_bar);
        navigationBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.test_home:
                        LogUtils.d(TestActivity.this, "切换到首页");
                        break;
                    case R.id.test_red_packet:
                        LogUtils.d(TestActivity.this, "切换到特惠");
                        break;
                    case R.id.test_selected:
                        LogUtils.d(TestActivity.this, "切换到精选");
                        break;
                    case R.id.test_search:
                        LogUtils.d(TestActivity.this, "切换到搜索");
                        break;
                }
            }
        });
    }
}
