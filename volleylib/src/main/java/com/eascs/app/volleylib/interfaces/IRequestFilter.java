package com.eascs.app.volleylib.interfaces;

/**
 * 作者：KevinHo on 2016/2/29 0029 14:01
 * 邮箱：20497342@qq.com
 * @param <K>
 */
public interface IRequestFilter<K> {
    boolean onFilter(K k);
}
