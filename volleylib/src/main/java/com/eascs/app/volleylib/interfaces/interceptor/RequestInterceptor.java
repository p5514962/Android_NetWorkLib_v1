package com.eascs.app.volleylib.interfaces.interceptor;

import com.eascs.app.volleylib.interfaces.Checker;
import com.eascs.app.volleylib.model.RequestInfo;

/*** 
 * @version V1.0
 * @author KevinHo
 * @desc 拦截器接口
 * @time 2016/5/5 0005 15:45
 * @reference 
 */
public interface RequestInterceptor<K> extends Checker {
    /**
     * @param requestInfo
     * @param data
     * @return
     */
    boolean onIntercept(RequestInfo requestInfo,K data);


    /**
     * @return
     */
    K returnData();

}
