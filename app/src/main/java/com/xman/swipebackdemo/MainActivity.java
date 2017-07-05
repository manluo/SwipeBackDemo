package com.xman.swipebackdemo;

import android.content.Intent;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findById() {
        setNeedSwipeBack(false);
    }


    public void skip_second(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

}
