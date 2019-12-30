package com.bw.myapplication.model;

import com.bw.myapplication.contract.IContract;
import com.bw.myapplication.model.bean.JavaBean;
import com.bw.myapplication.util.NetUtil;
import com.google.gson.Gson;

/*
 *@auther: 封英超
 *@Date: 2019/12/29
 *@Time:19:06
 *@Description:${DESCRIPTION}
 *
 */public class HomModel implements IContract.IModel {


    @Override
    public void getHomeData(final IModelCallBack iModelCallBack) {
        final String httpurl = "http://blog.zhaoliang5156.cn/api/shop/fulishe1.json";
        NetUtil.getInstance().getJsonGet(httpurl, new NetUtil.MyCallBack() {
            @Override
            public void ongetJson(String json) {
                JavaBean javaBean = new Gson().fromJson(json, JavaBean.class);
                iModelCallBack.onSuccess(javaBean);

            }

            @Override
            public void Error(Throwable throwable) {
                iModelCallBack.onFailure(throwable);
            }
        });


    }
}
