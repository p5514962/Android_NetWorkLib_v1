package com.eascs.app.volleylib;

import android.content.Context;

import com.eascs.app.volleylib.http.RequestManager;
import com.eascs.app.volleylib.interfaces.IUrlBuilder;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class NetWorkApiSDK {

    private static NetWorkApiSDK instance;

    private NetWorkApiSDK(){
    }

    /**
     * 初始化
     * @return instance
     */
    public static NetWorkApiSDK initialize(Context context){
        if(null == instance){
            synchronized (NetWorkApiSDK.class){
                if(null == instance){
                    instance = new NetWorkApiSDK();
                    RequestManager.init(context);
                }
            }
        }
        return instance;
    }

}
