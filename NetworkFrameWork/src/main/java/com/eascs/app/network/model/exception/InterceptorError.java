package com.eascs.app.network.model.exception;

import com.android.volley.VolleyError;
import com.eascs.app.network.interfaces.interceptor.RequestInterceptor;

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
