package com.question.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by baby on 2016/12/25.
 * <p>
 * 宜步出行，天天速达
 */

public class BaseActivity extends Activity {
    protected Context mContext ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
