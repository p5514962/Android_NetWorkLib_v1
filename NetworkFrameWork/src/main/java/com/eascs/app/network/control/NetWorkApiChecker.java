package com.eascs.app.network.control;

import android.util.SparseArray;

import com.eascs.app.network.interfaces.filter.ResponseFilter;
import com.eascs.app.network.interfaces.interceptor.RequestInterceptor;

/***
 * @author KevinHo
 * @version V1.0
 * @desc 检查器中心
 * @time 2016/5/5 0005 13:32
 * @reference
 */

public class NetWorkApiChecker {

//    instance;

    SparseArray<RequestInterceptor> requestInterceptors = new SparseArray<>();

    SparseArray<ResponseFilter> responseFilters = new SparseArray<>();

    public SparseArray<RequestInterceptor> getRequestInterceptors() {
        return requestInterceptors;
    }


    public SparseArray<ResponseFilter> getResponseFilters() {
        return responseFilters;
    }


    ///==================


    RequestInterceptor[] requestInterceptor = new RequestInterceptor[]{};

    ResponseFilter[] responseFilter = new ResponseFilter[]{};

    public ResponseFilter[] getResponseFilter() {
        return responseFilter;
    }

    public void setResponseFilter(ResponseFilter[] responseFilter) {
        this.responseFilter = responseFilter;
        addFilterSparseArray(responseFilter);
    }

    public void setRequestInterceptor(RequestInterceptor[] requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
        addInterceptorsSparseArray(requestInterceptor);
    }


    private void addInterceptorsSparseArray(RequestInterceptor[] requestInterceptor) {
        requestInterceptors.clear();
        for (RequestInterceptor interceptor : requestInterceptor) {
            requestInterceptors.put(interceptor.uniqueKey(), interceptor);
        }
    }

    private void addFilterSparseArray(ResponseFilter[] responseFilter) {
        responseFilters.clear();
        for (ResponseFilter filter : responseFilter) {
            responseFilters.put(filter.uniqueKey(), filter);
        }
    }


    public RequestInterceptor[] getRequestInterceptor() {
        return requestInterceptor;
    }


}
