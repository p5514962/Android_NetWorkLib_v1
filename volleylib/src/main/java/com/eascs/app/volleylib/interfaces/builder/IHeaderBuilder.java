package com.eascs.app.volleylib.interfaces.builder;

import java.util.Map;

/*** 
 * @version V1.0
 * @author KevinHo
 * @desc 网络请求头部组装器
 * @time 2016/5/5 0005 10:16
 * @reference 
 */
public interface IHeaderBuilder {
    /**
     * 返回头部键值对集合
     * @param contentType 网络请求类型
     * @return 返回头部键值对集合
     */
    Map<String, String> getHeaders(String contentType);
}