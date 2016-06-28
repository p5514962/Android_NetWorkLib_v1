package com.eascs.app.network.interfaces;

import android.content.Context;

import com.eascs.app.network.control.NetWorkApiBuilder;
import com.eascs.app.network.control.NetWorkApiChecker;
import com.eascs.app.network.http.RequestQueueManager;
import com.eascs.app.network.interfaces.builder.IHeaderBuilder;
import com.eascs.app.network.interfaces.builder.IUrlBuilder;
import com.eascs.app.network.interfaces.filter.ResponseFilter;
import com.eascs.app.network.interfaces.interceptor.RequestInterceptor;
import com.eascs.app.network.volley.RetryPolicy;

/***
 * @author KevinHo
 * @version V1.0
 * @desc 网络框架接口
 * @time 2016/5/5 0005 10:10
 * @reference NetWorkApiFactory, 具体场景
 */
public interface INetWorkAPI {
    /**
     * 注册NetWorkAPI
     *
     * @param context 上下文
     */
    INetWorkAPI registerApp(Context context);

    /**
     * 设置Url 组装器
     *
     * @param mIUrlBuilder Url 组装器
     */
    INetWorkAPI setUrlBuilder(IUrlBuilder mIUrlBuilder);

    /**
     * 设置网络请求头部 组装器
     *
     * @param mIHeaderBuilder header 组装器
     */
    INetWorkAPI setHeaderBuilder(IHeaderBuilder mIHeaderBuilder);

    /**
     * 设置结果过滤器
     *
     * @param responseFilter
     * @return
     */
    INetWorkAPI setResponseFilter(ResponseFilter[] responseFilter);

    /**
     * 设置默认重试机制
     * @param defaultRetryPolicy
     * @return
     */
    INetWorkAPI setDefaultRetryPolicy(RetryPolicy defaultRetryPolicy);

    /**
     * 设置请求前拦截器
     *
     * @return
     */
    INetWorkAPI setRequestInterceptor(RequestInterceptor[] requestInterceptor);

    NetWorkApiBuilder getNetWorkApiBuilder();

    NetWorkApiChecker getNetWorkApiChecker();

    RequestQueueManager getRequestQueueManager();

    RetryPolicy getDefaultRetryPolicy();


}
