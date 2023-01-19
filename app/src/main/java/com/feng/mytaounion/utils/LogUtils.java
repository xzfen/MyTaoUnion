package com.feng.mytaounion.utils;

import android.util.Log;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.utils
 * @作者：FENG
 * @类名：LogUtils
 * @创建时间：2022/10/2611:25
 * @描述：
 **/
public class LogUtils {
    //TODO:上线后可以把log级别修改为1，只有错误的才显示，这样其他log信息就都不显示了
    private static int currentLev = 4;
    private static final int DEBUG_LEV = 4;
    private static final int INFO_LEV = 3;
    private static final int WARNING_LEV = 2;
    private static final int ERROR_LEV = 1;


    public static void d(Object object,String log) {
        if(currentLev >= DEBUG_LEV) {
            Log.d(object.getClass().getSimpleName(),log);
        }
    }

    public static void i(Object object,String log) {
        if(currentLev >= INFO_LEV) {
            Log.i(object.getClass().getSimpleName(),log);
        }
    }

    public static void w(Object object,String log) {
        if(currentLev >= WARNING_LEV) {
            Log.w(object.getClass().getSimpleName(),log);
        }
    }

    public static void e(Object object,String log) {
        if(currentLev >= ERROR_LEV) {
            Log.e(object.getClass().getSimpleName(),log);
        }
    }
}
