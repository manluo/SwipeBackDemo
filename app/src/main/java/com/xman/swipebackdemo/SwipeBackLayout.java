package com.xman.swipebackdemo;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * 使用这个控件 需要配合activity theme 设置成Translucent_theme
 */
public class SwipeBackLayout extends FrameLayout {
    private ViewDragHelper mHelper;
    private View mView;
    private Activity mActivity;

    private OnSwipeBackListener mOnSwipeBackListener;

    public SwipeBackLayout(Context context) {
        super(context);
        init();
    }

    private void init() {
        mHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                // 默认不扑获 View
                return false;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                // 拖动限制（大于左边界）
                return Math.max(0, left);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                // 拖动距离大于屏幕的一半右移，拖动距离小于屏幕的一半左移
                int left = releasedChild.getLeft();
                if (left > getWidth() / 2) {
                    if (mOnSwipeBackListener != null) {
                        mOnSwipeBackListener.onSwipeBackFinish();
                    }
                } else {
                    mHelper.settleCapturedViewAt(0, 0);
                }
                invalidate();
            }


            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                // 移动子 View
                mHelper.captureChildView(mView, pointerId);
            }
        });
        // 跟踪左边界拖动
        mHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 拦截代理
        return mHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Touch Event 代理
        mHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        // 子 View 需要更新状态
        if (mHelper.continueSettling(true)) {
            invalidate();
        }
    }

    /**
     * 绑定 Activity
     *
     * @param activity 容器 Activity
     */
    public void attachActivity(Activity activity) {
        mActivity = activity;
        ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
        View content = decor.getChildAt(0);
        decor.removeView(content);
        mView = content;
        addView(content);
        decor.addView(this);
    }

    public void setOnSwipeBackListener(OnSwipeBackListener onSwipeBackListener) {
        mOnSwipeBackListener = onSwipeBackListener;
    }

    public interface OnSwipeBackListener {
        /**
         * 返回完成
         */
        void onSwipeBackFinish();
    }

}