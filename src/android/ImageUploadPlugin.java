package com.huasco.plugins;

import android.net.Uri;

import com.alibaba.fastjson.JSON;
import com.huasco.http.CommonAPI;
import com.huasco.http.ImageUploadResp;
import com.huasco.http.UploadImageParam;
import com.huasco.utils.FileUtils;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传的插件
 */

public class ImageUploadPlugin extends CordovaPlugin {

    private static String ERROR_CODE_LABEL = "errorCode";

    private static String ERROR_MSG_LABEL = "errorMsg";

    private static String SUCCESS_FLAG_MSG = "success";

    CallbackContext callbackContext;

    private final static String ERROR_CODE = "9999";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if ("uploadImage".equals(action)) { // 上传图片
            return uploadImage(args, callbackContext);
        }
        return super.execute(action, args, callbackContext);
    }

    /**
     * 图片上传
     *
     * @param args
     * @param callbackContext
     * @return
     */
    private boolean uploadImage(JSONArray args, CallbackContext callbackContext) throws JSONException {
        //
        JSONObject options = args.optJSONObject(0);
        // 文件名
        String photoKey = trimNull(options.getString("photoKey"), "");
        //  文件路径
        String photoPath = trimNull(options.getString("photoPath"), "");
        // 获取真实的路径
        photoPath = FileUtils.getRealPath(this.cordova.getActivity(),Uri.parse(photoPath));
        // 上传的基础url
        String baseUrl = trimNull(options.getString("baseUrl"),"");
        // 账户ID
        String accountNo = trimNull(options.getString("accountNo"),"");
        // 校验参数
        if (isEmpty(photoKey)) {
            this.callbackContext.error(this.packError("文件名为空"));
        }else if(isEmpty(photoPath)){
            this.callbackContext.error(this.packError("文件路径为空"));
        }else if(!new File(photoPath).exists()){
            this.callbackContext.error(this.packError("对应的文件不存在"));
        }else if (isEmpty(baseUrl)){
            this.callbackContext.error(this.packError("上传的基础url为空"));
        }else if (isEmpty(accountNo)){
            this.callbackContext.error(this.packError("账户ID为空"));
        }else {
            UploadImageParam param = new UploadImageParam();
            param.setBaseUrl(baseUrl);
            param.setAccountNo(accountNo);
            param.setPhotoKey(photoKey);
            param.setPhotoPath(photoPath);
            CommonAPI.shared().uploadImage(param, new CommonAPI.UploadImageCallback() {
                @Override
                public void uploadSucc(ImageUploadResp result) {
                    Map<String, Object> resultMap = new HashMap<String, Object>();
                    ImageUploadPlugin.this.callbackContext.success(JSON.toJSONString(result));
                }

                @Override
                public void uploadFail(String message) {
                    ImageUploadPlugin.this.callbackContext.error(ImageUploadPlugin.this.packError(message));
                }
            });
        }
        return true;
    }

    /**
     *
     * @param str
     * @return
     */
    public boolean isEmpty(String str){
        if(str == null || str.trim().equals(""))
            return true;
        return false;
    }

    public String trimNull(String str,String defaultStr){
        if (isEmpty(str)){
            return defaultStr;
        }else{
            return str.trim();
        }
    }

    /**
     * 组装错误信息
     *
     * @param message
     * @return
     */
    private String packError(String message) {
        Map<String, Object> errorInfo = new HashMap<String, Object>();
        errorInfo.put(ERROR_CODE_LABEL, ERROR_CODE);
        errorInfo.put(SUCCESS_FLAG_MSG, false);
        errorInfo.put(ERROR_MSG_LABEL, message);
        return JSON.toJSONString(errorInfo);
    }

}
