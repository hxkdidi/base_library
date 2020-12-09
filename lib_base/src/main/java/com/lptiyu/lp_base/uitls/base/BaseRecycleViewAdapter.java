package com.lptiyu.lp_base.uitls.base;
/**
 * Copyright 2017 WuHan, LPTY, Inc. All rights reserved.
 * <p/>
 * History:
 * ------------------------------------------------------------------------------
 * Date    	|  Who  		|  What
 * 2016/11/12	| map 	| 	create the file
 */


import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 类简要描述
 * <p/>
 * <p/>
 * 类详细描述
 *
 * @author
 */


public abstract class BaseRecycleViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected RecycleViewItemListener itemListener;
    protected List<T> datas = new ArrayList<T>();

    public List<T> getDatas() {
        if (datas == null)
            datas = new ArrayList<T>();
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setItemListener(RecycleViewItemListener listener) {
        this.itemListener = listener;
    }

}