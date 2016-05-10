package com.eascs.app.volleylib.interfaces;

import android.content.Context;
import android.util.SparseArray;

import com.eascs.app.volleylib.interfaces.builder.IHeaderBuilder;
import com.eascs.app.volleylib.interfaces.builder.IUrlBuilder;
import com.eascs.app.volleylib.interfaces.filter.ResponseFilter;
import com.eascs.app.volleylib.interfaces.interceptor.RequestInterceptor;

/*** 
 * @version V1.0
 * @author KevinHo
 * @desc 网络框架接口
 * @time 2016/5/5 0005 10:10
 * @reference NetWorkApiFactory,具体场景
 */
public interface INetWorkAPI {
    /**
     * 注册NetWorkAPI
     * @param context 上下文
     */
    INetWorkAPI registerApp(Context context);

    /**
     * 设置Url 组装器
     * @param mIUrlBuilder Url 组装器
     */
    INetWorkAPI setUrlBuilder(IUrlBuilder mIUrlBuilder);

    /**
     * 设置网络请求头部 组装器
     * @param mIHeaderBuilder header 组装器
     */
    INetWorkAPI setHeaderBuilder(IHeaderBuilder mIHeaderBuilder);

    /**
     * 设置结果过滤器
     * @param responseFilter
     * @return
     */
    INetWorkAPI setResponseFilter(ResponseFilter[] responseFilter);

    /**
     * 设置请求前拦截器
     * @return
     */
    INetWorkAPI setRequestInterceptor(RequestInterceptor[] requestInterceptor);




}
