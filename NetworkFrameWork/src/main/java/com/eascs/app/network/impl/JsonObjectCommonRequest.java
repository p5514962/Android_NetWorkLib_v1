package com.eascs.app.network.impl;
import android.util.Log;

import com.eascs.app.network.volley.NetworkResponse;
import com.eascs.app.network.volley.ParseError;
import com.eascs.app.network.volley.Request;
import com.eascs.app.network.volley.Response;
import com.eascs.app.network.volley.toolbox.HttpHeaderParser;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

/***
 * @author KevinHo
 * @version V1.0
 * @desc 描述
 * @time 2016/5/5 0005 13:25
 * @reference
 */
public class JsonObjectCommonRequest extends Request<JSONObject> {

    private Response.Listener<JSONObject> listener;
    public JsonObjectCommonRequest(int method, String url,
                                   Response.Listener<JSONObject> listener,
                                   Response.ErrorListener errorListener) {
//        super(method, url, jsonRequest, listener, errorListener);
        super(method, url, errorListener);
        this.listener = listener;
    }

    //=========以下专门覆写父类方法==================//

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        if(null == listener){
            Log.w("network warning","Response.Listener is null");
            return;
        }
        listener.onResponse(response);
    }

}
