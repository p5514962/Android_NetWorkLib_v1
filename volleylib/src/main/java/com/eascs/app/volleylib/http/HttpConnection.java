package com.eascs.app.volleylib.http;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.eascs.app.volleylib.control.NetWorkApiControlCenter;
import com.eascs.app.volleylib.constant.Constant;
import com.eascs.app.volleylib.impl.ErrorListener;
import com.eascs.app.volleylib.impl.JsonObjectCommonRequest;
import com.eascs.app.volleylib.impl.ResponseListener;
import com.eascs.app.volleylib.interfaces.HttpConnectionCallBack;
import com.eascs.app.volleylib.interfaces.builder.IUrlBuilder;
import com.eascs.app.volleylib.interfaces.interceptor.RequestInterceptor;
import com.eascs.app.volleylib.model.action.CheckerAction;
import com.eascs.app.volleylib.model.action.InterceptAction;
import com.eascs.app.volleylib.model.action.RequestAction;
import com.eascs.app.volleylib.model.exception.InterceptorError;
import com.eascs.app.volleylib.model.RequestInfo;
import com.eascs.app.volleylib.untils.LocalUntil;

import org.json.JSONObject;

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
    public RequestAction request(int requestCode, int method, String url, final Map<String, String> params,
                                 Constant.REQUEST_TYPE protocol, final String contentType, CheckerAction checkerAction) {

        Object uniqueTag = (httpRequestModel == null || null == httpRequestModel.getRequestTag()) ? url : httpRequestModel.getRequestTag();

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
        JsonObjectRequest request = new JsonObjectCommonRequest(method, url, paramJson, mResponseListener, mErrorListener, params, contentType);

        //设置重试，超时策略
        request.setRetryPolicy(httpRequestModel.getRetryPolicy());

        //启动请求,tag 表示按照Request.setTag设置好的 tag 取消请求，比如同属于某个 Activity
        netWorkApiControlCenter.getRequestQueueManager().addRequest(request, uniqueTag);

        return requestAction;
    }

    //==============辅助方法================//
    private boolean buildCheckerCase(CheckerAction checkerAction, ResponseListener mResponseListener, RequestInfo requestInfo, RequestInterceptor[] defaultInterceptors) {
        RequestInterceptor[] targetInterceptors = defaultInterceptors;//默认拦截器

        if (null != checkerAction) {
            InterceptAction interceptAction = checkerAction.getInterceptAction();
            mResponseListener.setFilterAction(checkerAction.getFilterAction());
            if (null != interceptAction && null != interceptAction.getInterceptors()) {//添加自定义拦截
                targetInterceptors = LocalUntil.instance.concat(defaultInterceptors, interceptAction.getInterceptors());
            }
        }
        if (null != targetInterceptors) {//开始拦截
            for (RequestInterceptor mRequestInterceptor : targetInterceptors) {
                if (!mRequestInterceptor.onIntercept(requestInfo, mRequestInterceptor.returnData())) {
                    callBack.onFailure(new InterceptorError(mRequestInterceptor), httpRequestModel);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 取消当前请求
     */
    public void cancelCurrentRequest(Object tag) {
        if (httpRequestModel != null) {
            Log.e("HttpConnection ", "___________________________request" + httpRequestModel.getExtrasData().toString());
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
