package com.eascs.app.network.model.exception;

import com.eascs.app.network.interfaces.filter.ResponseFilter;
import com.eascs.app.network.volley.VolleyError;

/**
 * Created by KevinHo on 2016/5/13 0013.
 */
public class FilterError extends VolleyError {

    private ResponseFilter responseFilter;

    public FilterError(ResponseFilter responseFilter){
        this.responseFilter = responseFilter;
    }

    public ResponseFilter getResponseFilter() {
        return responseFilter;
    }


}
