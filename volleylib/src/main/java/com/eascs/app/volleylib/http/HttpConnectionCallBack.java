package com.eascs.app.volleylib.http;


import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * 网络回调接口
 * 
 * @author Ammy
 *
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
