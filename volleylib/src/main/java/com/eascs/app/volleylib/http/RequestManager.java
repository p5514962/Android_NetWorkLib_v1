package com.eascs.app.volleylib.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by leo on 2015/7/31.
 */
public class RequestManager {

    private static RequestQueue mRequestQueue;

    private RequestManager() {
        // no instances
    }

    public static RequestManager init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);

        return null;
    }

    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    public static void addRequest(Request<?> request, Object tag) {
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
    public static void cancel(Object tag) {
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
        if(tag == null) {
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
    public static void cancelAll(Context ctx) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(ctx);
        }
    }

    public static void cancel() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {

                @Override
                public boolean apply(Request<?> request) {
                    return false;
                }
            });
        }
    }






    public static void clear(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
