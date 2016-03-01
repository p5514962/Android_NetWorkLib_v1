package com.eascs.app.volleylib.interfaces;

import com.eascs.app.volleylib.http.HeaderModel;

/**
 * 作者：KevinHo on 2016/3/1 0001 10:51
 * 邮箱：20497342@qq.com
 */
public interface IHeaderResponseFilter {
    void onFilter(HeaderModel headerModel);

}
