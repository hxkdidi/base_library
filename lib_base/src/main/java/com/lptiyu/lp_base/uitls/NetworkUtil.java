package com.lptiyu.lp_base.uitls;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * EMAIL : danxionglei@foxmail.com
 * DATE : 16/5/18
 *
 * @author ldx
 */
public class NetworkUtil {

    public static String getHostAddress(String host) {
        try {
            InetAddress x = InetAddress.getByName(host);
            String ip = x.getHostAddress();//得到字符串形式的ip地址
            LogUtils.d(" IP地址为：" + ip);
            return ip;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            LogUtils.d(" 域名解析出错 ");
        }
        return "";
    }

    /**
     * 判断是否有网
     *
     * @return
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isAvailable() && info.isConnected());
    }


    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            return info != null && info.getType() == ConnectivityManager.TYPE_MOBILE && info.isAvailable()&&info.isConnected();
        }
        return false;
    }

    /**
     * 判断是否有wifi
     *
     * @return
     */
    public static boolean isWlanAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.isConnected()
                && info.getType() == ConnectivityManager.TYPE_WIFI;
    }
}
