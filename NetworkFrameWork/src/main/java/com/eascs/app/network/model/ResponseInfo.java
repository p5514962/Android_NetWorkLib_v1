package com.eascs.app.network.model;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class ResponseInfo<K> {

    private HeaderModel headerModel;
    private K k;
    public ResponseInfo(HeaderModel headerModel,K k){
        this.headerModel = headerModel;
        this.k = k;
    }

    public HeaderModel getHeaderModel() {
        return headerModel;
    }

    public K getResponseData() {
        return k;
    }

}
