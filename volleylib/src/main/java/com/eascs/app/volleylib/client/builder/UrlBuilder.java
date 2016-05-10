package com.eascs.app.volleylib.client.builder;

import android.util.Log;

import com.eascs.app.volleylib.constant.Constant.REQUEST_TYPE;
import com.eascs.app.volleylib.interfaces.builder.IUrlBuilder;

/***
 * @version V1.0
 * @author KevinHo
 * @desc 网络请求前组装URL
 * @time 2016/5/5 0005 10:15
 * @reference
 */
public class UrlBuilder implements IUrlBuilder {
    @Override
    public String getUrl(String uri, REQUEST_TYPE requestType) {
        StringBuilder url = new StringBuilder();
        switch (requestType){
            case HTTP:
                url.append("http");
                break;

            case HTTPS:
                url.append("https");
                break;

        }
        Log.i("connection", "url:" + url);
        return url.toString();
    }
}
