package com.lptiyu.lp_base.uitls.span;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.lptiyu.lp_base.R;

import androidx.core.content.ContextCompat;


public class MyClickableSpan extends ClickableSpan {

    private Context context;

    public MyClickableSpan(Context context) {
        this.context = context;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(ContextCompat.getColor(context, R.color.theme_color));
        ds.setUnderlineText(false);
        ds.clearShadowLayer();
    }

    @Override
    public void onClick(View widget) {

    }
}