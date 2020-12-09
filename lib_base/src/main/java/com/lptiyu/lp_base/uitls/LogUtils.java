package com.lptiyu.lp_base.uitls;

import android.util.Log;

import com.lptiyu.lp_base.BuildConfig;


/**
 * Created by Jason on 2016/9/28.
 */

public class LogUtils {

    private static boolean isDebug = BuildConfig.DEBUG;

    private static String TAG = "bdlp";

    public static void i(String text) {
        if (isDebug)
            Log.i(TAG, text);
    }

    public static void i(String tag, String text) {
        if (isDebug)
            Log.i(tag, text);
    }

    public static void d(String text) {
        if (isDebug)
            Log.d(TAG, text);
    }

    public static void d(String tag, String text) {
        if (isDebug)
            Log.d(tag, text);
    }

    public static void e(String errorMessage) {
        if (isDebug)
            Log.e(TAG, errorMessage);
    }

    public static void e(String tag, String errorMessage) {
        if (isDebug)
            Log.e(tag, errorMessage);
    }
}
