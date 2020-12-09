package com.lptiyu.lp_base.uitls.dialog;

public class DialogData {
    String mTag;//标志
    String title = "提示";//标题
    String message;//信息
    String content;//内容
    String mConfirmText = "确定";
    String mCancelText = "取消";
    int mConfirmTextRes;
    boolean mCancelable = true;//是否可以取消
    boolean hideCancelButton = false;
    boolean hideComfirmButton = false;
    public int unfinisedCount;
    private boolean autoDismiss = true;
    private boolean showDivider = false;
    private boolean showTitle = true;

    private boolean isTop = false;

    private boolean isDelete = false;

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isShowTitle() {
        return showTitle;
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    private boolean showSingleButtonPadding = false;

    public boolean isShowSingleButtonPadding() {
        return showSingleButtonPadding;
    }

    public void setShowSingleButtonPadding(boolean showSingleButtonPadding) {
        this.showSingleButtonPadding = showSingleButtonPadding;
    }

    public boolean isShowDivider() {
        return showDivider;
    }

    public void setShowDivider(boolean showDivider) {
        this.showDivider = showDivider;
    }

    public static final int ALIGN_LEFT = 1;

    private boolean isMuiltLines = false;//是多行文本，文本头部加图标

    public boolean isMuiltLines() {
        return isMuiltLines;
    }

    public void setMuiltLines(boolean muiltLines) {
        isMuiltLines = muiltLines;
    }

    private int mMsgAlign;

    public int getmMsgAlign() {
        return mMsgAlign;
    }

    public void setmMsgAlign(int mMsgAlign) {
        this.mMsgAlign = mMsgAlign;
    }

    private boolean isUserPrivacy = false;

    public boolean isUserPrivacy() {
        return isUserPrivacy;
    }

    public void setUserPrivacy(boolean userPrivacy) {
        isUserPrivacy = userPrivacy;
    }


    public void setAutoDismiss(boolean autoDismiss) {
        this.autoDismiss = autoDismiss;
    }

    public boolean isAutoDismiss() {
        return autoDismiss;
    }

    public void setHideComfirmButton(boolean hideComfirmButton) {
        this.hideComfirmButton = hideComfirmButton;
    }

    public DialogData setUnfinisedCount(int unfinisedCount) {
        this.unfinisedCount = unfinisedCount;
        return this;
    }

    public DialogOnPositiveClick mDialogOnPositiveClick;
    public DialogOnNegativeClick mDialogOnNegativeClick;

    public int titleColor;
    public int messageColor;
    public int confirmColor;
    public int cancelColor;

    public int confirmTextSize;

    public int getConfirmTextSize() {
        return confirmTextSize;
    }

    public DialogData setConfirmTextSize(int confirmTextSize) {
        this.confirmTextSize = confirmTextSize;
        return this;
    }

    public DialogData setHideCancelButton(boolean hideCancelButton) {
        this.hideCancelButton = hideCancelButton;
        return this;
    }

    public boolean isHideComfirmButton() {
        return hideComfirmButton;
    }

    public boolean isHideCancelButton() {
        return hideCancelButton;
    }

    public String getContent() {
        return content;
    }

    public DialogData setContent(String content) {
        this.content = content;
        return this;
    }

    public String getCancelText() {
        return mCancelText;
    }

    public String getConfirmText() {
        return mConfirmText;
    }

    public DialogData setConfirmColor(int confirmColor) {
        this.confirmColor = confirmColor;
        return this;
    }

    public DialogData setMessageColor(int messageColor) {
        this.messageColor = messageColor;
        return this;
    }

    public DialogData setCancelColor(int cancelColor) {
        this.cancelColor = cancelColor;
        return this;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public int getMessageColor() {
        return messageColor;
    }

    public int getConfirmColor() {
        return confirmColor;
    }

    public int getCancelColor() {
        return cancelColor;
    }

    public DialogData setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public boolean isCancelable() {
        return mCancelable;
    }

    public DialogData setCancelable(boolean cancleble) {
        this.mCancelable = cancleble;
        return this;
    }

    public DialogData(String tag) {
        this.mTag = tag;
    }

    public String getTag() {
        return mTag;
    }

    public String getMessage() {
        return message;
    }

    public DialogData setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public DialogData setTitle(String title) {
        this.title = title;
        return this;
    }

    public DialogData setCancelText(String mCancelText) {
        this.mCancelText = mCancelText;
        return this;
    }

    public DialogData setConfirmText(String mConfirmText) {
        this.mConfirmText = mConfirmText;
        return this;
    }

    public DialogData setConfirmTextRes(int mConfirmTextRes) {
        this.mConfirmTextRes = mConfirmTextRes;
        return this;
    }

    public interface OnUserPrivacy {
        void onOnUserPrivacy();
    }

    public interface OnUserPotoco {
        void onOnUserUserPotoco();
    }

    private OnUserPotoco onUserPotoco;

    private OnUserPrivacy onUserPrivacy;


    public OnUserPotoco getOnUserPotoco() {
        return onUserPotoco;
    }

    public void setOnUserPotoco(OnUserPotoco onUserPotoco) {
        this.onUserPotoco = onUserPotoco;
    }

    public OnUserPrivacy getOnUserPrivacy() {
        return onUserPrivacy;
    }

    public void setOnUserPrivacy(OnUserPrivacy onUserPrivacy) {
        this.onUserPrivacy = onUserPrivacy;
    }

    public int getConfirmTextRes() {
        return mConfirmTextRes;
    }

    public interface DialogOnPositiveClick {
        void onPositiveClick(HoloDialogFragment holoAlertDialogFragment);
    }

    public interface OnRadioButtonClick {
        void onRadioButtonClick(boolean isDelete,boolean isAll);
    }

    private OnRadioButtonClick onRadioButtonClick;

    public OnRadioButtonClick getOnRadioButtonClick() {
        return onRadioButtonClick;
    }

    public void setOnRadioButtonClick(OnRadioButtonClick onRadioButtonClick) {
        this.onRadioButtonClick = onRadioButtonClick;
    }

    public DialogData setDialogOnPositiveClick(DialogOnPositiveClick mDialogOnPositiveClick) {
        this.mDialogOnPositiveClick = mDialogOnPositiveClick;
        return this;
    }

    public DialogData setDialogOnNegativeClick(DialogOnNegativeClick mDialogOnNegativeClick) {
        this.mDialogOnNegativeClick = mDialogOnNegativeClick;
        return this;
    }

    public DialogOnPositiveClick getDialogOnPositiveClick() {
        return mDialogOnPositiveClick;
    }

    public DialogOnNegativeClick getDialogOnNegativeClick() {
        return mDialogOnNegativeClick;
    }


    public interface DialogOnNegativeClick {
        void onNegativeClick(HoloDialogFragment holoDialogFragment);
    }

}
