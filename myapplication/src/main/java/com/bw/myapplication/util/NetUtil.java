package com.bw.myapplication.util;

import android.os.Handler;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 *@auther: 封英超
 *@Date: 2019/12/29
 *@Time:19:06
 *@Description:${DESCRIPTION}
 *
 */public class NetUtil {
    private static NetUtil netUtil;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    private NetUtil() {
        //handler联网
        handler = new Handler();

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public static NetUtil getInstance() {
        if (netUtil == null) {
            synchronized (NetUtil.class) {
                if (netUtil == null) {
                    netUtil = new NetUtil();
                }
            }
        }
        return netUtil;
    }

    //getJsonGet
    public void getJsonGet(String httpurl, final MyCallBack myCallBack) {
        //get请求
        Request request = new Request.Builder()
                .get()
                .url(httpurl)
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.Error(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {



                if (response != null && response.isSuccessful()) {
                    final String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.ongetJson(string);
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.Error(new Exception("请求失败"));
                        }
                    });
                }
            }
        });
    }

    //接口
    public interface MyCallBack {
        void ongetJson(String json);

        void Error(Throwable throwable);
    }

}
