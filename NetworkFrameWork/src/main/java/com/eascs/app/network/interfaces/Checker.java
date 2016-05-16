package com.eascs.app.network.interfaces;

/***
 * @version V1.0
 * @author KevinHo
 * @desc 检查器接口
 * @time 2016/5/10 0010 9:30
 * @reference
 */
public interface Checker {
    /**
     * @return 检查器唯一标识
     */
    int uniqueKey();

    String getStopReason();

}
