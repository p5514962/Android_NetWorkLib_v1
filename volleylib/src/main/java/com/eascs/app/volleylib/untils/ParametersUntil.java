package com.eascs.app.volleylib.untils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class ParametersUntil {

    public static final String U8_PARAMS_ENCODING = "UTF-8";

    public static String encodeParameters(String url, Map<String, String> params,
                                          String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            encodedParams.append(url).append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(),
                        paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(),
                        paramsEncoding));
                encodedParams.append('&');
            }
            return encodedParams.toString();
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: "
                    + paramsEncoding, uee);
        }
    }

}
