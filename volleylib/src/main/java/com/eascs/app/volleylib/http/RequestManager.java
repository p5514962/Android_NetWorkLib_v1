package com.eascs.app.volleylib.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by KevinHo on 2016/2/29.
 */
public class RequestManager {

    private RequestQueue mRequestQueue;
    private static RequestManager instance;

    private RequestManager() {//
        // no instances
    }

    private RequestManager(Context context) {//私有构造函数只提供内部使用
        mRequestQueue = Volley.newRequestQueue(context);
    }

    /**
     * 对外app 启动初始化方法
     * @param context 上下文
     * @return
     */
    public static RequestManager init(Context context) {
        if (context != null) {
            if (instance == null) {
                instance = new RequestManager(context);
            } else {
                throw new IllegalArgumentException("Context must be set");
            }
        }
        return instance;

    }

    /**
     * 对外RequestManager 实例
     * @return RequestManager
     */
    public static RequestManager getInstance() {
        return instance;
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

    /**
     * 根据tag取消请求
     *
     * @param tag
     */
//    public static void cancel(Object tag,OnRequestCancelListener onRequestCancelListener) {
//        if (mRequestQueue != null) {
//            mRequestQueue.cancelAll(tag,onRequestCancelListener);
//        }
//    }
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

    /**
     * 取消这个队列里所有的请求
     *
     * @param ctx
     */
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

    public void clear(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
