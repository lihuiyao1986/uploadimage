package com.huasco.http;

import java.io.Serializable;

/**
 * 图片上传参数
 */

public class UploadImageParam implements Serializable {

    private static final long serialVersionUID = 9140938832789351485L;

    // photoKey
    private String photoKey;

    // 账户ID
    private String accountNo;

    // 图片路径
    private String photoPath;

    // 上传的url
    private String baseUrl;

    public String getPhotoKey() {
        return photoKey;
    }

    public void setPhotoKey(String photoKey) {
        this.photoKey = photoKey;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
