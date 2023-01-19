package com.feng.mytaounion.model.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.model.domain
 * @作者：FENG
 * @类名：HomePagerContent
 * @创建时间：2022/10/319:30
 * @描述：
 **/
public class HomePagerContent {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("code")
    private long code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<DataBean> data;

    @Override
    public String toString() {
        return "HomePagerContent{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("category_id")
        private long categoryId;
        @SerializedName("category_name")
        private Object categoryName;
        @SerializedName("click_url")
        private String clickUrl;
        @SerializedName("commission_rate")
        private String commissionRate;
        @SerializedName("coupon_amount")
        private long couponAmount;
        @SerializedName("coupon_click_url")
        private String couponClickUrl;
        @SerializedName("coupon_end_time")
        private String couponEndTime;
        @SerializedName("coupon_info")
        private Object couponInfo;
        @SerializedName("coupon_remain_count")
        private long couponRemainCount;
        @SerializedName("coupon_share_url")
        private String couponShareUrl;
        @SerializedName("coupon_start_fee")
        private String couponStartFee;
        @SerializedName("coupon_start_time")
        private String couponStartTime;
        @SerializedName("coupon_total_count")
        private long couponTotalCount;
        @SerializedName("item_description")
        private String itemDescription;
        @SerializedName("item_id")
        private Long itemId;
        @SerializedName("level_one_category_id")
        private long levelOneCategoryId;
        @SerializedName("level_one_category_name")
        private String levelOneCategoryName;
        @SerializedName("nick")
        private String nick;
        @SerializedName("pict_url")
        private String pictUrl;
        @SerializedName("seller_id")
        private long sellerId;
        @SerializedName("shop_title")
        private String shopTitle;
        @SerializedName("small_images")
        private SmallImagesBean smallImages;
        @SerializedName("title")
        private String title;
        @SerializedName("user_type")
        private long userType;
        @SerializedName("volume")
        private long volume;
        @SerializedName("zk_final_price")
        private String zkFinalPrice;

        @Override
        public String toString() {
            return "DataBean{" +
                    "categoryId=" + categoryId +
                    ", categoryName=" + categoryName +
                    ", clickUrl='" + clickUrl + '\'' +
                    ", title='" + title + '\'' +
                    ", userType=" + userType +
                    ", volume=" + volume +
                    ", zkFinalPrice='" + zkFinalPrice + '\'' +
                    '}';
        }

        public long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(long categoryId) {
            this.categoryId = categoryId;
        }

        public Object getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(Object categoryName) {
            this.categoryName = categoryName;
        }

        public String getClickUrl() {
            return clickUrl;
        }

        public void setClickUrl(String clickUrl) {
            this.clickUrl = clickUrl;
        }

        public String getCommissionRate() {
            return commissionRate;
        }

        public void setCommissionRate(String commissionRate) {
            this.commissionRate = commissionRate;
        }

        public long getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(long couponAmount) {
            this.couponAmount = couponAmount;
        }

        public String getCouponClickUrl() {
            return couponClickUrl;
        }

        public void setCouponClickUrl(String couponClickUrl) {
            this.couponClickUrl = couponClickUrl;
        }

        public String getCouponEndTime() {
            return couponEndTime;
        }

        public void setCouponEndTime(String couponEndTime) {
            this.couponEndTime = couponEndTime;
        }

        public Object getCouponInfo() {
            return couponInfo;
        }

        public void setCouponInfo(Object couponInfo) {
            this.couponInfo = couponInfo;
        }

        public long getCouponRemainCount() {
            return couponRemainCount;
        }

        public void setCouponRemainCount(long couponRemainCount) {
            this.couponRemainCount = couponRemainCount;
        }

        public String getCouponShareUrl() {
            return couponShareUrl;
        }

        public void setCouponShareUrl(String couponShareUrl) {
            this.couponShareUrl = couponShareUrl;
        }

        public String getCouponStartFee() {
            return couponStartFee;
        }

        public void setCouponStartFee(String couponStartFee) {
            this.couponStartFee = couponStartFee;
        }

        public String getCouponStartTime() {
            return couponStartTime;
        }

        public void setCouponStartTime(String couponStartTime) {
            this.couponStartTime = couponStartTime;
        }

        public long getCouponTotalCount() {
            return couponTotalCount;
        }

        public void setCouponTotalCount(long couponTotalCount) {
            this.couponTotalCount = couponTotalCount;
        }

        public String getItemDescription() {
            return itemDescription;
        }

        public void setItemDescription(String itemDescription) {
            this.itemDescription = itemDescription;
        }

        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        public long getLevelOneCategoryId() {
            return levelOneCategoryId;
        }

        public void setLevelOneCategoryId(long levelOneCategoryId) {
            this.levelOneCategoryId = levelOneCategoryId;
        }

        public String getLevelOneCategoryName() {
            return levelOneCategoryName;
        }

        public void setLevelOneCategoryName(String levelOneCategoryName) {
            this.levelOneCategoryName = levelOneCategoryName;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getPictUrl() {
            return pictUrl;
        }

        public void setPictUrl(String pictUrl) {
            this.pictUrl = pictUrl;
        }

        public long getSellerId() {
            return sellerId;
        }

        public void setSellerId(long sellerId) {
            this.sellerId = sellerId;
        }

        public String getShopTitle() {
            return shopTitle;
        }

        public void setShopTitle(String shopTitle) {
            this.shopTitle = shopTitle;
        }

        public SmallImagesBean getSmallImages() {
            return smallImages;
        }

        public void setSmallImages(SmallImagesBean smallImages) {
            this.smallImages = smallImages;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getUserType() {
            return userType;
        }

        public void setUserType(long userType) {
            this.userType = userType;
        }

        public long getVolume() {
            return volume;
        }

        public void setVolume(long volume) {
            this.volume = volume;
        }

        public String getZkFinalPrice() {
            return zkFinalPrice;
        }

        public void setZkFinalPrice(String zkFinalPrice) {
            this.zkFinalPrice = zkFinalPrice;
        }

        public static class SmallImagesBean {
            @SerializedName("string")
            private List<String> string;

            public List<String> getString() {
                return string;
            }

            public void setString(List<String> string) {
                this.string = string;
            }
        }
    }
}
