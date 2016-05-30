package com.eascs.app.network.model.exception;

import com.eascs.app.network.interfaces.interceptor.RequestInterceptor;
import com.eascs.app.network.volley.VolleyError;

/**
 * Created by KevinHo on 2016/5/9 0009.
 */
public class InterceptorError extends VolleyError {

    private RequestInterceptor errorInterceptor;

    public InterceptorError(RequestInterceptor errorInterceptor) {
        this.errorInterceptor = errorInterceptor;
    }

    public RequestInterceptor getErrorInterceptor() {
        return errorInterceptor;
    }


}
