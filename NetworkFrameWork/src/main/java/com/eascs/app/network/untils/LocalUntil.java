package com.eascs.app.network.untils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public enum LocalUntil {

    instance;

    //默认UTF-8 编码方式
    public final String U8_PARAMS_ENCODING = "UTF-8";

    /**
     * GET方法请求拼接参数
     * @param url 目标URL
     * @param params 参数
     * @return
     */
    public String encodeParameters(String url, Map<String, String> params
    ) {
        String paramsEncoding = U8_PARAMS_ENCODING;
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

    /**
     * 两数组合并
     * @param first 数组1
     * @param second 数组2
     * @param <T> 泛型数组类型
     * @return
     */
    public <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

}
