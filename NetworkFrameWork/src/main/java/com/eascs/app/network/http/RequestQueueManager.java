package com.eascs.app.network.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by KevinHo on 2016/2/29.
 */
public class RequestQueueManager {

    private RequestQueue mRequestQueue;
//    private static RequestQueueManager instance;

    public RequestQueueManager() {//
        // no instances
    }

    /**
     * 对外app 启动初始化方法
     * @param context 上下文
     * @return
     */
    public void initRequestQueue(Context context) {
        if (context != null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }else{
            throw new IllegalArgumentException("Context must be set");
        }
    }

    /**
     * 获取请求队列实例
     * @return 返回请求队列实例
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    public void addRequest(Request<?> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    /**
     * 根据tag取消请求
     *
     * @param tag
     */
    public void cancel(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public static void cancelAll(final Object tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        } else {
            cancelAll(new RequestQueue.RequestFilter() {
                public boolean apply(Request<?> request) {
                    return request.getTag() == tag;
                }
            });
        }
    }


    public void cancelAll(Context ctx) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(ctx);
        }
    }

    public void cancel() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {

                @Override
                public boolean apply(Request<?> request) {
                    return false;
                }
            });
        }
    }

    /**
     * 取消这个队列里所有的请求
     *
     * @param tag
     */
    public void clear(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
