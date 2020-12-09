package com.lptiyu.lp_base.uitls;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lptiyu.lp_base.R;
import com.lptiyu.lp_base.uitls.base.BaseApplication;

import androidx.annotation.LayoutRes;


/**
 * @author : xiaoxiaoda
 * date: 16-5-6
 * email: wonderfulifeel@gmail.com
 */
public class ToastUtil {

    public static void showNativeToast(String text) {
        Toast.makeText(BaseApplication.getAppContext(), text, Toast.LENGTH_SHORT).show();
    }

    private static Toast toast;

    public static void showRectangleImageToast(String text, int resId) {
        Toast toast = getShortToast();
        View view = getView(R.layout.image_toast_layout);
        ImageView toast_img = view.findViewById(R.id.toast_img);
        TextView toast_msg = view.findViewById(R.id.toast_msg);
        toast_img.setImageResource(resId);
        toast.setView(view);
        toast_msg.setText(text);
        toast.show();
    }

    public static void showNormalImageToast(String text, int resId) {
        Toast toast = getShortToast();
        View view = getView(R.layout.image_toast_layout_normal);
        toast.setView(view);
        ImageView toast_img = view.findViewById(R.id.toast_img);
        TextView toast_msg = view.findViewById(R.id.toast_msg);
        toast_img.setImageResource(resId);
        toast_msg.setText(text);
        toast.show();
    }

    private static View getView(@LayoutRes int layouId) {
        View view = LayoutInflater.from(BaseApplication.getAppContext()).inflate(layouId, null);
        return view;
    }

    public static void showWholeCornerImageToast(String text, int resId) {
        Toast toast = getShortToast();
        View view = getView(R.layout.image_toast_layout_whole_corner);
        ImageView toast_img = view.findViewById(R.id.toast_img);
        TextView toast_msg = view.findViewById(R.id.toast_msg);
        toast_img.setImageResource(resId);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) toast_msg.getLayoutParams();
        layoutParams.gravity = Gravity.CENTER;
        toast_msg.setLayoutParams(layoutParams);
        toast_msg.setText(text);
        toast_msg.setGravity(Gravity.CENTER_HORIZONTAL);
        toast_msg.setLineSpacing(0, 1.3f);
        toast.setView(view);
        toast.show();
    }

    private static Toast getLongToast() {
        return getToast(Toast.LENGTH_LONG);
    }

    private static Toast getToast(int lengthLong) {
        if (toast == null) {
            toast = new Toast(BaseApplication.getAppContext());
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(lengthLong);
        }
        return toast;
    }

    private static Toast getShortToast() {
        return getToast(Toast.LENGTH_SHORT);
    }

    public static void showTextToast(String text) {
        if (checkText(text)) return;
        Toast toast = getShortToast();
        TextView view = getTextView(text);
        toast.setView(view);
        toast.show();
    }

    private static final int padding = 18;

    /**
     * 自定义TextView
     *
     * @param text
     * @return
     */
    private static TextView getTextView(String text) {
        TextView view = new TextView(BaseApplication.getAppContext());
        view.setText(text);
        view.setBackgroundResource(R.drawable.shape_bg_toast);
        view.setPadding(padding, padding, padding, padding);
        view.setLineSpacing(0, 1.2f);
        view.setTextSize(15);
        view.setTextColor(Color.WHITE);
        return view;
    }

    private static boolean checkText(String text) {
        return StringUtils.isNull(text);
    }

    public static void showNoNetToast() {
        Context context = BaseApplication.getAppContext();
        showTextToast(context.getString(R.string.no_network));
    }

    /**
     * 请求失败回调才调用这个方法
     *
     * @param
     */
    public static void showLoadErrorToast() {
        Context context = BaseApplication.getAppContext();
        if (!NetworkUtil.isNetConnected(context)) {
            showNoNetToast();
        } else {
            showTextToast(context.getString(R.string.net_error));
        }
    }

    /**
     * 请求失败回调才调用这个方法，自定义错误提示
     *
     * @param
     * @param errorMsg
     */
    public static void showNetWorkDefineMsgToast(String errorMsg) {
        Context context = BaseApplication.getAppContext();
        if (!NetworkUtil.isNetConnected(context)) {
            showNoNetToast();
        } else {
            if (TextUtils.isEmpty(errorMsg)) {
                showTextToast(context.getString(R.string.net_error));
            } else {
                showTextToast(errorMsg);
            }
        }
    }

    public static void showTextToast(String text, int resId) {
        if (checkText(text)) return;
        Toast toast = getShortToast();
        TextView view = getTextView(text);
        Drawable drawable = BaseApplication.getAppContext().getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        view.setCompoundDrawables(drawable, null, null, null);
        view.setCompoundDrawablePadding(5);
        view.setText(text);
        toast.setView(view);
        toast.show();
    }

    public static void showTextLongToast(String text) {
        if (checkText(text)) return;
        Toast toast = getLongToast();
        TextView view = getTextView(text);
        toast.setView(view);
        toast.show();
    }
}
