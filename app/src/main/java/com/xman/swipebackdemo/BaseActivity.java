package com.xman.swipebackdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by nieyunlong on 17/7/5.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private SwipeBackLayout mSwipeBackLayout;

    private boolean isNeedSwipeBack = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        findById();
        if (isNeedSwipeBack()) {
            initSwipeBackLayout();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.attachActivity(this);
        }
    }

    /**
     * 填充view
     *
     * @return
     */
    public abstract int getContentViewId();

    /**
     * 进行初始化操作 列如 view.findById 是否设置 边缘滑动返回 等
     *
     * @return
     */
    public abstract void findById();

    /**
     * 初始化滑动返回
     */
    private void initSwipeBackLayout() {
        mSwipeBackLayout = new SwipeBackLayout(this);
        mSwipeBackLayout.setOnSwipeBackListener(new SwipeBackLayout.OnSwipeBackListener() {
            @Override
            public void onSwipeBackFinish() {
                mHandlerSwipeBackFinishBefore();
                Toast.makeText(BaseActivity.this, "finish", Toast.LENGTH_LONG).show();
                finish();
                overridePendingTransition(R.anim.push_right_out, R.anim.right_out);
            }
        });
    }


    public boolean isNeedSwipeBack() {
        return isNeedSwipeBack;
    }

    public void setNeedSwipeBack(boolean needSwipeBack) {
        isNeedSwipeBack = needSwipeBack;
    }

    /**
     * 在滑动边缘返回 之前你需要处理的事情 例如使用观察者传递数据
     * 子类只需要复写这个方法就ok
     */
    protected void mHandlerSwipeBackFinishBefore() {

    }
}
