package com.eascs.app.volleylib.http;


import android.app.Activity;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpStack;

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
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * An {@link com.android.volley.toolbox.HttpStack HttpStack} implementation which
 * uses OkHttp as its transport.
 */
public class OkHttpStack implements HttpStack {
    private final OkHttpClient mClient;

    public OkHttpStack(OkHttpClient client) {
        this.mClient = client;
    }

    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders)
            throws IOException, AuthFailureError {

        int timeoutMs = request.getTimeoutMs();
        int retryCount = request.getRetryPolicy().getCurrentRetryCount();
        boolean isRetry = (retryCount > 0) ? true : false;
        //TODO  raw中的keyStore文件名 Context传入
        InputStream inputStream = new Activity().getResources().openRawResource(0);
        SSLContext sslContext = sslContextForTrustedCertificates(inputStream);

        OkHttpClient client = null;
        client = new OkHttpClient.Builder()
                .connectTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                .readTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                .writeTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                .sslSocketFactory(sslContext.getSocketFactory())
                .retryOnConnectionFailure(isRetry)
                .build();


        okhttp3.Request.Builder okHttpRequestBuilder = new okhttp3.Request.Builder();
        URL parsedUrl = new URL(request.getUrl());

        Map<String, String> headers = request.getHeaders();

        for (final String name : headers.keySet()) {
            okHttpRequestBuilder.addHeader(name, headers.get(name));
        }

        for (final String name : additionalHeaders.keySet()) {
            okHttpRequestBuilder.addHeader(name, additionalHeaders.get(name));
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
        if (body == null) return null;

        return RequestBody.create(MediaType.parse(request.getBodyContentType()), body);
    }


//    private SSLSocketFactory createSSLSocketFactory(Context context, int res, String password)
//            throws CertificateException,
//            NoSuchAlgorithmException,
//            IOException,
//            KeyStoreException,
//            KeyManagementException {
//        InputStream inputStream = context.getResources().openRawResource(res);
//        KeyStore keyStore = KeyStore.getInstance("BKS");
//        keyStore.load(inputStream, password.toCharArray());
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//        tmf.init(keyStore);
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, tmf.getTrustManagers(), new SecureRandom());
//        return sslContext.getSocketFactory();
//    }

    public SSLContext sslContextForTrustedCertificates(InputStream in) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
            if (certificates.isEmpty()) {
                throw new IllegalArgumentException("expected non-empty set of trusted certificates");
            }


            char[] password = "123@eascs.com".toCharArray();
            KeyStore keyStore = newEmptyKeyStore(password);
            int index = 0;
            for (Certificate certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificate);
            }

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                    KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, password);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(),
                    new SecureRandom());
            return sslContext;
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = null;
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
