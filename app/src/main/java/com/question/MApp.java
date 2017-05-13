package com.question;

import android.app.Application;

import org.xutils.x;

/**
 * Created by baby on 2017/5/9.
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
