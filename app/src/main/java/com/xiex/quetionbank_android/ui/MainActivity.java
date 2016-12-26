package com.xiex.quetionbank_android.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.xiex.quetionbank_android.MLog;
import com.xiex.quetionbank_android.R;
import com.xiex.quetionbank_android.data.QuestionContent;
import com.xiex.quetionbank_android.http.DataCallBack;
import com.xiex.quetionbank_android.http.HttpManagerImp;
import com.xiex.quetionbank_android.ui.adapter.MainViewPagerAdapter;
import com.xiex.quetionbank_android.ui.view.QuestionListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.rl_main_top)
    RelativeLayout rlMainTop;
    @Bind(R.id.cb_main_1)
    CheckBox cbMain1;
    @Bind(R.id.cb_main_2)
    CheckBox cbMain2;
    @Bind(R.id.cb_main_3)
    CheckBox cbMain3;
    @Bind(R.id.cb_main_4)
    CheckBox cbMain4;
    @Bind(R.id.rg_main_bottom)
    RadioGroup rgMainBottom;
    @Bind(R.id.vp_main_center)
    ViewPager vpMainCenter;
    @Bind(R.id.rl_activity_main)
    RelativeLayout rlActivityMain;
    private ListView lv;
    private List<QuestionContent> questionContents;
    private MainViewPagerAdapter mainViewPagerAdapter;
//    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<View> list = new ArrayList<>();
        final QuestionListView questionListView = new QuestionListView(MainActivity.this);
        list.add(questionListView);
        list.add(questionListView);
        list.add(questionListView);
        list.add(questionListView);
        mainViewPagerAdapter = new MainViewPagerAdapter(list);
        vpMainCenter.setAdapter(mainViewPagerAdapter);
        HttpManagerImp.getInstance().getData(null, new DataCallBack() {

            @Override
            public void onGetData(String data) {
                questionContents = JSON.parseArray(data, QuestionContent.class);
                questionListView.setQuestionContents(questionContents);
            }

            @Override
            public void onGetError(String data) {
                MLog.e("错误：" + data.toString());
            }

        });
    }

}
