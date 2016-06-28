package com.eascs.app.network.http;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.eascs.app.network.control.NetWorkApiControlCenter;
import com.eascs.app.network.constant.Constant;
import com.eascs.app.network.impl.ErrorListener;
import com.eascs.app.network.impl.JsonObjectCommonRequest;
import com.eascs.app.network.impl.ResponseListener;
import com.eascs.app.network.interfaces.HttpConnectionCallBack;
import com.eascs.app.network.interfaces.builder.IHeaderBuilder;
import com.eascs.app.network.interfaces.builder.IUrlBuilder;
import com.eascs.app.network.interfaces.interceptor.RequestInterceptor;
import com.eascs.app.network.model.action.CheckerAction;
import com.eascs.app.network.model.action.InterceptAction;
import com.eascs.app.network.model.action.RequestAction;
import com.eascs.app.network.model.exception.InterceptorError;
import com.eascs.app.network.model.RequestInfo;
import com.eascs.app.network.untils.LocalUntil;
import com.eascs.app.network.volley.AuthFailureError;
import com.eascs.app.network.volley.Request;
import com.eascs.app.network.volley.RetryPolicy;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/***
 * @author KevinHo
 * @version V1.0
 * @desc 请求类
 * @time 2016/5/5 0005 10:54
 * @reference
 */
public class HttpConnection implements RequestAction.IRequestAction {

    private HttpRequestModel httpRequestModel;
    private HttpConnectionCallBack callBack;
    private NetWorkApiControlCenter netWorkApiControlCenter;

    /**
     * 初始化控制中心
     */
    private void initControlCenter() {
        netWorkApiControlCenter = NetWorkApiControlCenter.instance;
    }

    /**
     * 直接请求不需要回调
     */
    public HttpConnection(HttpRequestModel httpRequestModel) {
        this(null, httpRequestModel);
    }

    public HttpConnection() {
        this(null, null);
    }


    /**
     * @param callBack
     * @param httpRequestModel
     */
    public HttpConnection(HttpConnectionCallBack callBack, HttpRequestModel httpRequestModel) {
        this.callBack = callBack;
        this.httpRequestModel = httpRequestModel;
        initControlCenter();
    }

    //===============分割线======================//

    public RequestAction request(int requestCode, int method, String url,
                                 Map<String, String> params) {
        return request(requestCode, method, url, params, Constant.REQUEST_TYPE.HTTP, Constant.ContentType.CONTENT_TYPE_FORM, null);
    }

    public RequestAction request(int requestCode, int method, String url,
                                 Map<String, String> params, Constant.REQUEST_TYPE protocol) {
        return request(requestCode, method, url, params, protocol, Constant.ContentType.CONTENT_TYPE_FORM, null);
    }

    public RequestAction request(int requestCode, int method, String url,
                                 Map<String, String> params, Constant.REQUEST_TYPE protocol, CheckerAction checkerAction) {
        return request(requestCode, method, url, params, protocol, Constant.ContentType.CONTENT_TYPE_FORM, checkerAction);
    }

    public RequestAction request(int requestCode, int method, String url,
                                 Map<String, String> params, CheckerAction checkerAction) {
        return request(requestCode, method, url, params, Constant.REQUEST_TYPE.HTTP, Constant.ContentType.CONTENT_TYPE_FORM, checkerAction);
    }

    /*
     *
     * @param context
     * @param requestCode
     * @param method
     * @param protocol
     * @param uri
     * @param params
     * @param protocol
     * @param contentType
     */
    public RequestAction request(int requestCode, final int method, String url, final Map<String, String> params,
                                 Constant.REQUEST_TYPE protocol, final String contentType, CheckerAction checkerAction) {
        if (httpRequestModel == null) {
            httpRequestModel = new HttpRequestModel(url);
        } else {
            if (null == httpRequestModel.getRequestTag()) {
                httpRequestModel.setRequestTag(url);
            }
        }


        Object uniqueTag = httpRequestModel.getRequestTag();

        RequestAction requestAction = new RequestAction(httpRequestModel.getRequestTag(), this);

        //===============过滤自定义拦截器===============//

        //响应监听器
        ResponseListener mResponseListener = new ResponseListener(requestCode, httpRequestModel, callBack, requestAction);

        //错误响应监听器
        ErrorListener mErrorListener = new ErrorListener(httpRequestModel, callBack, requestAction);

        //请求实体
        RequestInfo requestInfo =
                new RequestInfo(httpRequestModel, requestCode, method, url, params, protocol, contentType);

        //默认拦截器
        RequestInterceptor[] defaultInterceptors = netWorkApiControlCenter.getNetWorkApiChecker().getRequestInterceptor();

        //组装默认拦截器和过滤器
        if (!buildCheckerCase(checkerAction, mResponseListener, requestInfo, defaultInterceptors)) {
            return requestAction;
        }

        //取消上一个相同请求
        cancelCurrentRequest(uniqueTag);

        //URL组装器
        IUrlBuilder mIUrlBuilder = netWorkApiControlCenter.getNetWorkApiBuilder().getUrlBuilder();
        if (null != mIUrlBuilder) {
            url = mIUrlBuilder.getUrl(url, protocol);
        }

        JSONObject paramJson = null;// jsonObject:需要传递的数据，如果是以get方式传递，则为null，如果是以post方式传递，则需要设置数据。

        if (method == Request.Method.POST) {
            if (params != null && !params.isEmpty()) {
                paramJson = new JSONObject(params);
            }
        } else if (method == Request.Method.GET) {
            url = LocalUntil.instance.encodeParameters(url, params);
        }

        //组装请求具体实体且组装头部
        JsonObjectCommonRequest request = new JsonObjectCommonRequest(method, url, mResponseListener, mErrorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                IHeaderBuilder mIHeaderBuilder = NetWorkApiControlCenter.instance.getNetWorkApiBuilder().getHeaderBuilder();
                if (null != mIHeaderBuilder) {
                    return mIHeaderBuilder.getHeaders(contentType, params);
                }
                return super.getHeaders();
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
//        JsonObjectRequest request = new JsonObjectRequest(method, url, paramJson, mResponseListener, mErrorListener) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                IHeaderBuilder mIHeaderBuilder = NetWorkApiControlCenter.instance.getNetWorkApiBuilder().getHeaderBuilder();
//                if (null != mIHeaderBuilder) {
//                    return mIHeaderBuilder.getHeaders(contentType, params);
//                }
//                return super.getHeaders();
//            }
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return params;
//            }
//
//            @Override
//            public String getBodyContentType() {
//                String temp = "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
//                return (method == Request.Method.POST) ? temp : super.getBodyContentType();
//            }
//        };

//        JsonObjectRequest request = new JsonObjectRequest(method, url, paramJson, mResponseListener, mErrorListener) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                IHeaderBuilder mIHeaderBuilder = NetWorkApiControlCenter.instance.getNetWorkApiBuilder().getHeaderBuilder();
//                if (null != mIHeaderBuilder) {
//                    return mIHeaderBuilder.getHeaders(contentType, params);
//                }
//                return super.getHeaders();
//            }
//
////            @Override
////            public String getBodyContentType() {
////                return (getMethod() == Method.POST) ? Constant.ContentType.CONTENT_TYPE_FORM :
////                        super.getBodyContentType();
////            }
////
////            @Override
////            public byte[] getBody() {
////                return super.getBody();
////            }
//        };


//        Response.Listener<JSONObject> rs = new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                Log.e("","");
//            }
//        };
//
//        request = new JsonObjectRequest(method, url, paramJson, rs, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Log.e("", "");
//            }
//        });

        //设置重试，超时策略

        RetryPolicy retryPolicy = (null != httpRequestModel.getRetryPolicy())
                ? httpRequestModel.getRetryPolicy() : NetWorkApiControlCenter.instance.getDefaultRetryPolicy();

        request.setRetryPolicy(retryPolicy);

        //启动请求,tag 表示按照Request.setTag设置好的 tag 取消请求，比如同属于某个 Activity
        netWorkApiControlCenter.getRequestQueueManager().addRequest(request, uniqueTag);

        return requestAction;
    }

    //==============辅助方法================//
    private boolean buildCheckerCase(CheckerAction checkerAction, ResponseListener mResponseListener, RequestInfo requestInfo, RequestInterceptor[] defaultInterceptors) {
        RequestInterceptor[] targetInterceptors = defaultInterceptors;//默认拦截器
        //2.过滤自定义放行拦截器
        List<Integer> list = new LinkedList<>();

        if (null != checkerAction) {
            InterceptAction interceptAction = checkerAction.getInterceptAction();
            mResponseListener.setFilterAction(checkerAction.getFilterAction());//设置放行目标过滤器
            if (null != interceptAction && null != interceptAction.getCustomInterceptors()) {//添加自定义拦截
                //1.先合并默认和自定义拦截器
                targetInterceptors = LocalUntil.instance.concat(defaultInterceptors, interceptAction.getCustomInterceptors());

                for (RequestInterceptor mPassInterceptors : interceptAction.getPassInterceptors()) {
                    list.add(mPassInterceptors.uniqueKey());//存储临时需要放行拦截器
                }
            }
        }

        for (RequestInterceptor mTargetInterceptors : targetInterceptors) {
            if (list.contains(mTargetInterceptors.uniqueKey())) {
                continue;//过滤放行拦截器
            } else {//仅启动非放行拦截器
                if (!onIntercept(requestInfo, mTargetInterceptors)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 检查所有拦截器(默认+自定义过滤器)
     *
     * @param requestInfo
     * @param mTargetInterceptors
     * @return
     */
    private boolean onIntercept(RequestInfo requestInfo, RequestInterceptor mTargetInterceptors) {
        if (mTargetInterceptors.onIntercept(requestInfo, mTargetInterceptors.returnData())) {
            mTargetInterceptors.setData(null);//保证每次使用data model 完毕情况，供下次请求使用
            if (mTargetInterceptors.onCallBackPage() && null != callBack) {
                callBack.onFailure(mTargetInterceptors.returnError(mTargetInterceptors), httpRequestModel);
            }
            return false;
        }
        return true;
    }

    /**
     * 取消当前请求
     */
    public void cancelCurrentRequest(Object tag) {
        if (httpRequestModel != null) {
            // 请求之前先取消请求
            netWorkApiControlCenter.getRequestQueueManager().cancel(null == tag ? (httpRequestModel.getRequestTag()) : tag);
        }
    }

    /**
     * 取消指定请求
     */
    @Override
    public void onCancel() {
        cancelCurrentRequest(httpRequestModel.getRequestTag());
    }
}
