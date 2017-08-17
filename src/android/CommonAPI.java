package com.huasco.http;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jk on 2017/8/14.
 */

public class CommonAPI {

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder {
        private static final CommonAPI INSTANCE = new CommonAPI();
    }

    /**
     * 对外唯一实例的接口
     *
     * @return
     */
    public static CommonAPI shared() {
        return SingleInstanceHolder.INSTANCE;
    }

    /**
     * 上传文件
     *
     * @param param
     */
    public void uploadImage(UploadImageParam param, final UploadImageCallback callback) {
        UploadService uploadService = HttpManager.shared(param.getBaseUrl()).getAPI(UploadService.class);
        File file = new File(param.getPhotoPath());
        List<MultipartBody.Part> parts = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("photoKey", trimNull(param.getPhotoKey(), ""))
                .addFormDataPart("accountNo", trimNull(param.getAccountNo(), ""))
                .addFormDataPart("photoFile", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .build().parts();
        final Call<ImageUploadResp> resp = uploadService.updateImage(parts);
        resp.enqueue(new Callback<ImageUploadResp>() {
            @Override
            public void onResponse(Call<ImageUploadResp> call, Response<ImageUploadResp> response) {
                ImageUploadResp resp = response.body();
                if (callback != null) {
                    if (resp.isSuccess()) {
                        callback.uploadSucc(resp);
                    } else {
                        callback.uploadFail(resp.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ImageUploadResp> call, Throwable t) {
                if (callback != null) {
                    callback.uploadFail("图片上传失败!");
                }
            }
        });
    }


    /**
     * @param str
     * @return
     */
    public boolean isEmpty(String str) {
        if (str == null || str.trim().equals(""))
            return true;
        return false;
    }

    public String trimNull(String str, String defaultStr) {
        if (isEmpty(str)) {
            return defaultStr;
        } else {
            return str.trim();
        }
    }

    public interface UploadImageCallback {
        /**
         * 上传成功
         *
         * @param result
         */
        void uploadSucc(ImageUploadResp result);

        /**
         * 上传失败
         *
         * @param message
         */
        void uploadFail(String message);
    }
}
