package com.lptiyu.lp_base.uitls.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lptiyu.lp_base.R;
import com.lptiyu.lp_base.uitls.dialog.DialogData;
import com.lptiyu.lp_base.uitls.dialog.HoloDialogFragment;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * Created by Jason on 2016/9/23.
 */

public class LpBaseFragment extends Fragment {

    private ProgressDialog waitingDialog;

    protected LpBaseActivity activity;

    protected LayoutInflater inflater;
    private HoloDialogFragment mDialogFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (LpBaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        this.inflater = inflater;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void showWaitingDialog(String message) {
        if (waitingDialog == null) {
            waitingDialog = new ProgressDialog(getActivity());// 后期观察，如果有问题，this改为getParent()，防止出现is your
            waitingDialog.setCancelable(false);
            waitingDialog.setIndeterminate(true);
        }
        waitingDialog.setMessage(message);
        if (!waitingDialog.isShowing()) {
            waitingDialog.show();
        }
    }

    protected void showWaitingDialog() {
        if (waitingDialog == null) {
            waitingDialog = new ProgressDialog(getActivity());// 后期观察，如果有问题，this改为getParent()，防止出现is your
            waitingDialog.setCancelable(false);
            waitingDialog.setIndeterminate(true);
        }
        waitingDialog.setMessage(getActivity().getString(R.string.please_wait));
        if (!waitingDialog.isShowing()) {
            waitingDialog.show();
        }
    }

    protected void dismissWaitingDialog() {
        if (waitingDialog != null && waitingDialog.isShowing()) {
            waitingDialog.dismiss();
        }
    }

    //解决fragment嵌套的问题 androidX会出问题 https://mlog.club/article/840925
    @Override
    public void onDetach() {
        super.onDetach();
//        try {
//            Field childFragmentManager = Fragment.class
//                    .getDeclaredField("mChildFragmentManager");
//            childFragmentManager.setAccessible(true);
//            childFragmentManager.set(this, null);
//
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissWaitingDialog();
        cancelDialogFragment(mDialogFragment);
    }

    /**
     * 显示dialog
     *
     * @param type
     * @param dialogData
     * @return
     */
    public HoloDialogFragment showDialogFragment(int type, DialogData dialogData) {
         mDialogFragment = new HoloDialogFragment();
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 取消dialog
     */
    public void cancelDialogFragment(HoloDialogFragment dialogFragment) {
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }

    protected void showTextToast(String toastMsg) {
        if (activity != null) {
            activity.showTextToast(toastMsg);
        }
    }

    protected int getColors(int resId) {
        if (getContext() != null) {
            return ContextCompat.getColor(getContext(), resId);
        }
        return Color.TRANSPARENT;
    }

    protected String getStrings(int resId) {
        if (getContext() != null) {
            return getString(resId);
        }
        return "暂无记录";
    }

    protected Drawable getDrwables(int resId) {
        if (getContext() != null) {
            return ContextCompat.getDrawable(getContext(), resId);
        }
        return null;
    }
}
