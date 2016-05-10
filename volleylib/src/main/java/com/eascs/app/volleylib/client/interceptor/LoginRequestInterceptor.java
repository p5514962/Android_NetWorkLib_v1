package com.eascs.app.volleylib.client.interceptor;

import android.content.Context;
import android.text.TextUtils;

import com.eascs.app.volleylib.client.constant.ConstantClient;
import com.eascs.app.volleylib.constant.Constant;
import com.eascs.app.volleylib.interfaces.interceptor.RequestInterceptor;
import com.eascs.app.volleylib.model.RequestInfo;

/***
 * @author KevinHo
 * @version V1.0
 * @desc 登录拦截器
 * @time 2016/5/5 0005 13:55
 * @reference
 */
public class LoginRequestInterceptor implements RequestInterceptor<String> {
    //初始化只能传入Context 上下文
    private Context context;
    private String data;
    private String reason;


    public LoginRequestInterceptor(Context context, String data) {
        this.context = context;
        this.data = data;
    }
    //TODO 可以添加具体app业务,只能根据Context作出动作

    @Override
    public boolean onIntercept(RequestInfo requestInfo, String string) {

        //TODO 1.可以添加具体app业务

        if (TextUtils.isEmpty("")) {//2
            reason = "登录拦截";
            return false;
        }
        return true;
    }

    @Override
    public int uniqueKey() {
        return ConstantClient.Interceptor.LOGIN;
    }

    @Override
    public String returnData() {
        return data;
    }

    @Override
    public String getStopReason() {
        return reason;
    }

}
