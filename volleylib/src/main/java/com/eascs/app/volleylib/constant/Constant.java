package com.eascs.app.volleylib.constant;

import com.android.volley.DefaultRetryPolicy;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class Constant {

    /**
     * 请求类型
     */
    public enum REQUEST_TYPE{
        HTTP,HTTPS
    }

    /**
     *
     */
    public static final class Header{
        public static final String STATE = "state";
        public static final String MSG = "msg";
        public static final String ACCEPT = "Accept";
        public static final String CONTENT_TYPE = "Content-Type";

    }

    public static final class Response{
        public static final String DATA = "data";
    }

    public static final class RetryPolicy{
        public static final int DEFAULT_TIMEOUT_MS = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
        public static final int DEFAULT_MAX_RETRIES = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;
        public static final float DEFAULT_BACKOFF_MULT = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    }

    public static final class ContentType {
        public static final String CONTENT_TYPE_JSON = "application/json";
        public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
        public static final String[] CONTENT_TYPE_MULTI_MEDIA = {
                "application/pdf", "image/png", ",image/jpg", "image/gif",
                "image/jpeg", "image/bmp", "image/ico", "image/x-png",
                "image/pjpeg", "audio/mpeg", "audio/x-wav", "audio/wav",
                "audio/mp3", "application/x-mpegurl", "video/mp2t"};
    }





}
