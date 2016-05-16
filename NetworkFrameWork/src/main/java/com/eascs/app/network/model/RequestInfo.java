package com.eascs.app.network.model;

import com.eascs.app.network.constant.Constant;
import com.eascs.app.network.http.HttpRequestModel;

import java.util.Map;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class RequestInfo {
    private HttpRequestModel httpRequestModel;
    private int requestCode ;
    private int method;
    private String url;
    private Map<String, String> params;
    private Constant.REQUEST_TYPE requestType;
    private String contentType;

    public RequestInfo(HttpRequestModel httpRequestModel,int requestCode,int method,String url,Map<String, String> params,Constant.REQUEST_TYPE requestType,String contentType){
        this.httpRequestModel = httpRequestModel;
        this.requestCode = requestCode;
        this.method = method;
        this.url = url;
        this.params = params;
        this.requestType = requestType;
        this.contentType = contentType;
    }


}
