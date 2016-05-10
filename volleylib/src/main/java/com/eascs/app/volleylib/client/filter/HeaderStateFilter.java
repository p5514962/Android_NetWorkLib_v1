package com.eascs.app.volleylib.client.filter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.eascs.app.volleylib.client.constant.ConstantClient;
import com.eascs.app.volleylib.interfaces.filter.ResponseFilter;
import com.eascs.app.volleylib.model.HeaderModel;
import com.eascs.app.volleylib.model.ResponseInfo;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName: HeaderStateFilter
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class HeaderStateFilter implements ResponseFilter {

    private Context context;

    private ResponseInfo responseInfo;

    private String reason;

    public HeaderStateFilter() {
    }

    public HeaderStateFilter(Context context) {
        this.context = context;
    }


    @Override
    public boolean onFilter(ResponseInfo responseInfo) {

        boolean result = true;
        HeaderModel headerModel = responseInfo.getHeaderModel();

        if (null == headerModel) {
            return result;
        }

        switch (headerModel.getState()) {
            case 0: // 成功
                break;
            case 3800111: //用户被锁定
                break;
            case 3800112://一分钟内获取短信
                break;
            case 3800113://短信达到当天最大值
                reason = "短信达到当天最大值";
                break;
            case 3802001: // 没有收货地址
                if (!TextUtils.isEmpty(headerModel.getMsg())) {
                    Toast.makeText(context, headerModel.getMsg(), Toast.LENGTH_LONG).show();
                }
                reason = "没有收货地址";
                break;
            case -100: // 非法字符，后台数据库保存不了数据
                reason = "非法字符，后台数据库保存不了数据";
                break;
            case 3809201:
            case 3800114:
//                        if (!TextUtils.isEmpty(headerModel.getMsg())) {
//                            MyToast.show(ctx, headerModel.getMsg());
//                        }
//                        SettingFragment.doLogout(ctx);
//                        if (ctx instanceof BaseActivity) {
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    ((BaseActivity) ctx).startMainFragment(IndexFragment.class);
//                                }
//                            }, 1500);
//
//                        }
                break;
            default:
        }

        if(0 != headerModel.getState()){
            result = false;
            reason = headerModel.getMsg();
        }

        return result;
    }


    @Override
    public int uniqueKey() {
        return ConstantClient.Filter.HEADER_STATE;
    }

    @Override
    public String getStopReason() {
        return reason;
    }

}
