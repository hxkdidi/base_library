package com.lptiyu.lp_base.uitls.base;
/**
 * Copyright 2017 WuHan, LPTY, Inc. All rights reserved.
 * <p/>
 * History:
 * ------------------------------------------------------------------------------
 * Date    	|  Who  		|  What
 * 2016/11/12	| map 	| 	create the file
 */

import android.view.View;

/**
 * 类简要描述
 * <p/>
 * <p/>
 * 类详细描述
 *
 * @author
 */


public interface RecycleViewItemListener {

    void onItemClick(View view, int position);

    boolean onItemLongClick(View view, int position);

}
