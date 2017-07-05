package com.xman.swipebackdemo;

import android.widget.Toast;

/**
 * Created by nieyunlong on 17/7/5.
 */

public class SecondActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_second;
    }

    @Override
    public void findById() {

    }

    @Override
    protected void mHandlerSwipeBackFinishBefore() {
        super.mHandlerSwipeBackFinishBefore();
        Toast.makeText(SecondActivity.this,"我是在返回之前调用的", Toast.LENGTH_LONG).show();
    }
}
