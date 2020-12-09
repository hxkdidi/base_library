package com.lptiyu.lp_base.uitls.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lptiyu.lp_base.R;
import com.lptiyu.lp_base.uitls.DisplayUtils;
import com.lptiyu.lp_base.uitls.LogUtils;
import com.lptiyu.lp_base.uitls.NetworkUtil;
import com.lptiyu.lp_base.uitls.StringUtils;
import com.lptiyu.lp_base.uitls.ToastUtil;
import com.lptiyu.lp_base.uitls.span.SmileUtils;
import com.lptiyu.lp_base.uitls.span.SpanUtils;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class HoloDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final int NativeAlertTitleDialogType = 2;
    public static final int NativeAlertTitleDialogTypeVertical = 3;
    public static final int NativeAlertDialogTypeRadioGroupVertical = 4;
    private DialogData mDialogData;
    private FragmentManager fragmentManager;

    private int type = -1;

    private boolean isShowing = false;
    private TextView contentTextView;

    public boolean isShowing() {
        return isShowing;
    }

    public void setFragmentManager(FragmentManager manager) {
        this.fragmentManager = manager;
    }

    public void setDialogData(DialogData dialogData) {
        this.mDialogData = dialogData;
    }

    @Override
    public void dismiss() {
        isShowing = false;
        if (getFragmentManager() != null) {
            try {
                super.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void show() {
        isShowing = true;
        boolean isAdded = isAdded();
        LogUtils.i("isAdded = " + isAdded);
        if (!isAdded && mDialogData != null) {
            String tag = mDialogData.getTag();
            try {
                this.show(fragmentManager, tag);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.add(this, tag);
                    ft.commitAllowingStateLoss();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        type = getArguments().getInt("type");
        Dialog dialog = null;
        switch (type) {
            case NativeAlertTitleDialogType:
                dialog = getNativeAlertTitleDialog(new HoloNativeAlertTitleDialog(getContext()));
                break;
            case NativeAlertDialogTypeRadioGroupVertical:
                dialog = getNativeAlertRadioGroupDialog(new NativeAlertDialogTypeRadioGroup(getContext()));
                break;
            case NativeAlertTitleDialogTypeVertical:
                dialog = getNativeAlertTitleDialog(new HoloAlertDialogVertical(getContext()));
                ImageView iv_close = dialog.findViewById(R.id.iv_close);
                iv_close.setOnClickListener(this);
                break;
            default:
                break;
        }
        if (dialog != null) {
            TextView mConfirmButton = dialog.findViewById(R.id.dialog_confirm);
            TextView mCancelButton = dialog.findViewById(R.id.dialog_cancel);
            mCancelButton.setOnClickListener(this);
            mConfirmButton.setOnClickListener(this);
            if (mDialogData != null && StringUtils.isNotNull(mDialogData.getConfirmText())) {
                mConfirmButton.setText(mDialogData.getConfirmText());
            } else {
                mConfirmButton.setVisibility(View.GONE);
            }
            if (mDialogData != null && StringUtils.isNotNull(mDialogData.getCancelText())) {
                mCancelButton.setText(mDialogData.getCancelText());
            } else {
                mCancelButton.setVisibility(View.GONE);
            }
            if (mDialogData != null && mDialogData.isHideCancelButton()) {
                mCancelButton.setVisibility(View.GONE);
            } else {
                mCancelButton.setVisibility(View.VISIBLE);
            }
            if (mDialogData != null && mDialogData.isHideComfirmButton()) {
                mConfirmButton.setVisibility(View.GONE);
            } else {
                mConfirmButton.setVisibility(View.VISIBLE);
            }
            if (mDialogData != null && mDialogData.getCancelColor() != 0) {
                mCancelButton.setTextColor(mDialogData.getCancelColor());
            }
            if (mDialogData != null && mDialogData.getConfirmColor() != 0) {
                mConfirmButton.setTextColor(mDialogData.getConfirmColor());
            }
            if (mDialogData != null && mDialogData.getConfirmTextRes() != 0) {
                mConfirmButton.setBackground(ContextCompat.getDrawable(getContext(), mDialogData.getConfirmTextRes()));
            }
        }
        return dialog;
    }

    private boolean isAll = false;

    private Dialog getNativeAlertRadioGroupDialog(Dialog dialog) {
        contentTextView = dialog.findViewById(R.id.alert_dialog_content);
        TextView title = dialog.findViewById(R.id.alert_dialog_title);
        TextView dialog_confirm = dialog.findViewById(R.id.dialog_confirm);
        final RadioButton base_operation = dialog.findViewById(R.id.base_operation);
        final RadioButton all_operation = dialog.findViewById(R.id.all_operation);
        base_operation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                all_operation.setChecked(!isChecked);
                if (isChecked) {
                    isAll = false;
                }
            }
        });
        all_operation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                base_operation.setChecked(!isChecked);
                isAll = isChecked;
            }
        });
        if (mDialogData != null && mDialogData.isDelete()) {
            base_operation.setText("仅删除本条");
            all_operation.setText("全部删除");
        } else if (mDialogData != null) {
            if (mDialogData.isTop()) {
                base_operation.setText("仅取消本条置顶");
                all_operation.setText("取消全部置顶");
            } else {
                base_operation.setText("仅置顶本条");
                all_operation.setText("全部置顶");
            }
        }
        LinearLayout ll_button = dialog.findViewById(R.id.ll_button);
        String content = "";
        String message = "";
        if (mDialogData != null) {
            content = mDialogData.getContent();
            message = mDialogData.getMessage();
        }
        contentTextView.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(message)) {
            setContent(message);
        } else if (!TextUtils.isEmpty(content)) {
            setContent(content);
        } else {
            contentTextView.setVisibility(View.GONE);
        }
        View divider = dialog.findViewById(R.id.divider);
        if (mDialogData != null && mDialogData.isShowDivider()) {
            divider.setVisibility(View.VISIBLE);
        } else {
            divider.setVisibility(View.GONE);
        }
        if (mDialogData != null && mDialogData.isShowSingleButtonPadding()) {
            ll_button.setPadding(DisplayUtils.dp2px(getContext(), 30), DisplayUtils.dp2px(getContext(), 12),
                    DisplayUtils.dp2px(getContext(), 30), DisplayUtils.dp2px(getContext(), 10));
        } else {
            ll_button.setPadding(DisplayUtils.dp2px(getContext(), 30), DisplayUtils.dp2px(getContext(), 24),
                    DisplayUtils.dp2px(getContext(), 30), DisplayUtils.dp2px(getContext(), 20));
        }
        if (mDialogData != null && mDialogData.getConfirmTextSize() > 0) {
            dialog_confirm.setTextSize(TypedValue.COMPLEX_UNIT_SP, mDialogData.getConfirmTextSize());
        } else {
            dialog_confirm.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        }
        if (mDialogData != null && StringUtils.isNotNull(mDialogData.getTitle())) {
            title.setText(mDialogData.getTitle());
        }
        if (mDialogData != null && mDialogData.isShowTitle()) {
            title.setVisibility(View.VISIBLE);
        } else {
            title.setVisibility(View.GONE);
        }
        if (mDialogData != null && mDialogData.getTitleColor() != 0) {
            title.setTextColor(mDialogData.getTitleColor());
        }
        setCancelable(mDialogData != null && mDialogData.isCancelable());
        return dialog;
    }

    class HoloAlertDialogVertical extends Dialog {

        public HoloAlertDialogVertical(Context context) {
            this(context, R.style.no_title);
        }

        public HoloAlertDialogVertical(Context context, int theme) {
            this(context, theme, R.layout.alert_dialog_vertical_button);
        }

        public HoloAlertDialogVertical(Context context, int theme, int layout) {
            super(context, theme);
            setContentView(layout);
            setDialogParams(this);
        }

    }

    class NativeAlertDialogTypeRadioGroup extends Dialog {

        public NativeAlertDialogTypeRadioGroup(Context context) {
            this(context, R.style.no_title);
        }

        public NativeAlertDialogTypeRadioGroup(Context context, int theme) {
            this(context, theme, R.layout.alert_dialog_vertical_radio_group);
        }

        public NativeAlertDialogTypeRadioGroup(Context context, int theme, int layout) {
            super(context, theme);
            setContentView(layout);
            setDialogParams(this);
        }

    }

    private void setDialogParams(Dialog dialog) {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.width = (int) (DisplayUtils.width(getContext()) * 0.8);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }

    class HoloNativeAlertTitleDialog extends Dialog {

        public HoloNativeAlertTitleDialog(Context context) {
            this(context, R.style.no_title);
        }

        public HoloNativeAlertTitleDialog(Context context, int theme) {
            this(context, theme, R.layout.native_alert_dialog_title_holo);
        }

        public HoloNativeAlertTitleDialog(Context context, int theme, int layout) {
            super(context, theme);
            setContentView(layout);
            setDialogParams(this);
        }

    }

    /**
     * 公共样式Dialog
     *
     * @return
     */
    private Dialog getNativeAlertTitleDialog(Dialog dialog) {
        contentTextView = dialog.findViewById(R.id.alert_dialog_content);
        TextView title = dialog.findViewById(R.id.alert_dialog_title);
        TextView dialog_confirm = dialog.findViewById(R.id.dialog_confirm);
        LinearLayout ll_button = dialog.findViewById(R.id.ll_button);
        String content = "";
        String message = "";
        if (mDialogData != null) {
            content = mDialogData.getContent();
            message = mDialogData.getMessage();
        }
        contentTextView.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(message)) {
            setContent(message);
        } else if (!TextUtils.isEmpty(content)) {
            setContent(content);
        } else {
            contentTextView.setVisibility(View.GONE);
        }
        View divider = dialog.findViewById(R.id.divider);
        if (mDialogData != null && mDialogData.isShowDivider()) {
            divider.setVisibility(View.VISIBLE);
        } else {
            divider.setVisibility(View.GONE);
        }
        if (mDialogData != null && mDialogData.isShowSingleButtonPadding()) {
            ll_button.setPadding(DisplayUtils.dp2px(getContext(), 30), DisplayUtils.dp2px(getContext(), 12),
                    DisplayUtils.dp2px(getContext(), 30), DisplayUtils.dp2px(getContext(), 10));
        } else {
            ll_button.setPadding(DisplayUtils.dp2px(getContext(), 30), DisplayUtils.dp2px(getContext(), 24),
                    DisplayUtils.dp2px(getContext(), 30), DisplayUtils.dp2px(getContext(), 20));
        }
        if (mDialogData != null && mDialogData.getConfirmTextSize() > 0) {
            dialog_confirm.setTextSize(TypedValue.COMPLEX_UNIT_SP, mDialogData.getConfirmTextSize());
        } else {
            dialog_confirm.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        }
        //答题弹窗的特殊处理
        if (mDialogData != null && mDialogData.unfinisedCount > 0) {
            SpanUtils.setMultiPartText_Three(contentTextView, "#333333", "#ff0000",
                    "#333333", "你还有",
                    mDialogData.unfinisedCount + "", "道题未做，"
                            + getString(R.string.are_you_sure_submit_test_paper));
        }
        if (mDialogData != null && StringUtils.isNotNull(mDialogData.getTitle())) {
            title.setText(mDialogData.getTitle());
        }
        if (mDialogData != null && mDialogData.isShowTitle()) {
            title.setVisibility(View.VISIBLE);
        } else {
            title.setVisibility(View.GONE);
        }
        if (mDialogData != null && mDialogData.getTitleColor() != 0) {
            title.setTextColor(mDialogData.getTitleColor());
        }
        setCancelable(mDialogData != null && mDialogData.isCancelable());
        return dialog;
    }

    /**
     * 设置内容
     *
     * @param message
     */
    private void setContent(String message) {
        if (mDialogData != null && !mDialogData.isUserPrivacy()) {
            setImageSpan(message);
        } else {
            setPrivacy();
        }
    }

    /**
     * 设置
     */
    private void setPrivacy() {
        if (contentTextView != null) {
            avoidHintColor(contentTextView);
            contentTextView.setText("提交认证信息表示加入学校并接受学校管理对应运动数据，详情见");
            SpannableString clickString2 = new SpannableString("《用户协议》");
            clickString2.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    if (mDialogData != null && mDialogData.getOnUserPotoco() != null) {
                        mDialogData.getOnUserPotoco().onOnUserUserPotoco();
                    }
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(getContext().getResources().getColor(R.color.theme_color));
                    ds.setUnderlineText(false);
                    ds.clearShadowLayer();
                }
            }, 0, clickString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            contentTextView.append(clickString2);
            contentTextView.append(new SpannableString("及"));
            SpannableString clickString = new SpannableString("《隐私政策》");
            clickString.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    if (mDialogData != null && mDialogData.getOnUserPrivacy() != null) {
                        mDialogData.getOnUserPrivacy().onOnUserPrivacy();
                    }
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(getContext().getResources().getColor(R.color.theme_color));
                    ds.setUnderlineText(false);
                    ds.clearShadowLayer();
                }
            }, 0, clickString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            contentTextView.append(clickString);
            contentTextView.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件
        }
    }

    private void avoidHintColor(View view) {
        if (view instanceof TextView)
            ((TextView) view).setHighlightColor(getResources().getColor(android.R.color.transparent));
    }

    /**
     * 设置多行文本加图片
     *
     * @param message
     */
    private void setImageSpan(String message) {
        if (mDialogData != null && mDialogData.getmMsgAlign() > 0) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            if (mDialogData.getmMsgAlign() == DialogData.ALIGN_LEFT) {
                layoutParams.gravity = Gravity.LEFT;
            } else {
                layoutParams.gravity = Gravity.CENTER;
            }
            contentTextView.setGravity(Gravity.LEFT);
            contentTextView.setLayoutParams(layoutParams);
            if (mDialogData.isMuiltLines()) {
                Spannable span = SmileUtils.getSmiledText(getContext(), message, true);
                //设置内容
                contentTextView.setText(span, TextView.BufferType.SPANNABLE);
            }
        } else {
            contentTextView.setText(message);
        }
    }

    private void internalDismiss() {
        if (mDialogData != null && mDialogData.isAutoDismiss()) {
            dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.dialog_cancel) {
            if (mDialogData != null && mDialogData.getDialogOnNegativeClick() != null) {
                mDialogData.getDialogOnNegativeClick().onNegativeClick(HoloDialogFragment.this);
            }
            dismiss();
        } else if (id == R.id.iv_close) {
            dismiss();
        } else if (id == R.id.dialog_confirm) {
            if (mDialogData != null && mDialogData.getDialogOnPositiveClick() != null) {
                if (mDialogData.isAutoDismiss()) {
                    mDialogData.getDialogOnPositiveClick().onPositiveClick(HoloDialogFragment.this);
                } else {
                    if (!NetworkUtil.isNetConnected(getActivity())) {
                       ToastUtil.showTextToast( "请先连接网络～");
                    } else {
                        mDialogData.getDialogOnPositiveClick().onPositiveClick(HoloDialogFragment.this);
                    }
                }
            }
            if (mDialogData != null && mDialogData.getOnRadioButtonClick() != null) {
                mDialogData.getOnRadioButtonClick().onRadioButtonClick(mDialogData.isDelete(), isAll);
                dismiss();
            } else {
                internalDismiss();
            }
        } else {
            dismiss();
        }
    }

    public void setShowing(boolean showing) {
        isShowing = showing;
    }

}

