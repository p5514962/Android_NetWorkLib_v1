package com.eascs.app.volleylib.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.eascs.app.volleylib.control.NetWorkApiControlCenter;
import com.eascs.app.volleylib.interfaces.builder.IHeaderBuilder;

import org.json.JSONObject;

import java.util.Map;

/***
 * @author KevinHo
 * @version V1.0
 * @desc 描述
 * @time 2016/5/5 0005 13:25
 * @reference
 */
public class JsonObjectCommonRequest extends JsonObjectRequest {

    private Map<String, String> params;
    private String contentType;

    public JsonObjectCommonRequest(int method, String url,
                                   JSONObject jsonRequest,
                                   Response.Listener<JSONObject> listener,
                                   Response.ErrorListener errorListener,
                                   Map<String, String> params,
                                   String contentType) {
        super(method, url, jsonRequest, listener, errorListener);
        this.params = params;
        this.contentType = contentType;
    }


    //=========以下专门覆写父类方法==================//

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        IHeaderBuilder mIHeaderBuilder = NetWorkApiControlCenter.instance.getNetWorkApiBuilder().getHeaderBuilder();
        if (null != mIHeaderBuilder) {
            return mIHeaderBuilder.getHeaders(contentType);
        }
        return super.getHeaders();
    }

}
