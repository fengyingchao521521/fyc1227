package com.bw.myapplication.base;

/*
 *@auther: 封英超
 *@Date: 2019/12/29
 *@Time:19:03
 *@Description:${DESCRIPTION}
 *
 */public abstract class BasePresenter<V> {
     protected V view;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void  actach(V view) {
        this.view = view;
    }

    public void datach() {
        view=null;
    }
}
