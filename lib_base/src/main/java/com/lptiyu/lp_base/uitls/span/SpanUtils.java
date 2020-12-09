package com.lptiyu.lp_base.uitls.span;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.lptiyu.lp_base.R;
import com.lptiyu.lp_base.uitls.StringUtils;

import androidx.core.content.ContextCompat;


/**
 * Created by Administrator on 2017/12/20 0020.
 */

public class SpanUtils {
    /**
     * 处理标签
     *
     * @param contentTextView
     * @param content
     */
    public static void setTopicContent(final TextView contentTextView, String topic_name, String content,
                                       final ISpanClick iSpanClick) {
        final SpannableString sp = new SpannableString(topic_name + content);
        final Context context = contentTextView.getContext();
        setSpanContent(context, sp, contentTextView, 0, topic_name.length(), iSpanClick);
    }

    /**
     * 处理标签
     *
     * @param contentTextView
     * @param topic_name
     */
    public static void setTopicContentNew(final TextView contentTextView, String topic_name,
                                          final ISpanClick iSpanClick) {
        if (topic_name.contains("#")) {
            topic_name = topic_name.replace("#", "");
        }
        if (topic_name.length() > 18) {
            topic_name = topic_name.substring(0, 18) + "...";
        }
        final SpannableString sp = new SpannableString(topic_name);
        final Context context = contentTextView.getContext();
        setSpanContent(context, sp, contentTextView, 0, topic_name.length(), iSpanClick, 10);
    }

    /**
     * 处理标签
     *
     * @param contentTextView
     * @param content
     */
    public static void setListTopicContent(final TextView contentTextView, final String topic_name,
                                           final String content, final ISpanClick iSpanClick) {
        String html = "<font color='#0bad61'>" + topic_name + "</font>"
                + "<font color='#333333'>" + content + "</font>";
        contentTextView.setText(fromHtml(html));
        //如果话题就跳转话题详情
        if (StringUtils.isNotNull(topic_name)) {
            contentTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (iSpanClick != null) {
                        iSpanClick.onClick();
                    }
                }
            });
        }
    }

    /**
     * 设置多段不同文本
     *
     * @param textView
     * @param title
     * @param content
     */
    public static void setMultiPartText(TextView textView, String titleColor, String contentColor, String title, String content) {
        String html = "<font color='" + titleColor + "'>" + title + "</font>"
                + "<font color='" + contentColor + "'>" + content + "</font>";
        textView.setText(SpanUtils.fromHtml(html));
    }

    /**
     * 设置多段不同文本
     *
     * @param textView
     * @param title
     * @param content
     */
    public static void setMultiPartText_Three(TextView textView, String firstColor, String titleColor, String contentColor, String firstTitle, String title, String content) {
        String html = "<font color='" + firstColor + "'>" + firstTitle + "</font>" + "<font color='" + titleColor + "'>" + title + "</font>"
                + "<font color='" + contentColor + "'>" + content + "</font>";
        textView.setText(SpanUtils.fromHtml(html));
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    /**
     * 处理标签
     *
     * @param sp
     * @param contentTextView
     * @param start
     * @param end
     */
    public static void setSpanContent(final Context context, final Spannable sp, final TextView contentTextView,
                                      final int start, final int end, final ISpanClick iSpanClick) {
        MyClickableSpan clickableSpan = new MyClickableSpan(context) {
            @Override
            public void onClick(View widget) {
                sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.white)), start, end, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
                sp.setSpan(new BackgroundColorSpan(ContextCompat.getColor(context, R.color.theme_color_press)), start, end, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
                contentTextView.setText(sp);
                contentTextView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.theme_color)), start, end, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
                        sp.setSpan(new BackgroundColorSpan(ContextCompat.getColor(context, R.color.white)), start, end, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
                        contentTextView.setText(sp);
                        contentTextView.removeCallbacks(null);
                    }
                }, 50);
                if (iSpanClick != null) {
                    iSpanClick.onClick();
                }
            }
        };
        setSpan(sp, contentTextView, start, end, clickableSpan, 15);
    }

    /**
     * 处理标签
     *
     * @param sp
     * @param contentTextView
     * @param start
     * @param end
     */
    public static void setSpanContent(final Context context, final Spannable sp, final TextView contentTextView,
                                      final int start, final int end, final ISpanClick iSpanClick, int textSize) {
        MyClickableSpan clickableSpan = new MyClickableSpan(context) {
            @Override
            public void onClick(View widget) {
                contentTextView.setText(sp);
                contentTextView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        contentTextView.setText(sp);
                        contentTextView.removeCallbacks(null);
                    }
                }, 50);
                if (iSpanClick != null) {
                    iSpanClick.onClick();
                }
            }
        };
        setSpan(sp, contentTextView, start, end, clickableSpan, textSize);
    }

    /**
     * @param sp
     * @param contentTextView
     * @param start
     * @param end
     * @param clickableSpan
     */
    @SuppressLint("ClickableViewAccessibility")
    private static void setSpan(Spannable sp, TextView contentTextView, int start, int end,
                                MyClickableSpan clickableSpan, int textSize) {
        sp.setSpan(clickableSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        contentTextView.setText(sp);
        contentTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        contentTextView.setMovementMethod(ClickableMovementMethod.getInstance());
        contentTextView.setOnTouchListener(ClickableMovementMethod.getInstance());
        contentTextView.setFocusable(false);
        contentTextView.setClickable(false);
        contentTextView.setLongClickable(false);
    }

    public interface ISpanClick {
        void onClick();
    }


    /**
     * 字符串高亮显示部分文字
     *
     * @param textView 控件
     * @param str1     要高亮显示的文字（输入的关键词）
     * @param str2     包含高亮文字的字符串
     */
    public static void setTextHighLight(TextView textView, String str1, String str2) {

        SpannableString sp = new SpannableString(str2);
        // 遍历要显示的文字
        for (int i = 0; i < str1.length(); i++) {
            // 得到单个文字
            String s1 = str1.charAt(i) + "";
            // 判断字符串是否包含高亮显示的文字
            if (str2.contains(s1)) {
                // 查找文字在字符串的下标
                int index = str2.indexOf(s1);
                // 循环查找字符串中所有该文字并高亮显示
                while (index != -1) {
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0bad61"));
                    sp.setSpan(colorSpan, index, index + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    // s1从这个索引往后开始第一个出现的位置
                    index = str2.indexOf(s1, index + 1);
                }
            }
        }
        // 设置控件
        textView.setText(sp);
    }
}
