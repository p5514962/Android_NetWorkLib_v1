package com.eascs.app.volleylib.model.action;

import com.eascs.app.volleylib.interfaces.interceptor.RequestInterceptor;

/***
 * @version V1.0
 * @author KevinHo
 * @desc 拦截器中心
 * @time 2016/5/10 0010 10:06
 * @reference
 */
public class InterceptAction {

    private RequestInterceptor[] interceptors;

    private boolean ignoreDefault = false;

    public InterceptAction(RequestInterceptor[] interceptors) {
        this.interceptors = interceptors;
    }

    public InterceptAction(RequestInterceptor[] interceptors, boolean ignoreDefault) {
        this.interceptors = interceptors;
        this.ignoreDefault = ignoreDefault;
    }

    public RequestInterceptor[] getInterceptors() {
        return interceptors;
    }

    public boolean isIgnoreDefault() {
        return ignoreDefault;
    }


}
