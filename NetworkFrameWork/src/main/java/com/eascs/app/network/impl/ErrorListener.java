package com.eascs.app.network.impl;

import com.eascs.app.network.interfaces.HttpConnectionCallBack;
import com.eascs.app.network.http.HttpRequestModel;
import com.eascs.app.network.model.action.RequestAction;
import com.eascs.app.network.volley.Response;
import com.eascs.app.network.volley.VolleyError;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class ErrorListener implements Response.ErrorListener {

    private HttpRequestModel httpRequestModel;
    private RequestAction requestAction;
    HttpConnectionCallBack httpConnectionCallBack;

    public ErrorListener(HttpRequestModel httpRequestModel,
                         HttpConnectionCallBack httpConnectionCallBack,RequestAction requestAction) {
        this.httpConnectionCallBack = httpConnectionCallBack;
        this.httpRequestModel = httpRequestModel;
        this.requestAction = requestAction;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        if (httpConnectionCallBack != null && !requestAction.isCancel()) {
            httpConnectionCallBack.onFailure(volleyError, httpRequestModel);
        }
    }
}
