package com.eascs.app.volleylib.impl;

import android.text.TextUtils;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.eascs.app.volleylib.control.NetWorkApiControlCenter;
import com.eascs.app.volleylib.constant.Constant;
import com.eascs.app.volleylib.model.action.FilterAction;
import com.eascs.app.volleylib.model.HeaderModel;
import com.eascs.app.volleylib.interfaces.HttpConnectionCallBack;
import com.eascs.app.volleylib.http.HttpRequestModel;
import com.eascs.app.volleylib.interfaces.filter.ResponseFilter;
import com.eascs.app.volleylib.model.ResponseInfo;
import com.eascs.app.volleylib.model.action.RequestAction;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;

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
    private FilterAction filterAction;
    private HttpRequestModel httpRequestModel;
    private HttpConnectionCallBack httpConnectionCallBack;
    private NetWorkApiControlCenter mNetWorkApiControlCenter;
    private RequestAction requestAction;

    public ResponseListener(int requestCode, HttpRequestModel httpRequestModel, HttpConnectionCallBack httpConnectionCallBack,RequestAction requestAction) {
        this.requestCode = requestCode;
        this.httpRequestModel = httpRequestModel;
        this.requestAction = requestAction;
        this.httpConnectionCallBack = httpConnectionCallBack;
        mNetWorkApiControlCenter = NetWorkApiControlCenter.instance;
    }

    public void setFilterAction(FilterAction filterAction) {
        this.filterAction = filterAction;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        try {
            if (requestAction.isCancel()) {
                return;
            }

            //第一部:组装响应结果头部
            int state = jsonObject.getInt(Constant.Header.STATE);
            String msg = jsonObject.getString(Constant.Header.MSG);
            HeaderModel headerModel = new HeaderModel(state, msg);

            ResponseInfo responseInfo = new ResponseInfo(headerModel, jsonObject);
            ResponseFilter[] responseFilters = mNetWorkApiControlCenter.getNetWorkApiChecker().getResponseFilter();


            //第二部：响应结果默认过滤器
            if (null == responseFilters) {
                buildSuccessCase(headerModel, jsonObject);//第三部获取消息体且回调
                return;
            }

            //第三步：回调具体请求需要过滤器
            for (int i = 0; i < responseFilters.length; i++) {
                ResponseFilter mResponseFilter = responseFilters[i];
                if (null != mResponseFilter && !mResponseFilter.onFilter(responseInfo)) {
                    if (null != filterAction && Arrays.asList(filterAction).contains(mResponseFilter.uniqueKey())) {
                        buildSuccessCase(headerModel, jsonObject);//回调具体页面,携带感兴趣数据
                        return;
                    } else return;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            httpConnectionCallBack.onFailure(new ParseError(), httpRequestModel);
        } catch (Exception e) {
            e.printStackTrace();
            httpConnectionCallBack.onFailure(new VolleyError(), httpRequestModel);
        }
    }


    /**
     * @param headerModel
     * @param jsonObject
     * @throws JSONException
     */
    private void buildSuccessCase(HeaderModel headerModel, JSONObject jsonObject) throws JSONException {
        if (httpConnectionCallBack != null) {
            String data = jsonObject.optString(Constant.Response.DATA);
            boolean isNotEmpty = (!TextUtils.isEmpty(data) && !TextUtils.equals(data, "null"));
            httpConnectionCallBack.onSuccess(requestCode, isNotEmpty ? new JSONObject(data) : new JSONObject(), headerModel,
                    httpRequestModel);
        }
    }


}
