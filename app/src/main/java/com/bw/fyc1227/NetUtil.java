package com.bw.fyc1227;

import android.os.Handler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/*
 *@auther: 封英超
 *@Date: 2019/12/27
 *@Time:19:04
 *@Description:${DESCRIPTION}
 *
 */public class NetUtil {
    private static NetUtil netUtil;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    private NetUtil() {
        handler = new Handler();
        //补全
        // TODO: 2019/12/27 alt+enter
        // TODO: 2019/12/27 联网
        // TODO: 2019/12/30 拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //打印级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                //添加
                .addInterceptor(httpLoggingInterceptor)
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
    public void getJsonGet(String httpurl, MyCallback myCallback) {
        // TODO: 2019/12/27 第二步
        Request request = new Request.Builder()
                .get()   //请求方法
                .url(httpurl)
                .build();
        // TODO: 2019/12/27 第三步
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallback.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    String string = response.body().string();
                    //handler切换到主线程
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallback.ongetJson(string);
                        }
                    });
                } else {
                    //handler切换
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallback.onError(new Exception("请求失败"));
                        }
                    });

                }
            }
        });
    }

    //getJsonPost
    public void getJsonPost(String httpurl, Map<String, String> paramsmap, MyCallback myCallback) {
        // TODO: 2019/12/28 创建一个 FormBody.Builder 对象
        FormBody.Builder builder = new FormBody.Builder();

        //遍历for循环  得到 for（key的类型 key的名字  冒号  map集合名字.keySet())
        for (String key : paramsmap.keySet()) {
            //根基key获取value
            String value = paramsmap.get(key);
            //添加
            builder.add(key, value);
        }
        // TODO: 2019/12/28 在循环外  用 builder.build  得到build
        FormBody build = builder.build();


        // TODO: 2019/12/27 第二步
        Request request = new Request.Builder()
                .post(build)   //请求方法
                .url(httpurl)
                .build();
        // TODO: 2019/12/27 第三步
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallback.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    String string = response.body().string();
                    //handler切换到主线程
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallback.ongetJson(string);
                        }
                    });
                } else {
                    //handler切换
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallback.onError(new Exception("请求失败"));
                        }
                    });

                }
            }
        });
    }

    //接口
    public interface MyCallback {
        void ongetJson(String json);

        void onError(Throwable throwable);

    }

}

