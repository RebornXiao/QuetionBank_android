package com.question.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiex.quetionbank_android.R;
import com.question.data.QuestionContent;

import java.util.List;

/**
 * Created by baby on 2016/12/25.
 * <p>
 * 宜步出行，天天速达
 */

public class QuestionAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<QuestionContent> questionContents;

    public void setQuestionContents(List<QuestionContent> questionContents) {
        this.questionContents = questionContents;
        notifyDataSetChanged();
    }

    public QuestionAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (questionContents != null && questionContents.size() > 0) {
            return questionContents.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return questionContents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder holder = null;
        if (convertView == null) {
            holder = new ViewHoder();
            convertView = inflater.inflate(R.layout.layout_lv, null);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHoder) convertView.getTag();
        }
        if (questionContents != null && questionContents.size() > position) {
            holder.tv.setText(questionContents.get(position).getQuestionText());
        }
        return convertView;
    }

    class ViewHoder {
        TextView tv;
    }
}
