package com.huasco.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http请求管理器类
 */

public class HttpManager {

    private Retrofit retrofit;

    private static HttpManager instance;

    private HttpManager (){

    }

    private HttpManager(String baseUrl) {
        initConfig(baseUrl);
    }

    private void initConfig(String baseUrl){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 对外唯一实例的接口
     *
     * @return
     */
    public static synchronized HttpManager shared(String baseUrl) {
        if (instance == null){
            instance = new HttpManager(baseUrl);
        }
        return instance;
    }


    /**
     * 获取API
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getAPI(Class<T> clazz){
        return this.retrofit.create(clazz);
    }

}
