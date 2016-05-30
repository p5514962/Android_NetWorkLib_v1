package com.eascs.app.network.interfaces.interceptor;

import com.eascs.app.network.interfaces.Checker;
import com.eascs.app.network.model.RequestInfo;
import com.eascs.app.network.volley.VolleyError;

/*** 
 * @version V1.0
 * @author KevinHo
 * @desc 拦截器接口
 * @time 2016/5/5 0005 15:45
 * @reference 
 */
public interface RequestInterceptor<K,T extends VolleyError> extends Checker {
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

    T returnError(RequestInterceptor requestInterceptor);

    boolean onCallBackPage();


}
