package com.feng.mytaounion.utils;

import android.widget.Toast;

import com.feng.mytaounion.base.BaseApplication;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.utils
 * @作者：FENG
 * @类名：ToastUtil
 * @创建时间：2022/11/289:56
 * @描述：
 **/
public class ToastUtil {

    private static Toast sToast;

    public static void showToast(String tips) {
        if(sToast == null){
            sToast = Toast.makeText(BaseApplication.getAppContext(), tips, Toast.LENGTH_SHORT);
        }else {
            sToast.setText(tips);
        }
        sToast.show();
    }
}
