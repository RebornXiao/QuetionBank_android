package com.xiex.quetionbank_android.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by admin on 2016/9/9.
 */
public class MainViewPagerAdapter extends PagerAdapter {
    private List<View> list;
    private int mChildCount = 0;

    public MainViewPagerAdapter(List<View> list) {
        this.list = list;
    }

    public void setList(List<View> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position),0);
        return list.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        if ( mChildCount > 0) {
            mChildCount --;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
      //  return POSITION_NONE;
    }

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }
}
