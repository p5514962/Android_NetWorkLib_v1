package com.eascs.app.volleylib.interfaces;

import com.eascs.app.volleylib.constant.Constant;

/**
 * 作者：KevinHo on 2016/2/29 0029 11:06
 * 邮箱：20497342@qq.com
 */
public interface IUrlBuilder {
    String getUrl(String uri, Constant.REQUEST_TYPE type);
}
