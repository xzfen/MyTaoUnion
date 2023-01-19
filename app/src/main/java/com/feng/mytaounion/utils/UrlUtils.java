package com.feng.mytaounion.utils;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.utils
 * @作者：FENG
 * @类名：UrlUtils
 * @创建时间：2022/10/3111:01
 * @描述：
 **/
public class UrlUtils {
    public static String createHomePageUrl(int materialId,int page) {
        return "discovery/" + materialId + "/" + page;
    }

    public static String getCoverPath(String pict_url,int size) {
        return "https:" + pict_url + "_" + size + "x" + size + ".jpg";
    }

    public static String getCoverPath(String pict_url) {
        if(pict_url.startsWith("http") || pict_url.startsWith("https")) {
            return pict_url;
        } else {
            return "https:" + pict_url;
        }
    }

    public static String getTicketUrl(String url) {
        if(url.startsWith("http") || url.startsWith("https")) {
            return url;
        } else {
            return "https:" + url;
        }
    }

    public static String getSelectedPageContentUrl(int categoryId) {
        return "recommend/" + categoryId;
    }

    public static String getOnSellPageUrl(int currentPage) {
        return "onSell/" + currentPage;
    }
}
