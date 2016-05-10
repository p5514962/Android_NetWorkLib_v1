package com.eascs.app.volleylib.model;

import com.eascs.app.volleylib.constant.Constant;
import com.eascs.app.volleylib.http.HttpRequestModel;

import java.util.Map;

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
