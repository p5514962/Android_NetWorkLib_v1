package com.eascs.app.network.model.action;

import com.eascs.app.network.interfaces.interceptor.RequestInterceptor;

/***
 * @author KevinHo
 * @version V1.0
 * @desc 拦截器中心
 * @time 2016/5/10 0010 10:06
 * @reference
 */
public class InterceptAction {

    private RequestInterceptor[] customInterceptors = new RequestInterceptor[]{};

    private RequestInterceptor[] passInterceptors = new RequestInterceptor[]{};

//    private boolean ignoreDefault = false;

    public InterceptAction(RequestInterceptor[] customInterceptors, RequestInterceptor[] passInterceptors) {
        if(null != customInterceptors){
            this.customInterceptors = customInterceptors;
        }
        if(null != passInterceptors){
            this.passInterceptors = passInterceptors;
        }
    }

//    public InterceptAction(RequestInterceptor[] interceptors, boolean ignoreDefault) {
//        this.interceptors = interceptors;
//        this.ignoreDefault = ignoreDefault;
//    }

    public RequestInterceptor[] getCustomInterceptors() {
        return customInterceptors;
    }

    public RequestInterceptor[] getPassInterceptors() {
        return passInterceptors;
    }


//    public boolean isIgnoreDefault() {
//        return ignoreDefault;
//    }


}
