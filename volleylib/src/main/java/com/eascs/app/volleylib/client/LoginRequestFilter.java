package com.eascs.app.volleylib.client;

import android.text.TextUtils;
import com.eascs.app.volleylib.http.HttpRequestModel;
import com.eascs.app.volleylib.interfaces.IRequestFilter;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class LoginRequestFilter implements IRequestFilter<HttpRequestModel> {

    @Override
    public boolean onFilter(HttpRequestModel httpRequestModel) {
        if (TextUtils.isEmpty("")) {
            return false;
        }
        return true;
    }
}
