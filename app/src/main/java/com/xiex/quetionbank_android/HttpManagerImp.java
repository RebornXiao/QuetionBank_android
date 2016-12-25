package com.xiex.quetionbank_android;

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
    private BaseRespon errorBack = new BaseRespon(1, "错误");
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
        BaseReque baseReque = new BaseReque(Methods.GETALLQUESTION);
        String body = JSON.toJSONString(baseReque);
        MLog.e("请求参数=" + url + body);
        String url = null;
//        url = Config.url + body;
        body = "?content=" + body;
        try {
            url = url + URLEncoder.encode(body, Config.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MLog.e("请求参数=" + url);
        RequestParams params = new RequestParams(url);
//        params.addParameter("content",body);
        params.setCacheMaxAge(2000);
        params.setAsJsonContent(true);
        return params;
    }

    public void getData(Object t, DataCallBack callBack) {
        BaseReque baseReque = new BaseReque(Methods.GETALLQUESTION);
        baseReque.setDetail(t);
        String body = JSON.toJSONString(baseReque);
        String url = Config.url + body;
        MLog.e("请求参数=" + url);
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
                            MLog.e(result);
                            final BaseRespon baseRespon = JSON.parseObject(result, BaseRespon.class);
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
