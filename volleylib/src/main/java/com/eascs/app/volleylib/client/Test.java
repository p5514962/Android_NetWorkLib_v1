package com.eascs.app.volleylib.client;

import android.text.TextUtils;

import com.eascs.app.volleylib.cache.NetWorkCenter;
import com.eascs.app.volleylib.constant.Constant;
import com.eascs.app.volleylib.interfaces.IJsonRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class Test {
    public void fake() {
        NetWorkCenter.getIntance().setUrlBuilder(new UrlBuilder());
        NetWorkCenter.getIntance().setJsonRequestImpl(new IJsonRequest() {
            @Override
            public Map<String, String> getHeaders(String contentType) {
                Map<String, String> headers = new HashMap<>();
                if (!TextUtils.isEmpty(contentType) && contentType.equals(Constant.ContentType.CONTENT_TYPE_JSON)) {
                    headers.put(Constant.Header.ACCEPT, Constant.ContentType.CONTENT_TYPE_JSON);
                    headers.put(Constant.Header.CONTENT_TYPE, Constant.ContentType.CONTENT_TYPE_JSON + "; charset=UTF-8");
                }
//                headers.put("appname", Platform.UAAPPNAME + Platform.getVersionName(ctx));
//                headers.put("OS", Platform.OS);
//                headers.put("DVUA", Platform.DVUA);
//                headers.put("NT", Platform.getNetWorkTypeName(ctx));
//                String token = BuyerApplication.getToken();
//                if (TextUtils.isEmpty(token)) {
//                    token = SharePreferenceUtils.getString(ctx, "token");
//                    BuyerApplication.token = token;
//                }
//                headers.put("Token", token);
//                headers.put("DVID", Platform.getEquipmentId(ctx));
//                headers.put("ChannelId", Platform.getChannelId());
//                Log.e("headers", headers.toString());
                return headers;
            }
        });

    }
}
