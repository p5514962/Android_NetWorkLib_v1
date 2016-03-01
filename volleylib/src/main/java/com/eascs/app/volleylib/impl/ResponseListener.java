package com.eascs.app.volleylib.impl;

import android.text.TextUtils;
import com.android.volley.Response;
import com.eascs.app.volleylib.constant.Constant;
import com.eascs.app.volleylib.http.HeaderModel;
import com.eascs.app.volleylib.http.HttpConnectionCallBack;
import com.eascs.app.volleylib.http.HttpRequestModel;
import com.eascs.app.volleylib.interfaces.IHeaderResponseFilter;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class ResponseListener implements Response.Listener<JSONObject> {

    private int requestCode;
    private HttpRequestModel httpRequestModel;
    private HttpConnectionCallBack httpConnectionCallBack;
    private IHeaderResponseFilter mIHeaderResponseFilter;

    @Override
    public void onResponse(JSONObject jsonObject) {
        HeaderModel headerModel = new HeaderModel();
        try {
            int state = jsonObject.getInt(Constant.Header.STATE);
            String msg = jsonObject.getString(Constant.Header.MSG);
            headerModel.setState(state);
            headerModel.setMsg(msg);

            if(null != mIHeaderResponseFilter){
                mIHeaderResponseFilter.onFilter(headerModel);
            }

            //获取消息体
            if (httpConnectionCallBack != null) {
                String data = jsonObject.optString(Constant.Response.DATA);
                boolean isNotEmpty = (!TextUtils.isEmpty(data) && !TextUtils.equals(data,"null"));
                httpConnectionCallBack.onSuccess(requestCode, isNotEmpty?new JSONObject(data):new JSONObject(), headerModel,
                        httpRequestModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ResponseListener(int requestCode,HttpRequestModel httpRequestModel,HttpConnectionCallBack httpConnectionCallBack,IHeaderResponseFilter mIHeaderResponseFilter){
        this.requestCode = requestCode;
        this.httpRequestModel = httpRequestModel;
        this.httpConnectionCallBack = httpConnectionCallBack;
        this.mIHeaderResponseFilter = mIHeaderResponseFilter;
    }

    public ResponseListener(int requestCode,HttpRequestModel httpRequestModel,HttpConnectionCallBack httpConnectionCallBack){
        this(requestCode, httpRequestModel, httpConnectionCallBack,null);
    }

}
