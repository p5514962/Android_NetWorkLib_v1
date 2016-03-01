package com.eascs.app.volleylib.http;

import android.content.Context;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;
import com.eascs.app.volleylib.cache.NetWorkCenter;
import com.eascs.app.volleylib.constant.Constant;
import com.eascs.app.volleylib.impl.ErrorListener;
import com.eascs.app.volleylib.impl.ResponseListener;
import com.eascs.app.volleylib.interfaces.IRequestFilter;
import com.eascs.app.volleylib.untils.NetworkUntil;
import com.eascs.app.volleylib.untils.ParametersUntil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 网络连接相关
 *
 * @author hmily
 */
public class HttpConnection {

    private HttpRequestModel httpRequestModel = new HttpRequestModel();
    private HttpConnectionCallBack callBack;
    public HttpConnection() {
        this(null);
    }

    public HttpConnection(HttpConnectionCallBack callBack) {
        this(callBack, new HttpRequestModel());
    }

    public HttpConnection(HttpConnectionCallBack callBack, HttpRequestModel httpRequestModel) {
        this.callBack = callBack;
        this.httpRequestModel = httpRequestModel;
    }

    //===============分割线======================//
    private List<IRequestFilter> requestFilter = new ArrayList<>();

    public List<IRequestFilter> getRequestFilter() {
        return requestFilter;
    }
    //===============分割线======================//

    public void request(Context context, int requestCode, int method, String url,
                        Map<String, String> params) {
        request(context, requestCode, method, url, params, Constant.REQUEST_TYPE.HTTP,Constant.ContentType.CONTENT_TYPE_FORM);
    }

    public void request(Context context, int requestCode, int method, String url,
                        Map<String, String> params,Constant.REQUEST_TYPE protocol) {
        request(context, requestCode, method, url, params, protocol,Constant.ContentType.CONTENT_TYPE_FORM);
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
    public void request(Context context, int requestCode, int method, String url, final Map<String, String> params,
                        Constant.REQUEST_TYPE protocol,final String contentType) {
        if (null == context) {
            return;
        } else if (!NetworkUntil.isNetworkAvailable(context)) {//无网络状态
            if (callBack != null) {
                callBack.onFailure(new NoConnectionError(), httpRequestModel);
            }
        }

        //===============过滤自定义过滤条件===============//
        for (IRequestFilter mIRequestFilter : requestFilter) {
            if (!mIRequestFilter.onFilter(httpRequestModel)) {
                return;
            }
        }
        cancelCurrentRequest();

        if (null != NetWorkCenter.getIntance().getUrlBuilder()) {
            url = NetWorkCenter.getIntance().getUrlBuilder().getUrl(url, protocol);
        }

//        Logger.i("connection","url:" + url);
        // GET请求需手动添加参数
        if (params != null && method == Request.Method.GET) {
            url = ParametersUntil.encodeParameters(url, params, ParametersUntil.U8_PARAMS_ENCODING);
        }

        ResponseListener mResponseListener = new ResponseListener(requestCode, httpRequestModel, callBack);
        ErrorListener mErrorListener = new ErrorListener(httpRequestModel, callBack);

        JsonObjectRequest request = new JsonObjectRequest(method, url, null, mResponseListener, mErrorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (null != NetWorkCenter.getIntance().getJsonRequestImpl()) {
                    return NetWorkCenter.getIntance().getJsonRequestImpl().getHeaders(contentType);
                }
                return super.getHeaders();
            }
        };


        // tag 表示按照Request.setTag设置好的 tag 取消请求，比如同属于某个 Activity
        if (httpRequestModel != null) {
            request.setTag(httpRequestModel.getRequestTag());
        }

        // 设置超时时间，重试次数
        RetryPolicy mRetryPolicy = new DefaultRetryPolicy(
                Constant.RetryPolicy.DEFAULT_TIMEOUT_MS,
                Constant.RetryPolicy.DEFAULT_MAX_RETRIES,
                Constant.RetryPolicy.DEFAULT_BACKOFF_MULT);

        if (null != httpRequestModel.getRetryPolicy()) {
            mRetryPolicy = httpRequestModel.getRetryPolicy();
        }
        request.setRetryPolicy(mRetryPolicy);
        RequestManager.getInstance().addRequest(request, null);
    }


    /**
     * 取消当前请求
     */
    public void cancelCurrentRequest(){
        if (httpRequestModel != null) {
            Log.e("HttpConnection ", "___________________________request" + httpRequestModel.getTag().toString());
            // 请求之前先取消请求
            RequestManager.getInstance().cancel(httpRequestModel.getRequestTag());
        }
    }
}
