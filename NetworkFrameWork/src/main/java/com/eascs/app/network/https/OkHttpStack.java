package com.eascs.app.network.https;

import android.app.Activity;
import android.text.TextUtils;

import com.eascs.app.network.volley.AuthFailureError;
import com.eascs.app.network.volley.Request;
import com.eascs.app.network.volley.toolbox.HttpStack;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class OkHttpStack implements HttpStack {
    private OkHttpClient mClient = null;
    public boolean isRelease;
    private BksInfo mBksInfo;

    public OkHttpStack(BksInfo mBksInfo) {
        this.mBksInfo = mBksInfo;
    }

    public void setRelease(boolean release) {
        isRelease = release;
    }

    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders)
            throws IOException, AuthFailureError {
        OkHttpClient client = null;
        int timeoutMs = request.getTimeoutMs();
        int retryCount = request.getRetryPolicy().getCurrentRetryCount();
        client = new OkHttpClient.Builder()
                .connectTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                .readTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                .writeTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(false)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(HttpsUtils.getSslSocketFactory(
                        null, mBksInfo.getInputStream(),
                        mBksInfo.getBksPwd()
                )).build();

        okhttp3.Request.Builder okHttpRequestBuilder = new okhttp3.Request.Builder();
        URL parsedUrl = new URL(request.getUrl());
//        String protocol = parsedUrl.getProtocol();
//        if (!isRelease) {
//            if ("https".equals(protocol)) {
//                HTTPSTrustManager.allowAllSSL();
//            }
//        }
        Map<String, String> headers = request.getHeaders();

        for (final String name : headers.keySet()) {
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(headers.get(name))) {
                okHttpRequestBuilder.addHeader(name, headers.get(name));
            }
        }

        for (final String name : additionalHeaders.keySet()) {
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(additionalHeaders.get(name))) {
                okHttpRequestBuilder.addHeader(name, additionalHeaders.get(name));
            }
        }

        setConnectionParametersForRequest(okHttpRequestBuilder, request);

        okHttpRequestBuilder.url(request.getUrl());

        okhttp3.Request okHttpRequest = okHttpRequestBuilder.build();
        Call okHttpCall = client.newCall(okHttpRequest);
        Response okHttpResponse = okHttpCall.execute();

        StatusLine responseStatus = new BasicStatusLine
                (
                        parseProtocol(okHttpResponse.protocol()),
                        okHttpResponse.code(),
                        okHttpResponse.message()
                );

        BasicHttpResponse response = new BasicHttpResponse(responseStatus);
        response.setEntity(entityFromOkHttpResponse(okHttpResponse));

        Headers responseHeaders = okHttpResponse.headers();

        for (int i = 0, len = responseHeaders.size(); i < len; i++) {
            final String name = responseHeaders.name(i), value = responseHeaders.value(i);

            if (name != null) {
                response.addHeader(new BasicHeader(name, value));
            }
        }

        return response;
    }

    private static HttpEntity entityFromOkHttpResponse(Response response) throws IOException {
        BasicHttpEntity entity = new BasicHttpEntity();
        ResponseBody body = response.body();

        entity.setContent(body.byteStream());
        entity.setContentLength(body.contentLength());
        entity.setContentEncoding(response.header("Content-Encoding"));

        if (body.contentType() != null) {
            entity.setContentType(body.contentType().type());
        }
        return entity;
    }

    @SuppressWarnings("deprecation")
    private static void setConnectionParametersForRequest
            (okhttp3.Request.Builder builder, Request<?> request)
            throws IOException, AuthFailureError {
        switch (request.getMethod()) {
            case Request.Method.DEPRECATED_GET_OR_POST:
                // Ensure backwards compatibility.
                // Volley assumes a request with a null body is a GET.
                byte[] postBody = request.getPostBody();

                if (postBody != null) {
                    builder.post(RequestBody.create
                            (MediaType.parse(request.getPostBodyContentType()), postBody));
                }
                break;

            case Request.Method.GET:
                builder.get();
                break;

            case Request.Method.DELETE:
                builder.delete();
                break;

            case Request.Method.POST:
                builder.post(createRequestBody(request));
                break;

            case Request.Method.PUT:
                builder.put(createRequestBody(request));
                break;

            case Request.Method.HEAD:
                builder.head();
                break;

            case Request.Method.OPTIONS:
                builder.method("OPTIONS", null);
                break;

            case Request.Method.TRACE:
                builder.method("TRACE", null);
                break;

            case Request.Method.PATCH:
                builder.patch(createRequestBody(request));
                break;

            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static ProtocolVersion parseProtocol(final Protocol protocol) {
        switch (protocol) {
            case HTTP_1_0:
                return new ProtocolVersion("HTTP", 1, 0);
            case HTTP_1_1:
                return new ProtocolVersion("HTTP", 1, 1);
            case SPDY_3:
                return new ProtocolVersion("SPDY", 3, 1);
            case HTTP_2:
                return new ProtocolVersion("HTTP", 2, 0);
        }

        throw new IllegalAccessError("Unkwown protocol");
    }

    private static RequestBody createRequestBody(Request request) throws AuthFailureError {
        final byte[] body = request.getBody();
        if(body == null) {
            FormBody formBody = (new okhttp3.FormBody.Builder()).build();
            return formBody;
        } else {
            return RequestBody.create(MediaType.parse(request.getBodyContentType()), body);
        }
    }

}