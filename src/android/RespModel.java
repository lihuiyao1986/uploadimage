package com.huasco.http;

import java.io.Serializable;

/**
 *  响应基础类
 */

public class RespModel implements Serializable{

    private static final long serialVersionUID = -2286714991669211959L;

    private String responseCode;

    private String message;

    public boolean isSuccess(){
        return "100000".equals(responseCode);
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
