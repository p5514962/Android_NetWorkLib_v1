package com.eascs.app.volleylib.model.action;

import com.eascs.app.volleylib.interfaces.filter.ResponseFilter;

/***
 * @version V1.0
 * @author KevinHo
 * @desc 过滤器中心
 * @time 2016/5/10 0010 10:06
 * @reference
 */
public class FilterAction {

    private ResponseFilter[] responseFilter;

    public FilterAction(ResponseFilter[] responseFilter){
        this.responseFilter = responseFilter;
    }

    public ResponseFilter[] getResponseFilter() {
        return responseFilter;
    }

}
