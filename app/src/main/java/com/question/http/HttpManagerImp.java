package com.question.http;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.question.cfg.Config;
import com.question.cfg.Methods;
import com.question.utils.MLog;
import com.xiex.quetionbank_android.BaseReque;
import com.xiex.quetionbank_android.BaseRespon;

import org.xutils.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.question.cfg.Config.url;

/**
 * Created by baby on 2016/12/25.
 * <p>
 * 宜步出行，天天速达
 */

public class HttpManagerImp {

    private static HttpManagerImp instance;
    private BaseRespon errorBack = new BaseRespon(1, "错误");

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
        com.xiex.quetionbank_android.BaseReque baseReque = new com.xiex.quetionbank_android.BaseReque(Methods
                .GETALLQUESTION);
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
        HttpUtils.doGetData(url, new HttpUtils.CallBack() {

            @Override
            public void onRequestComplete(final String result) {
                MLog.e(result);
                final BaseRespon baseRespon = JSON.parseObject(result, BaseRespon.class);
                if (callBack != null) {
                    callBack.onGetData(baseRespon.getMsg());
                }
            }

            @Override
            public void onError() {
                if (callBack != null) {
                    callBack.onGetError("错误");
                }
            }
        });
    }
}
