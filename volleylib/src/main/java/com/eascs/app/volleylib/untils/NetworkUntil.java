package com.eascs.app.volleylib.untils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class NetworkUntil {

    private static NetworkUntil intance;

    public NetworkUntil getIntance(){

        if(null == intance){
            synchronized (NetworkUntil.class){
                if(null == intance){
                    intance = new NetworkUntil();
                }
            }
        }
        return intance;
    }

    /**
     * 检测网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
