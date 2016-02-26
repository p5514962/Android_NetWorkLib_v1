package com.eascs.app.volleylib.http;

public class  HttpRequestModel {

    private boolean isShowProgress = true;
    private boolean isShowLoadingAnimation = false;
    private boolean isShowNetErrorDialog = true;
    private boolean isShowSystemBusyDialog = true;
    private boolean isCanceled;
    private String txtMsg;
    private Object requestTag = new Object();
    private Object tag = new Object();

    public HttpRequestModel() {
        super();
    }

    public HttpRequestModel(boolean isShowProgress,
                            boolean isShowLoadingAnimation, boolean isShowNetErrorDialog,
                            boolean isShowSystemBusyDialog) {
        super();
        this.isShowProgress = isShowProgress;
        this.isShowLoadingAnimation = isShowLoadingAnimation;
        this.isShowNetErrorDialog = isShowNetErrorDialog;
        this.isShowSystemBusyDialog = isShowSystemBusyDialog;
    }


    public boolean isShowProgress() {
        return isShowProgress;
    }

    public void setShowProgress(boolean isShowProgress) {
        this.isShowProgress = isShowProgress;
    }

    public boolean isShowLoadingAnimation() {
        return isShowLoadingAnimation;
    }

    public void setShowLoadingAnimation(boolean isShowLoadingAnimation) {
        this.isShowLoadingAnimation = isShowLoadingAnimation;
    }

    public boolean isShowNetErrorDialog() {
        return isShowNetErrorDialog;
    }

    public void setShowNetErrorDialog(boolean isShowNetErrorDialog) {
        this.isShowNetErrorDialog = isShowNetErrorDialog;
    }

    public boolean isShowSystemBusyDialog() {
        return isShowSystemBusyDialog;
    }

    public void setShowSystemBusyDialog(boolean isShowSystemBusyDialog) {
        this.isShowSystemBusyDialog = isShowSystemBusyDialog;
    }

    public String getTxtMsg() {
        return txtMsg;
    }

    public void setTxtMsg(String txtMsg) {
        this.txtMsg = txtMsg;
    }

    public Object getRequestTag() {
        return requestTag;
    }

    public void setRequestTag(Object requestTag) {
        this.requestTag = requestTag;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "HttpRequestModel{" +
          "isShowProgress=" + isShowProgress +
          ", isShowLoadingAnimation=" + isShowLoadingAnimation +
          ", isShowNetErrorDialog=" + isShowNetErrorDialog +
          ", isShowSystemBusyDialog=" + isShowSystemBusyDialog +
          ", isCanceled=" + isCanceled +
          ", txtMsg='" + txtMsg + '\'' +
          ", requestTag=" + requestTag +
          ", tag=" + tag +
          '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpRequestModel that = (HttpRequestModel) o;

        if (isShowProgress != that.isShowProgress) return false;
        if (isShowLoadingAnimation != that.isShowLoadingAnimation) return false;
        if (isShowNetErrorDialog != that.isShowNetErrorDialog) return false;
        if (isShowSystemBusyDialog != that.isShowSystemBusyDialog) return false;
        if (isCanceled != that.isCanceled) return false;
        if (txtMsg != null ? !txtMsg.equals(that.txtMsg) : that.txtMsg != null) return false;
        if (requestTag != null ? !requestTag.equals(that.requestTag) : that.requestTag != null)
            return false;
        return !(tag != null ? !tag.equals(that.tag) : that.tag != null);

    }

    @Override
    public int hashCode() {
        int result = (isShowProgress ? 1 : 0);
        result = 31 * result + (isShowLoadingAnimation ? 1 : 0);
        result = 31 * result + (isShowNetErrorDialog ? 1 : 0);
        result = 31 * result + (isShowSystemBusyDialog ? 1 : 0);
        result = 31 * result + (isCanceled ? 1 : 0);
        result = 31 * result + (txtMsg != null ? txtMsg.hashCode() : 0);
        result = 31 * result + (requestTag != null ? requestTag.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}
