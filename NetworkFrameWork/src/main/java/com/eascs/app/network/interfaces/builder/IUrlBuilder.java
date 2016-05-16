package com.eascs.app.network.interfaces.builder;

import com.eascs.app.network.constant.Constant;

/***
 * @version V1.0
 * @author KevinHo
 * @desc URL 组装器
 * @time 2016/5/5 0005 10:11
 * @reference NetWorkApiBuilder,INetWorkAPI
 */
public interface IUrlBuilder {
    /**
     * 组装目标请求url
     * @param url 目标url
     * @param type 网络请求类型
     * @return 组装后url
     */
    String getUrl(String url, Constant.REQUEST_TYPE type);
}
