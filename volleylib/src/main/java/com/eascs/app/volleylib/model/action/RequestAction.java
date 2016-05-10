package com.eascs.app.volleylib.model.action;


/***
 * @version V1.0
 * @author 请求管理类
 * @desc 描述
 * @time 2016/5/10 0010 10:07
 * @reference
 */
public class RequestAction {

    //请求取消回调接口
    private IRequestAction IRequestAction;

    private Object requestTag;

    private boolean isCancel = false;

    public RequestAction(Object requestTag, IRequestAction IRequestAction){
        this.requestTag = requestTag;
        this.IRequestAction = IRequestAction;
    }

    public void cancel() {
        if(null != IRequestAction){
            isCancel = true;
            IRequestAction.onCancel();
        }
    }

    public boolean isCancel() {
        return isCancel;
    }

    public interface IRequestAction{
        /**
         * 请求取消回调方法
         */
        void onCancel();
    }


}
