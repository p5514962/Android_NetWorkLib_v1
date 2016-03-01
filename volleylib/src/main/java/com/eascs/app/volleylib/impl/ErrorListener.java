package com.eascs.app.volleylib.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.eascs.app.volleylib.http.HttpConnectionCallBack;
import com.eascs.app.volleylib.http.HttpRequestModel;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class ErrorListener implements Response.ErrorListener {

    HttpRequestModel httpRequestModel;
    HttpConnectionCallBack httpConnectionCallBack;

    public ErrorListener(HttpRequestModel httpRequestModel,
                         HttpConnectionCallBack httpConnectionCallBack) {
        this.httpConnectionCallBack = httpConnectionCallBack;
        this.httpRequestModel = httpRequestModel;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        if (httpConnectionCallBack != null) {
            httpConnectionCallBack.onFailure(volleyError, httpRequestModel);
        }
    }
}
