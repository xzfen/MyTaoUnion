package com.feng.mytaounion.model.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @项目名称：MyTaoUnion
 * @包名：com.feng.mytaounion.model.domain
 * @作者：FENG
 * @类名：Category
 * @创建时间：2022/10/2615:42
 * @描述：
 **/
public class Categories {
    /**
     * success : true
     * code : 10000
     * message : 获取分类成功.
     * data : [{"id":9660,"title":"推荐"},{"id":9649,"title":"食品"}]
     */

    @SerializedName("success")
    private Boolean success;
    @SerializedName("code")
    private Integer code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<DataBean> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
        @SerializedName("id")
        private Integer id;
        @SerializedName("title")
        private String title;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Categories{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
