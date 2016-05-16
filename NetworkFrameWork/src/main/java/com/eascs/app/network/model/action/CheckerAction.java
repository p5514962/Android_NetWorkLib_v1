package com.eascs.app.network.model.action;

/***
 * @version V1.0
 * @author KevinHo
 * @desc 检查器中心
 * @time 2016/5/10 0010 10:05
 * @reference
 */
public class CheckerAction {

    private InterceptAction interceptAction;//拦截器
    private FilterAction filterAction;//过滤器

    public CheckerAction(InterceptAction interceptAction){
        this(interceptAction,null);
    }


    public CheckerAction(InterceptAction interceptAction, FilterAction filterAction){
        this.interceptAction = interceptAction;
        this.filterAction = filterAction;
    }

    public InterceptAction getInterceptAction() {
        return interceptAction;
    }

    public FilterAction getFilterAction() {
        return filterAction;
    }

}
