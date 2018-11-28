package com.lh.lhjuzhen.utils;


import android.util.Log;

import com.lh.lhjuzhen.BuildConfig;


/**
 * 打印log日志
 */
public class ELog {
    public static boolean isDebug = BuildConfig.JAVA_LOG_ENABLED;

    public static void i(Object msg) {
        if (isDebug) {
            Log.i("mylog", "" + msg);
        }
    }

    public static void d(Object msg) {
        if (isDebug) {
            Log.d("mylog", "" + msg);
        }
    }

    public static void e(Object msg) {
        if (isDebug) {
            Log.e("mylog", "" + msg);
        }
    }

}