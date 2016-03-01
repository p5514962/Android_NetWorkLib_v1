package com.eascs.app.volleylib.cache;

import com.android.volley.toolbox.JsonObjectRequest;
import com.eascs.app.volleylib.interfaces.IJsonRequest;
import com.eascs.app.volleylib.interfaces.IUrlBuilder;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class NetWorkCenter {

    private static NetWorkCenter intance;

    private IUrlBuilder urlBuilder;

    private IJsonRequest jsonRequest;

    public static NetWorkCenter getIntance(){
        if(null == intance){
            synchronized (NetWorkCenter.class){
                if(null == intance){
                    intance = new NetWorkCenter();
                }
            }
        }
        return intance;
    }

    public IUrlBuilder getUrlBuilder() {
        return urlBuilder;
    }

    public void setUrlBuilder(IUrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    public IJsonRequest getJsonRequestImpl() {
        return jsonRequest;
    }

    public void setJsonRequestImpl(IJsonRequest jsonRequest) {
        this.jsonRequest = jsonRequest;
    }

}
