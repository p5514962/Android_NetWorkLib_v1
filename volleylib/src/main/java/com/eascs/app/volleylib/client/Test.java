package com.eascs.app.volleylib.client;

import android.app.Activity;

import com.android.volley.VolleyError;
import com.eascs.app.volleylib.factory.NetWorkApiFactory;
import com.eascs.app.volleylib.client.builder.HeaderBuilder;
import com.eascs.app.volleylib.client.builder.UrlBuilder;
import com.eascs.app.volleylib.constant.Constant;
import com.eascs.app.volleylib.http.HttpConnection;
import com.eascs.app.volleylib.http.HttpRequestModel;
import com.eascs.app.volleylib.client.filter.HeaderStateFilter;
import com.eascs.app.volleylib.client.interceptor.LoginRequestInterceptor;
import com.eascs.app.volleylib.client.interceptor.NetWorkStateRequestInterceptor;
import com.eascs.app.volleylib.interfaces.HttpConnectionCallBack;
import com.eascs.app.volleylib.interfaces.filter.ResponseFilter;
import com.eascs.app.volleylib.interfaces.interceptor.RequestInterceptor;
import com.eascs.app.volleylib.model.action.CheckerAction;
import com.eascs.app.volleylib.model.HeaderModel;
import com.eascs.app.volleylib.model.action.InterceptAction;
import com.eascs.app.volleylib.model.action.RequestAction;

import org.json.JSONObject;


/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class Test implements HttpConnectionCallBack {
    public void fake() {
        Activity activity = new Activity();//模拟上下文

        //1.APP 初始化
        NetWorkApiFactory.createNetWorkApi()
                .registerApp(activity)
                .setUrlBuilder(new UrlBuilder())//URl组装器
                .setHeaderBuilder(new HeaderBuilder())//头部组装器
                .setRequestInterceptor(new RequestInterceptor[]{
                        new NetWorkStateRequestInterceptor(activity)})//默认拦截器
                .setResponseFilter(new ResponseFilter[]{new HeaderStateFilter(activity)});//默认过滤器


        //2.模拟请求
        HttpConnection connection = new HttpConnection(this, new HttpRequestModel(""));

        //3.请求拦截器
        InterceptAction interceptAction = new InterceptAction(new RequestInterceptor[]{new LoginRequestInterceptor(activity, "data")});

        CheckerAction checkerAction = new CheckerAction(interceptAction);

        RequestAction mRequestAction = connection.
                request(12, 12, "", null, Constant.REQUEST_TYPE.HTTP, "",
                        checkerAction);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mRequestAction.cancel();//取消请求
    }

    @Override
    public void onSuccess(int requestCode, JSONObject obj, HeaderModel headerModel, HttpRequestModel httpRequestModel) {

    }

    @Override
    public void onFailure(VolleyError error, HttpRequestModel httpRequestModel) {

    }
}