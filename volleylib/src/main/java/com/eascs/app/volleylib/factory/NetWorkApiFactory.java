package com.eascs.app.volleylib.factory;

import com.eascs.app.volleylib.impl.NetWorkApiImpl;
import com.eascs.app.volleylib.interfaces.INetWorkAPI;

/*** 
 * @version V1.0
 * @author KevinHo
 * @desc 提供给APP 初始化时候，生产NetWorkAPI
 * @time 2016/5/5 0005 10:09
 * @reference 具体场景
 */
public class NetWorkApiFactory {

    private NetWorkApiFactory(){
    }

    /**
     * 初始化
     * @return INetWorkAPI instance
     */
    public static INetWorkAPI createNetWorkApi(){
        return new NetWorkApiImpl();
    }

}
