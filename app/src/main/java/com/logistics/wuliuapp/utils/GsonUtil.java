package com.logistics.wuliuapp.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by Mikes on 2016-3-28.
 */
public class GsonUtil {

    private static Gson gson = new Gson();

    public static String toJson(Object obj, Type type) {
        return gson.toJson(obj, type);
    }

    public static Object fromJson(String str,Type type){
        return gson.fromJson(str, type);
    }
}
