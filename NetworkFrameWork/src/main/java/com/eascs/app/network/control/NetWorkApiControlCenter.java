package com.eascs.app.network.control;

import com.eascs.app.network.http.RequestQueueManager;
import com.eascs.app.network.impl.ApiRetryPolicy;
import com.eascs.app.network.volley.DefaultRetryPolicy;
import com.eascs.app.network.volley.RetryPolicy;

/***
 * @version V1.0
 * @author KevinHo
 * @desc API 控制中心
 * @time 2016/5/6 0006 9:51
 * @reference NetWorkApiImpl,HttpConnection,ResponseListener
 */
public enum NetWorkApiControlCenter {

    instance;

    private RetryPolicy retryPolicy;

    NetWorkApiControlCenter(){
        netWorkApiBuilder = new NetWorkApiBuilder();
        netWorkApiChecker = new NetWorkApiChecker();
        requestQueueManager = new RequestQueueManager();
        retryPolicy = new ApiRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 0);
    }

    private NetWorkApiBuilder netWorkApiBuilder;//组装器

    private NetWorkApiChecker netWorkApiChecker;//检查器

    private RequestQueueManager requestQueueManager;//操作请求队列

    public NetWorkApiBuilder getNetWorkApiBuilder() {
        return netWorkApiBuilder;
    }

    public NetWorkApiChecker getNetWorkApiChecker() {
        return netWorkApiChecker;
    }

    public RequestQueueManager getRequestQueueManager() {
        return requestQueueManager;
    }

    public RetryPolicy getDefaultRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }


}
