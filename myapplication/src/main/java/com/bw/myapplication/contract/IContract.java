package com.bw.myapplication.contract;

import com.bw.myapplication.model.bean.JavaBean;

/*
 *@auther: 封英超
 *@Date: 2019/12/29
 *@Time:19:05
 *@Description:${DESCRIPTION}
 *
 */public interface IContract {
    interface IView {
        //商品
        void onSuccess(JavaBean javaBean);

        void onFailure(Throwable throwable);
    }

    interface IPresenter {
        //商品
        void getHomeData();

    }

    interface IModel {
        void getHomeData(IModelCallBack iModelCallBack);

        interface IModelCallBack {
            //商品
            void onSuccess(JavaBean javaBean);

            void onFailure(Throwable throwable);
        }

    }


}
