# SwipeBackDemo
边缘滑动返回使用ViewDragHelper
------- 
增加了回调 以及子类可控性 是否需要边缘滑动返回 <br>
public void setNeedSwipeBack(boolean needSwipeBack) {
        isNeedSwipeBack = needSwipeBack;
    }
</br>

增加接口<br>
 public interface OnSwipeBackListener {
        /**
         * 返回完成
         */
        void onSwipeBackFinish();
    }
    
增加子类滑动返回时 子类的扩展方法
 /**
  * 在滑动边缘返回 之前你需要处理的事情 例如使用观察者传递数据
  * 子类只需要复写这个方法就ok
  * BaseActivity.class
  */
  protected void mHandlerSwipeBackFinishBefore() {
 }
 
 
非常感谢chrnie作者的无私奉献 http://www.jianshu.com/p/69c6beaf98bd

