package com.eascs.app.volleylib.client.interceptor;

import android.content.Context;
import android.util.Log;
import com.eascs.app.volleylib.client.constant.ConstantClient;
import com.eascs.app.volleylib.interfaces.interceptor.RequestInterceptor;
import com.eascs.app.volleylib.model.RequestInfo;
import com.eascs.app.volleylib.client.until.NetWorkApiUntil;

/***
 * @author KevinHo
 * @version V1.0
 * @desc 网络状态拦截器
 * @time 2016/5/5 0005 13:55
 * @reference
 */
public class NetWorkStateRequestInterceptor implements RequestInterceptor<Integer> {

    private Context context;
    private String reason;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    private int data;

    public NetWorkStateRequestInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public boolean onIntercept(RequestInfo requestInfo, Integer data) {
        if (!NetWorkApiUntil.instance.isNetworkAvailable(context)) {
            reason = "当前无有效网络";
            return false;
        }
        Log.e(data + "", data + "");
        return true;
    }

    @Override
    public int uniqueKey() {
        return ConstantClient.Interceptor.NETWORK_STATE;
    }

    @Override
    public Integer returnData() {
        return getData();
    }

    @Override
    public String getStopReason() {
        return reason;
    }
}
