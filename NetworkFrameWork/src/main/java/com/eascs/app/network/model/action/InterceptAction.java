package com.eascs.app.network.model.action;

import android.util.SparseArray;

import com.eascs.app.network.control.NetWorkApiChecker;
import com.eascs.app.network.control.NetWorkApiControlCenter;
import com.eascs.app.network.interfaces.interceptor.RequestInterceptor;

/***
 * @author KevinHo
 * @version V1.0
 * @desc 拦截器中心
 * @time 2016/5/10 0010 10:06
 * @reference
 */
public class InterceptAction {

    //自定义拦截器
    private RequestInterceptor[] customInterceptors = new RequestInterceptor[]{};

    //放行拦截器
    private RequestInterceptor[] passInterceptors = new RequestInterceptor[]{};


    public InterceptAction(){
    }

    public InterceptAction(RequestInterceptor[] customInterceptors) {//原子性自定义拦截器
        if (null != customInterceptors) {
            this.customInterceptors = customInterceptors;
        }
    }


    public void setPassInterceptors(RequestInterceptor[] passInterceptors) {//原子性放行拦截器
        if (null != passInterceptors) {
            this.passInterceptors = passInterceptors;
        }
    }

    public void setInterceptorsExtraData(RequestInterceptor[] targetInterceptors) {

        if(null == NetWorkApiControlCenter.instance.getNetWorkApiChecker()){//无初始化检查控制中心
            return;
        }

        SparseArray<RequestInterceptor> defaultRequestInterceptors =  NetWorkApiControlCenter.instance.getNetWorkApiChecker().getRequestInterceptors();

        if(null == defaultRequestInterceptors || defaultRequestInterceptors.size()<=0){
            return;//无默认请求拦截器
        }

        for (RequestInterceptor mTargetInterceptors : targetInterceptors) {
            //匹配默认过滤器和目标添加过滤（携带具体data model）
            RequestInterceptor defaultRequestInterceptor = defaultRequestInterceptors.get(mTargetInterceptors.uniqueKey());
            if(null != defaultRequestInterceptor){
                defaultRequestInterceptor.setData(mTargetInterceptors.returnData());
            }
        }
    }


//    public InterceptAction(RequestInterceptor[] customInterceptors, RequestInterceptor[] passInterceptors) {
//        if (null != customInterceptors) {
//            this.customInterceptors = customInterceptors;
//        }
//        if (null != passInterceptors) {
//            this.passInterceptors = passInterceptors;
//        }
//    }

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
