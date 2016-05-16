package com.eascs.app.network.interfaces.filter;

import com.eascs.app.network.interfaces.Checker;
import com.eascs.app.network.model.ResponseInfo;

/***
 * @version V1.0
 * @author KevinHo
 * @desc 过滤器接口
 * @time 2016/5/10 0010 9:31
 * @reference
 */
public interface ResponseFilter extends Checker {

    /**
     * 拦截方法
     * @param responseInfo
     * @return
     */
    boolean onFilter(ResponseInfo responseInfo);

    String getStopReasonKey();

}
