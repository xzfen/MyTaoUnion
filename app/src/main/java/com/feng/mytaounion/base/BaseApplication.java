package com.feng.mytaounion.base;

import android.app.Application;
import android.content.Context;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.base
 * @作者：FENG
 * @类名：BaseApplication
 * @创建时间：2022/11/2810:00
 * @描述：
 **/
public class BaseApplication extends Application {

    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getBaseContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
