package com.eascs.app.volleylib.http;

import com.eascs.app.volleylib.impl.ApiRetryPolicy;

public class  HttpRequestModel {
    private String txtMsg;
    private Object requestTag = new Object();
    private Object tag = new Object();


    private ApiRetryPolicy retryPolicy;

    public HttpRequestModel() {
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

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "HttpRequestModel{" +
//          ", isCanceled=" + isCanceled +
          ", txtMsg='" + txtMsg + '\'' +
          ", requestTag=" + requestTag +
          ", tag=" + tag +
          '}';
    }


    public ApiRetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(ApiRetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }


    //    public boolean isCanceled() {
//        return isCanceled;
//    }
//
//    public void setCanceled(boolean isCanceled) {
//        this.isCanceled = isCanceled;
//    }


}
