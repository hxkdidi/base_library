package com.lptiyu.lp_base.uitls.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.lptiyu.lp_base.R;
import com.lptiyu.lp_base.uitls.InflaterUtils;


/**
 * @author xiaoxiaoda
 *         date: 16-1-20
 *         email: daque@hustunique.com
 */
public abstract class BaseDialog extends Dialog {

    private NiftyDialogBuilder mBuilder;

    public Context mContext;

    public BaseDialog(Context context) {
        super(context);
        mContext = context;
        mBuilder = NiftyDialogBuilder.getInstance(context);
        mBuilder.withTitleColor(getColor(R.color.black333))                                  //def
                .withDividerColor(getColor(R.color.divider_color_f3f3f3))                              //def
                .withDialogColor(getColor(R.color.white))                               //def  |
                .withDuration(300)
                .isCancelableOnTouchOutside(false)
                .withEffect(Effectstype.SlideBottom);
    }

    public BaseDialog withDividerColor(String colorString) {
        mBuilder.withDividerColor(colorString);
        return this;
    }

    public BaseDialog withButton1Text(String text) {
        mBuilder.withButton1Text(text);
        return this;
    }

    public BaseDialog setButton1Click(View.OnClickListener listener) {
        mBuilder.setButton1Click(listener);
        return this;
    }

    public BaseDialog withButton2Text(String text) {
        mBuilder.withButton2Text(text);
        return this;
    }

    public BaseDialog withDividerColor(int color) {
        mBuilder.withDividerColor(color);
        return this;
    }

    public BaseDialog withTitle(CharSequence title) {
        mBuilder.withTitle(title);
        return this;
    }

    public BaseDialog withTitleColor(String colorString) {
        mBuilder.withTitleColor(Color.parseColor(colorString));
        return this;
    }

    public BaseDialog withTitleColor(int color) {
        mBuilder.withTitleColor(color);
        return this;
    }

    public BaseDialog withMessage(int textResId) {
        mBuilder.withMessage(textResId);
        return this;
    }

    public BaseDialog withMessage(CharSequence msg) {
        mBuilder.withMessage(msg);
        return this;
    }

    public BaseDialog withMessageColor(String colorString) {
        mBuilder.withMessageColor(colorString);
        return this;
    }

    public BaseDialog withMessageColor(int color) {
        mBuilder.withMessageColor(color);
        return this;
    }

    public BaseDialog withDialogColor(String colorString) {
        mBuilder.withDialogColor(colorString);
        return this;
    }

    public BaseDialog withDialogColor(int color) {
        mBuilder.withDialogColor(color);
        return this;
    }

    public BaseDialog withIcon(int drawableResId) {
        mBuilder.withIcon(drawableResId);
        return this;
    }

    public BaseDialog withIcon(Drawable icon) {
        mBuilder.withIcon(icon);
        return this;
    }

    public BaseDialog withDuration(int duration) {
        mBuilder.withDuration(duration);
        return this;
    }

    public BaseDialog withEffect(Effectstype type) {
        mBuilder.withEffect(type);
        return this;
    }

    public BaseDialog isCancelableOnTouchOutside(boolean cancelable) {
        mBuilder.isCancelableOnTouchOutside(cancelable);
        return this;
    }

    public BaseDialog isCancelable(boolean cancelable) {
        mBuilder.isCancelable(cancelable);
        return this;
    }

    public BaseDialog setCustomView(int resId, Context context) {
        View v = InflaterUtils.inflate(resId, null, false);
        return setCustomView(v, context);
    }

    public BaseDialog setCustomView(View view, Context context) {
        mBuilder.setCustomView(view, context);
        return this;
    }

    protected int getColor(int resId) {
        return getContext().getResources().getColor(resId);
    }

    @Override
    public void show() {
        mBuilder.show();
    }

    public void onDestory() {
        mBuilder = null;
    }

    @Override
    public void dismiss() {
        mBuilder.dismiss();
    }

}
