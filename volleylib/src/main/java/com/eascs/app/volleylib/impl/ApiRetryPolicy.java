package com.eascs.app.volleylib.impl;

import com.android.volley.DefaultRetryPolicy;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class ApiRetryPolicy extends DefaultRetryPolicy {

    public ApiRetryPolicy(){//默认关闭重试机制
        super(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, /*DefaultRetryPolicy.DEFAULT_MAX_RETRIES*/0, 0/*DefaultRetryPolicy.DEFAULT_BACKOFF_MULT*/);
    }

    public ApiRetryPolicy(int timeoutMs,int retriesMax,float mBackoffMultiplier){
        super(timeoutMs,retriesMax,mBackoffMultiplier);
    }

    public ApiRetryPolicy(int timeoutMs,int retriesMax){
        super(timeoutMs,retriesMax,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public ApiRetryPolicy(int timeoutMs){
        super(timeoutMs, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

}
