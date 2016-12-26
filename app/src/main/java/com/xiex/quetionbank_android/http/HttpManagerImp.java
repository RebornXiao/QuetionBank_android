package com.xiex.quetionbank_android.http;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import org.xutils.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.xiex.quetionbank_android.Config.url;

/**
 * Created by baby on 2016/12/25.
 * <p>
 * 宜步出行，天天速达
 */

public class HttpManagerImp {

    private static HttpManagerImp instance;
    private com.xiex.quetionbank_android.BaseRespon errorBack = new com.xiex.quetionbank_android.BaseRespon(1, "错误");
    private Handler handler = new Handler(Looper.getMainLooper());

    private HttpManagerImp() {
    }

    public static HttpManagerImp getInstance() {
        if (instance == null) {
            synchronized (HttpManagerImp.class) {
                instance = new HttpManagerImp();
            }
        }
        return instance;
    }

    private ExecutorService es = Executors.newFixedThreadPool(2);

    @NonNull
    private RequestParams setRequest() {
        com.xiex.quetionbank_android.BaseReque baseReque = new com.xiex.quetionbank_android.BaseReque(com.xiex.quetionbank_android.Methods.GETALLQUESTION);
        String body = JSON.toJSONString(baseReque);
        com.xiex.quetionbank_android.MLog.e("请求参数=" + url + body);
        String url = null;
//        url = Config.url + body;
        body = "?content=" + body;
        try {
            url = url + URLEncoder.encode(body, com.xiex.quetionbank_android.Config.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        com.xiex.quetionbank_android.MLog.e("请求参数=" + url);
        RequestParams params = new RequestParams(url);
//        params.addParameter("content",body);
        params.setCacheMaxAge(2000);
        params.setAsJsonContent(true);
        return params;
    }

    public void getData(Object t, DataCallBack callBack) {
        com.xiex.quetionbank_android.BaseReque baseReque = new com.xiex.quetionbank_android.BaseReque(com.xiex.quetionbank_android.Methods.GETALLQUESTION);
        baseReque.setDetail(t);
        String body = JSON.toJSONString(baseReque);
        String url = com.xiex.quetionbank_android.Config.url + body;
        com.xiex.quetionbank_android.MLog.e("请求参数=" + url);
        doNetJob(url, callBack);
    }

    private void doNetJob(final String url, final DataCallBack callBack) {
        es.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpUtils.doGetData(url, new HttpUtils.CallBack() {

                        @Override
                        public void onRequestComplete(final String result) {
                            com.xiex.quetionbank_android.MLog.e(result);
                            final com.xiex.quetionbank_android.BaseRespon baseRespon = JSON.parseObject(result, com.xiex.quetionbank_android.BaseRespon.class);
                            if (callBack != null) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        callBack.onGetData(baseRespon.getMsg());
                                    }
                                });

                            }
                        }

                        @Override
                        public void onError() {
                            if (callBack != null) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        callBack.onGetError("错误");
                                    }
                                });
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
