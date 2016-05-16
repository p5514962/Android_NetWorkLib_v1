package com.eascs.app.network.control;

import com.eascs.app.network.http.RequestQueueManager;

/***
 * @version V1.0
 * @author KevinHo
 * @desc API 控制中心
 * @time 2016/5/6 0006 9:51
 * @reference NetWorkApiImpl,HttpConnection,ResponseListener
 */
public enum NetWorkApiControlCenter {

    instance;

    NetWorkApiControlCenter(){
        netWorkApiBuilder = new NetWorkApiBuilder();
        netWorkApiChecker = new NetWorkApiChecker();
        requestQueueManager = new RequestQueueManager();
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

}
