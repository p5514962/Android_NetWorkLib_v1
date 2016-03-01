package com.eascs.app.volleylib.client;

import com.eascs.app.volleylib.constant.Constant.REQUEST_TYPE;
import com.eascs.app.volleylib.interfaces.IUrlBuilder;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description: 模拟在请求api前的url封装
 * @email 20497342@qq.com
 * @date
 */
public class UrlBuilder implements IUrlBuilder {
    @Override
    public String getUrl(String uri, REQUEST_TYPE requestType) {
        switch (requestType){
            case HTTP:
                break;

            case HTTPS:
                break;

        }

        return null;
    }
}
