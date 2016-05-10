package com.eascs.app.volleylib.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.eascs.app.volleylib.interfaces.HttpConnectionCallBack;
import com.eascs.app.volleylib.http.HttpRequestModel;
import com.eascs.app.volleylib.model.action.RequestAction;

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
