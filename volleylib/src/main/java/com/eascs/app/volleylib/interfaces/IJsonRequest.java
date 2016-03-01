package com.eascs.app.volleylib.interfaces;

import java.util.Map;

/**
 * 作者：KevinHo on 2016/3/1 0001 14:03
 * 邮箱：20497342@qq.com
 */
public interface IJsonRequest {
    Map<String, String> getHeaders(String contentType);
}
