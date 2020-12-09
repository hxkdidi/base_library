package com.lptiyu.lp_base.uitls.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lptiyu.lp_base.R;
import com.lptiyu.lp_base.uitls.StatusBarUtil;
import com.lptiyu.lp_base.uitls.ToastUtil;
import com.lptiyu.lp_base.uitls.dialog.DialogData;
import com.lptiyu.lp_base.uitls.dialog.HoloDialogFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import fullscreenlibs.SwipeBackActivityBase;
import fullscreenlibs.SwipeBackActivityHelper;
import fullscreenlibs.SwipeBackLayout;
import fullscreenlibs.Utils;

/**
 * Created by Jason on 2016/8/5.
 */
public class LpBaseActivity extends AppCompatActivity implements SwipeBackActivityBase {

    public ProgressDialog waitingDialog;
    private SwipeBackActivityHelper mHelper;
    protected Context context;
    protected LpBaseActivity activity;

    public void showWaitingDialog(String message) {
        if (waitingDialog == null) {
            waitingDialog = new ProgressDialog(this);// 后期观察，如果有问题，this改为getParent()，防止出现is your activity
            // running问题
            waitingDialog.setCancelable(false);
            waitingDialog.setIndeterminate(true);
        }
        if (TextUtils.isEmpty(message)) {
            waitingDialog.setMessage(getString(R.string.please_wait));
        } else {
            waitingDialog.setMessage(message);
        }
        if (!activity.isFinishing() && !waitingDialog.isShowing()) {
            waitingDialog.show();
        }
    }

    public void showWaitingDialog() {
        showWaitingDialog(null);
    }

    public void dismissWaitingDialog() {
        if (waitingDialog != null && waitingDialog.isShowing()) {
            waitingDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activity = this;
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        StatusBarUtil.setStatusBarTranslucent(this);
        StatusBarUtil.setSpecialStatusBar(this);
    }

    /**
     * 全屏
     */
    protected void setFullScreen() {
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
                .FLAG_FULLSCREEN);
    }

    /**
     * 显示dialog
     *
     * @param type
     * @param dialogData
     * @return
     */
    public HoloDialogFragment showDialogFragment(int type, DialogData dialogData) {
        HoloDialogFragment mDialogFragment = new HoloDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        mDialogFragment.setArguments(bundle);
        mDialogFragment.setDialogData(dialogData);
        mDialogFragment.setFragmentManager(activity.getSupportFragmentManager());
        mDialogFragment.show();
        return mDialogFragment;
    }

    public HoloDialogFragment showDialogFragment(DialogData dialogData) {
        return showDialogFragment(HoloDialogFragment.NativeAlertTitleDialogType, dialogData);
    }

    /**
     * 取消dialog
     */
    public void cancelDialogFragment(HoloDialogFragment dialogFragment) {
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        dismissWaitingDialog();
        super.onDestroy();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    protected void showTextToast(String toastMsg) {
        ToastUtil.showTextToast(toastMsg);
    }

    /**
     * 控制是否提示
     *
     * @return
     */
    protected boolean showTips() {
        return true;
    }

    protected int getColors(int resId) {
        return ContextCompat.getColor(activity, resId);
    }

    protected String getStrings(int resId) {
        return context.getString(resId);
    }

    protected Drawable getDrwables(int resId) {
        return ContextCompat.getDrawable(activity, resId);
    }
}
