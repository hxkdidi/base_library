package com.lptiyu.lp_base.uitls;

import android.content.Context;
import android.util.DisplayMetrics;


/**
 * author:wamcs
 * date:2016/5/20
 * email:kaili@hustunique.com
 */
public class DisplayUtils {

    public static DisplayMetrics display(Context context) {
        if (context == null) {
            return null;
        }
        return context.getResources().getDisplayMetrics();
    }

    public static int width(Context context) {
        DisplayMetrics display = display(context);
        if (display == null) {
            return 0;
        }
        return display.widthPixels;
    }

    public static int height(Context context) {
        DisplayMetrics display = display(context);
        if (display == null) {
            return 0;
        }
        return display.heightPixels;
    }

    public static int dp2px(Context context,float dp) {
        DisplayMetrics display = display(context);
        if (display == null) {
            return 0;
        }
        final float scale = display.density;
        return (int) (dp * scale + 0.5f);
    }

    public static float px2dp(Context context,int px) {
        DisplayMetrics display = display(context);
        if (display == null) {
            return 0;
        }
        return px / display.density;
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context,float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
