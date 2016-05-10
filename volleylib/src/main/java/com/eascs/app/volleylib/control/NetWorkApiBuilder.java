package com.eascs.app.volleylib.control;

import com.eascs.app.volleylib.interfaces.builder.IHeaderBuilder;
import com.eascs.app.volleylib.interfaces.builder.IUrlBuilder;
/*** 
 * @version V1.0
 * @author KevinHo
 * @desc 网络框架请求Builder中心
 * @time 2016/5/5 0005 10:01
 * @reference INetWorkAPI
 */
public class NetWorkApiBuilder {

    private IUrlBuilder urlBuilder;

    private IHeaderBuilder mIHeaderBuilder;


    //===============url 组装器===============//
    public IUrlBuilder getUrlBuilder() {
        return urlBuilder;
    }

    public void setUrlBuilder(IUrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    //===============请求头部 组装器===============//
    public IHeaderBuilder getHeaderBuilder() {
        return mIHeaderBuilder;
    }

    public void setHeaderBuilder(IHeaderBuilder mIHeaderBuilder) {
        this.mIHeaderBuilder = mIHeaderBuilder;
    }

}
