package com.logistics.wuliuapp.utils;

import android.util.Log;

import com.logistics.wuliuapp.BuildConfig;

/**
 * 日志工具类
 * @author Administrator
 */
public class LogUtils {
	public static int DEBUG_LEVEL = 6;
	public static String TAG = "aijie";

    private static final int VERBOSE = 5;
    private static final int DEBUG = 4;
    private static final int INFO = 3;
    private static final int WARN = 2;
    private static final int ERROR = 1;

    static {
        if (BuildConfig.DEBUG) {
            DEBUG_LEVEL = 6;
        }else{
            DEBUG_LEVEL = 0;
        }
    }

    /**
     * 打印长日志的方法(分次打印)
     * @param tag
     * @return
     */
	public static void logL(String tag, String content) {
		int p = 2000;
		long length = content.length();
		if (length < p || length == p)
			
			Log.i(tag, content);
		else {
			while (content.length() > p) {
				String logContent = content.substring(0, p);
				content = content.replace(logContent, "");
				Log.i(tag, logContent);
			}
			Log.i(tag, content);
		}
	}
	
	public static void logL(String content) {
		int p = 2000;
		long length = content.length();
		if (length < p || length == p)
			Log.i(TAG, content);
		else {
			while (content.length() > p) {
				String logContent = content.substring(0, p);
				content = content.replace(logContent, "");
				Log.i(TAG, logContent);
			}
			Log.i(TAG, content);
		}
	}
    
    // 个人日志工具方法
    public static int v(String msg) {
        if (DEBUG_LEVEL > VERBOSE) {
            return Log.v(TAG, msg);
        } else {
            return 0;
        }
    }

    public static int d(String msg) {
        if (DEBUG_LEVEL > DEBUG) {
            return Log.d(TAG, msg);
        } else {
            return 0;
        }
    }

    public static int i(String msg) {
        if (DEBUG_LEVEL > INFO) {
            return Log.i(TAG, msg);
        } else {
            return 0;
        }
    }

    public static int w(String msg) {
        if (DEBUG_LEVEL > WARN) {
            return Log.w(TAG, msg);
        } else {
            return 0;
        }
    }

    public static int e(String msg) {
        if (DEBUG_LEVEL > ERROR) {
            return Log.e(TAG, msg);
        } else {
            return 0;
        }
    }
    
    // 带标签的日志工具方法
    public static int v(String tag, String msg) {
        if (DEBUG_LEVEL > VERBOSE) {
            return Log.v(tag, msg);
        } else {
            return 0;
        }
    }

    public static int d(String tag, String msg) {
        if (DEBUG_LEVEL > DEBUG) {
            return Log.d(tag, msg);
        } else {
            return 0;
        }
    }

    public static int i(String tag, String msg) {
        if (DEBUG_LEVEL > INFO) {
            return Log.i(tag, msg);
        } else {
            return 0;
        }
    }

    public static int w(String tag, String msg) {
        if (DEBUG_LEVEL > WARN) {
            return Log.w(tag, msg);
        } else {
            return 0;
        }
    }

    public static int e(String tag, String msg) {
        if (DEBUG_LEVEL > ERROR) {
            return Log.e(tag, msg);
        } else {
            return 0;
        }
    }

    public static int d(String tag,String msg,Throwable throwable){
        if (DEBUG_LEVEL > ERROR) {
            return Log.d(tag, msg,throwable);
        } else {
            return 0;
        }
    }

    public static int e(String tag,String msg,Throwable throwable){
        if (DEBUG_LEVEL > ERROR) {
            return Log.e(tag, msg,throwable);
        } else {
            return 0;
        }
    }
}
