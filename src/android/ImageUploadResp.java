package com.huasco.http;

/**
 * 图片上传响应类
 */

public class ImageUploadResp extends RespModel {

    private static final long serialVersionUID = -7299589584761834538L;

    private ImageUploadBean result;

    public ImageUploadBean getResult() {
        return result;
    }

    public void setResult(ImageUploadBean result) {
        this.result = result;
    }
}
