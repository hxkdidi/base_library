package com.lptiyu.lp_base.uitls.span;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.BaseMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public  class ClickableMovementMethod extends BaseMovementMethod implements View.OnTouchListener {

    private volatile static ClickableMovementMethod sInstance;

    public  static  ClickableMovementMethod getInstance() {
        if(sInstance == null) {
            synchronized(ClickableMovementMethod.class) {
                if(sInstance == null) {
                    sInstance = new ClickableMovementMethod();
                }
            }
        }
        return sInstance;
    }

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {

        int action = event.getActionMasked();
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {

            int x = (int) event.getX();
            int y = (int) event.getY();
            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();
            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            ClickableSpan[] link = buffer.getSpans(off, off, ClickableSpan.class);
            if (link.length > 0) {
                if (action == MotionEvent.ACTION_UP) {
                    link[0].onClick(widget);
                } else {
                    Selection.setSelection(buffer, buffer.getSpanStart(link[0]),
                            buffer.getSpanEnd(link[0]));
                }
                return true;
            } else {
                Selection.removeSelection(buffer);
            }
        }

        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TextView widget = (TextView) v;
        Object text = widget.getText();
        if (text instanceof Spanned) {
            Spanned buffer = (Spanned) text;

            int action = event.getAction();

            if (action == MotionEvent.ACTION_UP
                    || action == MotionEvent.ACTION_DOWN) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                x -= widget.getTotalPaddingLeft();
                y -= widget.getTotalPaddingTop();

                x += widget.getScrollX();
                y += widget.getScrollY();

                Layout layout = widget.getLayout();
                int line = layout.getLineForVertical(y);
                int off = layout.getOffsetForHorizontal(line, x);

                ClickableSpan[] link = buffer.getSpans(off, off,
                        ClickableSpan.class);

                if (link.length != 0) {
                    if (action == MotionEvent.ACTION_UP) {
                        link[0].onClick(widget);
                    } else if (action == MotionEvent.ACTION_DOWN) {
                        // Selection only works on Spannable text. In our case setSelection doesn't work on spanned text
                        Selection.setSelection((Spannable) buffer, buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]));
                    }
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public void initialize(TextView widget, Spannable text) {
        Selection.removeSelection(text);
    }
}
