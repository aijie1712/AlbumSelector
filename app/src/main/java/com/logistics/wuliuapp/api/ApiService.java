package com.logistics.wuliuapp.api;

import com.logistics.wuliuapp.model.BaseModel;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016-11-25
 *
 * @desc
 */

public interface ApiService {
    @POST("ann/front/announcement/list/json")
    Observable<BaseModel> getTest(@Query("serverTime") String serverTime, @Query("pageIndex") int pageIndex,
                                  @Query("pageSize") int pageSize, @Query("propertyNames") String propertyNames, @Query("propertyValues") String propertyValues);
}
