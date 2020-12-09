package com.lptiyu.lp_base.uitls;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lptiyu.lp_base.uitls.base.BaseApplication;


/**
 * author:wamcs
 * date:2016/5/19
 * email:kaili@hustunique.com
 */
public class InflaterUtils {
    public static View inflate(int resId, ViewGroup parent, boolean attach) {
        return getInflater().inflate(resId, parent, attach);
    }

    private static LayoutInflater getInflater() {
        return LayoutInflater.from(BaseApplication.getAppContext());
    }

    public static View inflate(int resId, ViewGroup parent) {
        return getInflater().inflate(resId, parent);
    }
}
