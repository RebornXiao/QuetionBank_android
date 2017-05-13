package com.question.ui.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.question.R;
import com.question.data.QuestionContent;
import com.question.ui.adapter.QuestionAdapter;

import java.util.List;

/**
 * Created by baby on 2016/12/25.
 * <p>
 *
 */

public class QuestionTeacherView extends RelativeLayout {
    private Context context;
    private ListView lv_main_question;
    private List<QuestionContent> questionContents;
    private QuestionAdapter adapter;

    public void setQuestionContents(List<QuestionContent> questionContents) {
        this.questionContents = questionContents;
        if (adapter == null) {
            adapter = new QuestionAdapter(context);
        }
        adapter.setQuestionContents(questionContents);
    }

    public QuestionTeacherView(Context context) {
        super(context);
        adapter = new QuestionAdapter(context);
        init(context);
    }

    public QuestionTeacherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public QuestionTeacherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_ques_list, this);
        lv_main_question = (ListView) view.findViewById(R.id.lv_main_question);
        lv_main_question.setAdapter(adapter);
    }
}
