package com.logistics.wuliuapp.module;

import com.logistics.wuliuapp.api.Api;
import com.logistics.wuliuapp.api.HeaderInterceptor;
import com.logistics.wuliuapp.api.LoggingInterceptor;
import com.logistics.wuliuapp.api.ResponseInterceptor;
import com.logistics.wuliuapp.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016-11-25
 *
 * @desc
 */
@Module
public class ApiModule {
    @Provides
    public OkHttpClient provideOkHttpClient() {

        LoggingInterceptor logging = new LoggingInterceptor(new MyLog());
        logging.setLevel(LoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logging)
                .addNetworkInterceptor(new ResponseInterceptor());
        return builder.build();
    }

    @Provides
    protected Api provideBookService(OkHttpClient okHttpClient) {
        return Api.getInstance(okHttpClient);
    }

    public static class MyLog implements LoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            // LogUtils.i("oklog: " + message);
        }
    }
}
