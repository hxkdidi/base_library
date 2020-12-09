package com.lptiyu.lp_base.uitls;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * 工具类
 * <p>
 * Created by jianddongguo on 2017/7/10.
 * http://blog.csdn.net/andrexpert
 */

public class SystemUtils {

    /**
     * 判断本应用是否存活
     * 如果需要判断本应用是否在后台还是前台用getRunningTask
     */
    public static boolean isAPPALive(Context mContext, String packageName) {
        boolean isAPPRunning = false;
        // 获取activity管理对象
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        // 获取所有正在运行的app
        List<ActivityManager.RunningAppProcessInfo> appProcessInfoList = activityManager.getRunningAppProcesses();
        if (CollectionUtils.isEmpty(appProcessInfoList)) {
            return false;
        }
        // 遍历，进程名即包名
        for (ActivityManager.RunningAppProcessInfo appInfo : appProcessInfoList) {
            // 友盟日志  Attempt to invoke interface method 'java.util.Iterator java.util.List.iterator()' on a null object referenceat com.lptiyu.tanke.utils.SystemUtils.isAPPALive(SystemUtils.java:27)
            if (appInfo != null && packageName.equals(appInfo.processName)) {
                isAPPRunning = true;
                break;
            }
        }
        return isAPPRunning;
    }
}
