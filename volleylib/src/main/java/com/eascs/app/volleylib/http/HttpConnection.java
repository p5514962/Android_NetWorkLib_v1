package com.eascs.app.volleylib.http;


/**
 * 网络连接相关
 *
 * @author hmily
 */
public class HttpConnection {

//    public static Map<Object, NetProgressBar> progressBars = new HashMap<Object, NetProgressBar>();
    private HttpRequestModel httpRequestModel = new HttpRequestModel();
    private HttpConnectionCallBack callBack;
//    private ICancelOutSide mICancelOutSide;

    public HttpConnection() {
        this(null);
    }

    public HttpConnection(HttpConnectionCallBack callBack) {
        this(callBack, new HttpRequestModel());
    }

    public HttpConnection(HttpConnectionCallBack callBack, HttpRequestModel httpRequestModel) {
        this.callBack = callBack;
        this.httpRequestModel = httpRequestModel;
    }


    /**
     * 默认请求带loading
     *
     * @param ctx
     * @param requestCode
     * @param method
     * @param uri
     * @param params
     */
//    public void request(Context ctx, int requestCode, int method, String uri,
//                        Map<String, String> params) {
//        request(ctx, requestCode, method, uri, params, Constants.HTTP);
//    }
//
//    public void request(Context ctx, int requestCode, int method, String uri,
//                        Map<String, String> params, String protocol) {
//
//        request(ctx, requestCode, method, uri, params, protocol, false);
//    }
//
//    public void request(Context ctx, int requestCode, int method, String uri,
//                        Map<String, String> params, String protocol, boolean needToken) {
//        request(ctx, requestCode, method, uri, params, protocol, needToken, Constants.Http.ContentType.CONTENT_TYPE_FORM);
//    }
//
//
//    /**
//     * @param ctx
//     * @param requestCode
//     * @param method
//     * @param protocol
//     * @param uri
//     * @param params
//     */
//    public void request(final Context ctx, final int requestCode, int method, String uri, final Map<String, String> params,
//                        String protocol, final boolean needToken,
//                        final String contentType) {
//
//
//        if (null == ctx) {
//            return;
//        }
//
//        if (httpRequestModel.isShowProgress()) {
//            showLoading(ctx, httpRequestModel.getTxtMsg(), httpRequestModel.getRequestTag());
//        }
//
//        // 网络未连接
//        if (!Platform.isNetworkAvailable(ctx)) {
//            //  MyToast.show(ctx, ctx.getString(R.string.network_not_connected));
//            if (callBack != null) {
//                if (httpRequestModel != null) {
//
//                    dismissLoading(httpRequestModel.getRequestTag());
//
//                    if (httpRequestModel.isCanceled()) {
//                        return;
//                    }
//                }
////                callBack.onFailure(new NoConnectionError(), httpRequestModel);
//            }
////            return;
//        }
//        if (needToken) {
//            if (TextUtils.isEmpty(BuyerApplication.token) && TextUtils.isEmpty(SharePreferenceUtils.getString(ctx, "token"))) {
//                if (ctx instanceof BaseActivity) {
//                    if (httpRequestModel != null) {
//                        dismissLoading(httpRequestModel.getRequestTag());
//                        if (httpRequestModel.isCanceled()) {
//                            return;
//                        }
//                    }
//                    ((BaseActivity) ctx).startContentFragment(LoginFragment.class);
//                    return;
//                }
//            }
//        }
//        if (httpRequestModel != null) {
//            Log.e("HttpConnection ", "___________________________request" + httpRequestModel.getTag().toString());
//            // 请求之前先取消请求
//            RequestManager.cancel(httpRequestModel.getRequestTag());
//        }
//
//        String url = getUrl(uri, protocol);
//        Logger.i("connection","url:" + url);
//        // GET请求需手动添加参数
//        if (params != null && method == Request.Method.GET) {
//            url = encodeParameters(url, params, "UTF-8");
//        }
//
//        final JsonObjectRequest request = new JsonObjectRequest(ctx, method, url, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                EventBus.getDefault().post(new DismissLoading());
//                Log.e("HttpConnection ", "___________________________response" + httpRequestModel.getTag().toString());
//                // 移除loadingHtt
//                if (httpRequestModel != null) {
//
//                    dismissLoading(httpRequestModel.getRequestTag());
//
//                    if (httpRequestModel.isCanceled()) {
//                        return;
//                    }
//                }
//                if (jsonObject == null) {
//                    return;
//                }
//
//                HeaderModel headerModel = new HeaderModel();
//
//                try {
//                    int state = jsonObject.getInt("state");
//                    String msg = jsonObject.getString("msg");
//
//                    headerModel.setState(state);
//                    headerModel.setMsg(msg);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                switch (headerModel.getState()) {
//                    case 0: // 成功
//                        break;
//                    case 3800111: //用户被锁定
//                        break;
//                    case 3800112://一分钟内获取短信
//                        break;
//                    case 3800113://短信达到当天最大值
//                        break;
//                    case 3802001: // 没有收货地址
//                       /* if (!TextUtils.isEmpty(headerModel.getMsg())) {
//                            MyToast.show(ctx, headerModel.getMsg());
//                        }*/
//                        break;
//                    case -100: // 非法字符，后台数据库保存不了数据
//                        break;
//                    case 3809201:
//                    case 3800114:
//                        if (!TextUtils.isEmpty(headerModel.getMsg())) {
//                            MyToast.show(ctx, headerModel.getMsg());
//                        }
//                        SettingFragment.doLogout(ctx);
//                        if (ctx instanceof BaseActivity) {
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    ((BaseActivity) ctx).startMainFragment(IndexFragment.class);
//                                }
//                            }, 1500);
//
//                        }
//                        break;
//                    default:
//                        if (BuyerApplication.mProgressBar != null) {
//                            BuyerApplication.mProgressBar.dismiss();
//                        }
//                }
//
//                /**
//                 * 获取消息体
//                 */
//                try {
//                    if (callBack != null) {
//                        String data = jsonObject.optString("data");
//                        if (!TextUtils.isEmpty(data) && !data.equals("null")) {
//                            callBack.onSuccess(requestCode, new JSONObject(data), headerModel,
//                                    httpRequestModel);
//                        } else {
//                            callBack.onSuccess(requestCode, new JSONObject(), headerModel,
//                                    httpRequestModel);
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Log.e("HttpConnection ", "___________________________error");
//                if (httpRequestModel != null) {
//                    dismissLoading(httpRequestModel.getRequestTag());
//
//                    if (httpRequestModel.isCanceled()) {
//                        return;
//                    }
//                }
//                if (callBack != null) {
//                    callBack.onFailure(volleyError, httpRequestModel);
//                }
//            }
//
//        }
//        ) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<String, String>();
//
//                if (!TextUtils.isEmpty(contentType) && contentType.equals(Constants.Http.ContentType.CONTENT_TYPE_JSON)) {
//                    headers.put("Accept", Constants.Http.ContentType.CONTENT_TYPE_JSON);
//                    headers.put("Content-Type", Constants.Http.ContentType.CONTENT_TYPE_JSON + "; charset=UTF-8");
//                }
//                headers.put("appname", Platform.UAAPPNAME + Platform.getVersionName(ctx));
//                headers.put("OS", Platform.OS);
//                headers.put("DVUA", Platform.DVUA);
//                headers.put("NT", Platform.getNetWorkTypeName(ctx));
//                String token = BuyerApplication.getToken();
//                if (TextUtils.isEmpty(token)) {
//                    token = SharePreferenceUtils.getString(ctx, "token");
//                    BuyerApplication.token = token;
//                }
//                headers.put("Token", token);
//                headers.put("DVID", Platform.getEquipmentId(ctx));
//                headers.put("ChannelId", Platform.getChannelId());
//                Log.e("headers", headers.toString());
//                return headers;
//            }
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return params;
//            }
//        };
//
//        // tag 表示按照Request.setTag设置好的 tag 取消请求，比如同属于某个 Activity
//        if (httpRequestModel != null) {
//            request.setTag(httpRequestModel.getRequestTag());
//        }
//
//        // 设置超时时间，重试次数
//        request.setRetryPolicy(new DefaultRetryPolicy(
//                        DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
//        );
//        RequestManager.addRequest(request, null);
//    }
//
//    public HttpConnectionCallBack getCallBack() {
//        return callBack;
//    }
//
//    public void setCallBack(HttpConnectionCallBack callBack) {
//        this.callBack = callBack;
//    }
//
//    public interface ICancelOutSide {
//        void onCancelOutSide(DialogInterface dialog);
//    }
//
//    public void registerICancelOutSide(ICancelOutSide mICancelOutSide) {
//        this.mICancelOutSide = mICancelOutSide;
//    }
//
//
//    /**
//     * 显示加载进度条
//     *
//     * @param msg
//     */
//    protected void showLoading(final Context ctx, String msg, Object tag) {
//        if (progressBars.containsKey(tag)) {
//            progressBars.clear();
//        }
//
//        NetProgressBar progressBar = new NetProgressBar(ctx, msg, tag);
//        progressBar.registerICancelOutSide(new NetProgressBar.ICancelOutSide() {
//            @Override
//            public void onCancelOutSide(DialogInterface dialog) {
//                if (null != mICancelOutSide)
//                    mICancelOutSide.onCancelOutSide(dialog);
//            }
//        });
//
//        progressBar.show(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    if (httpRequestModel != null) {
//                      //  RequestManager.cancel(httpRequestModel.getRequestTag());
//                      //  httpRequestModel.setCanceled(true);
//                        if (null != mICancelOutSide)
//                            mICancelOutSide.onCancelOutSide(dialog);
//                    }
//                }
//                return false;
//            }
//        }, true);
//
//        progressBars.put(tag, progressBar);
//    }
//
//    /**
//     * 显示加载进度条
//     */
//    protected void dismissLoading(Object tag) {
//        NetProgressBar progressBar = progressBars.get(tag);
//        if (progressBar != null) {
//            if (progressBar.isShowing()) {
//                progressBar.dismiss();
//                progressBars.clear();
//            }
//        }
//    }
//
//
//    /**
//     * 获取url
//     *
//     * @param uri
//     * @return
//     */
//    public static String getUrl(String uri, String type) {
//        initUrl();
//        StringBuilder sb = new StringBuilder(Constants.HOST_HTTP);
//
//        if (!TextUtils.isEmpty(type)) {
//            if (type.equals(Constants.HTTPS)) {
//                sb = new StringBuilder(Constants.HOST_HTTPS);
//            }
//        }
//        sb./*append(Constants.API_VERSION).*/append(uri);
//        String url = sb.toString();
//        return url;
//    }
//
//    public static void initUrl() {
//        switch (BuyerApplication.ENV) {
//            case Constants.Http.Environment.Command.NORMAL_ENV: // 正式环境
//
//                break;
//            case Constants.Http.Environment.Command.TEST_ENV: // 测试环境
//                Constants.HOST_HTTP = "http://183.62.215.52:8005";
//                Constants.HOST_HTTPS = "https://183.62.215.52:443";
//                Constants.HOST_UPLOAD = "http://183.62.215.52:8007/upload/file/multiUpload";
//                Constants.HOST_WAP = "http://ywdtest.eascs.com";
//                Constants.HOST_DOWNLOAD = "http://ywdtest.eascs.com/appdownload/app.html";
//                break;
//
//            case Constants.Http.Environment.Command.DEV_HOST: // 开发环境
//                Constants.HOST_HTTP = "http://172.29.7.162:28080";
//                Constants.HOST_HTTPS = "https://172.29.7.162:28443";
//                Constants.HOST_UPLOAD = "http://172.29.7.162:19080/upload/file/multiUpload";
//                Constants.HOST_WAP = "http://wxshare.eascs.com";
//                Constants.HOST_DOWNLOAD = "http://wxshare.eascs.com/eawx/appdownload/app.html";
//                break;
//
//            case Constants.Http.Environment.Command.PREPARED_ENV: // 预发布环境
//                Constants.HOST_HTTP = "http://bapi.380star.com";
//                Constants.HOST_HTTPS = "https://bapi.380star.com";
//                Constants.HOST_UPLOAD = "http://up.380star.com/upload/file/multiUpload";
//                Constants.HOST_WAP = "http://wap.380star.com";
//                Constants.HOST_DOWNLOAD = "http://m.380star.com/appdownload/app4buyer.html";
//
//                break;
//
//            case Constants.Http.Environment.Command.PREPARED_DOMAIN_ENV://压力测试环境
//                Constants.HOST_HTTP = "http://bapi4beta.380star.com";
//                Constants.HOST_HTTPS = "https://bapi4beta.380star.com";
//                Constants.HOST_UPLOAD = "http://172.30.3.119:80/upload/file/multiUpload";
//                Constants.HOST_WAP = "http://wap4beta.380star.com";
//                Constants.HOST_DOWNLOAD = "http://m.380star.com/appdownload/app4buyer.html";
//
//                break;
//            default:
//                break;
//        }
//    }
//
//    private String encodeParameters(String url, Map<String, String> params,
//                                    String paramsEncoding) {
//        StringBuilder encodedParams = new StringBuilder();
//        try {
//            encodedParams.append(url).append("?");
//            for (Map.Entry<String, String> entry : params.entrySet()) {
//                encodedParams.append(URLEncoder.encode(entry.getKey(),
//                        paramsEncoding));
//                encodedParams.append('=');
//                encodedParams.append(URLEncoder.encode(entry.getValue(),
//                        paramsEncoding));
//                encodedParams.append('&');
//            }
//            return encodedParams.toString();
//        } catch (UnsupportedEncodingException uee) {
//            throw new RuntimeException("Encoding not supported: "
//                    + paramsEncoding, uee);
//        }
//    }


}
