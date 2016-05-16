package com.eascs.app.network.impl;

import android.content.Context;
import com.eascs.app.network.control.NetWorkApiBuilder;
import com.eascs.app.network.control.NetWorkApiChecker;
import com.eascs.app.network.control.NetWorkApiControlCenter;
import com.eascs.app.network.http.RequestQueueManager;
import com.eascs.app.network.interfaces.builder.IHeaderBuilder;
import com.eascs.app.network.interfaces.INetWorkAPI;
import com.eascs.app.network.interfaces.builder.IUrlBuilder;
import com.eascs.app.network.interfaces.filter.ResponseFilter;
import com.eascs.app.network.interfaces.interceptor.RequestInterceptor;


/*** 
 * @version V1.0
 * @author KevinHo
 * @desc INetWorkAPI 唯一实现类，注册UrlBuilder，HeaderBuilder
 * @time 2016/5/5 0005 10:05
 * @reference NetWorkApiFactory
 */
public class NetWorkApiImpl implements INetWorkAPI{

    private NetWorkApiBuilder netWorkApiBuilder;
    private NetWorkApiChecker netWorkApiChecker;
    private RequestQueueManager requestQueueManager;

    public NetWorkApiImpl(){
        netWorkApiBuilder = NetWorkApiControlCenter.instance.getNetWorkApiBuilder();
        netWorkApiChecker = NetWorkApiControlCenter.instance.getNetWorkApiChecker();
        requestQueueManager = NetWorkApiControlCenter.instance.getRequestQueueManager();
    }


    /**
     * 初始化默认Builder
     * @param context 上下文 注册请求队列使用
     * @return INetWorkAPI
     */
    @Override
    public INetWorkAPI registerApp(Context context) {
        requestQueueManager.initRequestQueue(context);
        initBuilder();
        initInterceptor();
        return this;
    }

    /**
     * 覆盖初始化 Url 组装器
     * @param mIUrlBuilder Url 组装器
     * @return
     */
    @Override
    public INetWorkAPI setUrlBuilder(IUrlBuilder mIUrlBuilder) {
        netWorkApiBuilder.setUrlBuilder(mIUrlBuilder);
        return this;
    }

    /**
     * 覆盖初始化 header 组装器
     * @param mIHeaderBuilder header 组装器
     * @return
     */
    @Override
    public INetWorkAPI setHeaderBuilder(IHeaderBuilder mIHeaderBuilder) {
        netWorkApiBuilder.setHeaderBuilder(mIHeaderBuilder);
        return this;
    }

    @Override
    public INetWorkAPI setRequestInterceptor(RequestInterceptor[] requestInterceptor) {
        netWorkApiChecker.setRequestInterceptor(requestInterceptor);
        return this;
    }

    @Override
    public INetWorkAPI setResponseFilter(ResponseFilter[] responseFilter){
        netWorkApiChecker.setResponseFilter(responseFilter);
        return this;
    }

    /**
     * 初始化默认Builder
     */
    private void initBuilder(){
    }

    /**
     * 初始化拦截器
     */
    private void initInterceptor(){
       //无初始化拦截器
    }

}
