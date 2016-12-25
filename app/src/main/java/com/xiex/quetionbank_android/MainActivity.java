package com.xiex.quetionbank_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xiex.quetionbank_android.UI.BaseActivity;
import com.xiex.quetionbank_android.respon.QuestionContent;

import java.util.List;

public class MainActivity extends BaseActivity {

    private ListView lv;
    private List<QuestionContent> questionContents;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        adapter = new MyAdapter();
        lv.setAdapter(adapter);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManagerImp.getInstance().getData(null, new DataCallBack() {

                    @Override
                    public void onGetData(String data) {
                        questionContents = JSON.parseArray(data, QuestionContent.class);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onGetError(String data) {
                        MLog.e("错误：" + data.toString());
                    }

                });
            }
        });
    }

    class MyAdapter extends BaseAdapter {


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
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_lv, null);
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
    }

    class ViewHoder {
        TextView tv;
    }
}
