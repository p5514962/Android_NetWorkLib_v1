package com.eascs.app.volleylib.client;

import com.eascs.app.volleylib.http.HeaderModel;
import com.eascs.app.volleylib.interfaces.IHeaderResponseFilter;

/**
 * @author KevinHo
 * @version V1.0
 * @ClassName:
 * @Description:
 * @email 20497342@qq.com
 * @date
 */
public class HeaderReponseFilter implements IHeaderResponseFilter {
    @Override
    public void onFilter(HeaderModel headerModel) {
        switch (headerModel.getState()) {
            case 0: // 成功
                break;
            case 3800111: //用户被锁定
                break;
            case 3800112://一分钟内获取短信
                break;
            case 3800113://短信达到当天最大值
                break;
            case 3802001: // 没有收货地址
                       /* if (!TextUtils.isEmpty(headerModel.getMsg())) {
                            MyToast.show(ctx, headerModel.getMsg());
                        }*/
                break;
            case -100: // 非法字符，后台数据库保存不了数据
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
//                        if (BuyerApplication.mProgressBar != null) {
//                            BuyerApplication.mProgressBar.dismiss();
//                        }
        }
    }
}
