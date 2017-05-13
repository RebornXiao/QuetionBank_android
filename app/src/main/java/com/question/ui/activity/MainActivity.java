package com.question.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.question.data.QuestionContent;
import com.question.http.DataCallBack;
import com.question.http.HttpManagerImp;
import com.question.ui.adapter.MainViewPagerAdapter;
import com.question.ui.view.QuestionListView;
import com.question.ui.view.QuestionMyView;
import com.question.ui.view.QuestionTeacherView;
import com.question.ui.view.QuestionTypeView;
import com.question.utils.MLog;

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
    private QuestionListView questionListView;
//    private MyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<View> list = new ArrayList<>();
        questionListView = new QuestionListView(MainActivity.this);
        QuestionTypeView questionTypeView = new QuestionTypeView(MainActivity.this);
        QuestionTeacherView questionTeacherView = new QuestionTeacherView(MainActivity.this);
        QuestionMyView questionMyView = new QuestionMyView(MainActivity.this);
        list.add(questionListView);
        list.add(questionTypeView);
        list.add(questionTeacherView);
        list.add(questionMyView);
        mainViewPagerAdapter = new MainViewPagerAdapter(list);
        vpMainCenter.setAdapter(mainViewPagerAdapter);
        vpMainCenter.setCurrentItem(0);
        vpMainCenter.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
