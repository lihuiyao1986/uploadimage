package com.huasco.http;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 上传服务
 */

public interface UploadService {

    @Multipart
    @POST("safecheck/front/uploadAppPhoto")
    Call<ImageUploadResp> updateImage(@Part List<MultipartBody.Part> partList);
}
