package com.eascs.app.volleylib.interfaces;


import com.android.volley.VolleyError;
import com.eascs.app.volleylib.model.HeaderModel;
import com.eascs.app.volleylib.http.HttpRequestModel;

import org.json.JSONObject;

/***
 * @version V1.0
 * @author KevinHo
 * @desc 请求回调方法（可提供多次主动调用）
 * @time 2016/5/5 0005 10:48
 * @reference
 */
public interface HttpConnectionCallBack {

	/**
	 * 成功后回调
	 * 
	 * @param obj
	 */
	void onSuccess(int requestCode, JSONObject obj, HeaderModel headerModel,
				   HttpRequestModel httpRequestModel); // http获取数据成功

	/**
	 * 失败后回调
	 * 
	 * @param error
	 */
	void onFailure(VolleyError error, HttpRequestModel httpRequestModel); // http连接获取数据失败
}
