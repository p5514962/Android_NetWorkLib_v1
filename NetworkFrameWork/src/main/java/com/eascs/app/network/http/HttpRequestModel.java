package com.eascs.app.network.http;

import com.eascs.app.network.impl.ApiRetryPolicy;

/***
 * @author KevinHo
 * @version V1.0
 * @desc 描述
 * @time 2016/5/5 0005 10:29
 * @reference
 */
public class HttpRequestModel {

    //请求唯一tag
    private Object requestTag;

    //额外业务数据
    private Object extrasData;

    //重试策略
    private ApiRetryPolicy retryPolicy;

    /**
     * 构造函数
     * @param requestTag 请求唯一tag
     * @param extrasData 业务额外数据
     * @param retryPolicy 重试策略
     */
    public HttpRequestModel(Object requestTag, Object extrasData, ApiRetryPolicy retryPolicy) {
        this.requestTag = requestTag;
        this.retryPolicy = retryPolicy;
        this.extrasData = extrasData;
    }

    /**
     * 构造函数:至少传递请求唯一标识
     * @param requestTag 请求唯一tag
     */
    public HttpRequestModel(Object requestTag) {
        this(requestTag, null, new ApiRetryPolicy());
    }

    public Object getRequestTag() {
        return requestTag;
    }

    public void setRequestTag(Object requestTag) {
        this.requestTag = requestTag;
    }

    public Object getTag() {
        return extrasData;
    }

    public void setTag(Object extrasData) {
        this.extrasData = extrasData;
    }

    public ApiRetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(ApiRetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    @Override
    public String toString() {
        return "HttpRequestModel{" +
                ", requestTag=" + requestTag +
                ", extrasData=" + extrasData +
                '}';
    }


}
