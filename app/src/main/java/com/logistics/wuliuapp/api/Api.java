package com.logistics.wuliuapp.api;

import com.logistics.wuliuapp.Constant;
import com.logistics.wuliuapp.model.BaseModel;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2016-11-25
 *
 * @desc
 */

public class Api {
    public static Api instance;

    private ApiService service;

    public Api(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static Api getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new Api(okHttpClient);
        return instance;
    }

    /**
     * 测试
     *
     * @return
     */
    public Observable<BaseModel> getTest(String serverTime, int pageIndex,
                                         int pageSize, String propertyNames, String propertyValues) {
        return service.getTest(serverTime, pageIndex, pageSize, propertyNames, propertyValues);
    }
}
