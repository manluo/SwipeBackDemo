package com.xman.swipebackdemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nieyunlong on 17/7/5.
 */

public class SecondActivity extends BaseActivity {

    ViewPager mVp;

    private List<View> mViewList;

    private int[] images = {R.mipmap.ic_one, R.mipmap.ic_two};

    private MyPageAdapter adapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_second;
    }

    @Override
    protected void findById() {
        mViewList = new ArrayList<>();
        mVp = (ViewPager) findViewById(R.id.vp);
        adapter = new MyPageAdapter(mViewList);
        mVp.setAdapter(adapter);
        initData();
    }

    private void initData() {
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(mParams);
            imageView.setBackgroundResource(images[i]);
            mViewList.add(imageView);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void mHandlerSwipeBackFinishBefore() {
        super.mHandlerSwipeBackFinishBefore();
        Toast.makeText(SecondActivity.this, "我是在返回之前调用的", Toast.LENGTH_LONG).show();
    }

    private static class MyPageAdapter extends PagerAdapter {

        private List<View> list;

        public MyPageAdapter(List<View> list) {
            this.list = list;
        }


        @Override
        public int getCount() {
            return list != null ? list.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = list.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }
}
