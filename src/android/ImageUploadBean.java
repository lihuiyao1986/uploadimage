package com.huasco.http;

import java.io.Serializable;

/**
 * 文件上传信息bean
 */
public class ImageUploadBean implements Serializable {

    private static final long serialVersionUID = 79127621337085216L;

    private String photoKey;

    private String photoUrl;

    public String getPhotoKey() {
        return photoKey;
    }

    public void setPhotoKey(String photoKey) {
        this.photoKey = photoKey;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
