package com.lptiyu.lp_base.uitls.span;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.lptiyu.lp_base.R;

import androidx.core.content.ContextCompat;


public class NameClickable extends ClickableSpan implements View.OnClickListener {
    private final ISpanClick mListener;
    private int mPosition;
    private Context context;

    public NameClickable(ISpanClick mListener, int mPosition, Context context) {
        this.mListener = mListener;
        this.mPosition = mPosition;
        this.context = context;
    }

    @Override
    public void onClick(View widget) {
        mListener.onClick(mPosition);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(ContextCompat.getColor(context, R.color.theme_color));
        ds.setUnderlineText(false);
        ds.clearShadowLayer();
    }
}
