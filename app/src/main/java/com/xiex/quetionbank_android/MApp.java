package com.xiex.quetionbank_android;

import android.app.Application;

import org.xutils.x;


/**
 * Created by baby on 2016/12/25.
 * <p>
 * 宜步出行，天天速达
 */

public class MApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);

    }
}
