package com.bw.myapplication.presenter;

import com.bw.myapplication.base.BasePresenter;
import com.bw.myapplication.contract.IContract;
import com.bw.myapplication.model.HomModel;
import com.bw.myapplication.model.bean.JavaBean;

/*
 *@auther: 封英超
 *@Date: 2019/12/29
 *@Time:19:06
 *@Description:${DESCRIPTION}
 *
 */public class HomPresenter extends BasePresenter<IContract.IView>implements IContract.IPresenter {

    private HomModel homModel;

    @Override
    protected void initModel() {
        homModel = new HomModel();
    }

    @Override
    public void getHomeData() {
        homModel.getHomeData(new IContract.IModel.IModelCallBack() {
            @Override
            public void onSuccess(JavaBean javaBean) {
                view.onSuccess(javaBean);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onFailure(throwable);
            }
        });

    }
}
