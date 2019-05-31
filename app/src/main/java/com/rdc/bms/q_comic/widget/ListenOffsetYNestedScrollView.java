package com.rdc.bms.q_comic.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.rdc.bms.q_comic.listener.OnScrollYChangeForAlphaListener;

/**
 * 可以监听滑动距离并且可以嵌套水平RecyclerView的ScrollView
 * 这里使用外部拦截法解决冲突
 */
public class ListenOffsetYNestedScrollView extends NestedScrollView {

    private String TAG ="ListenOffsetYNestedScrollView";

    //偏移量，当RecyclerView位于初始状态向下滑动时，边滑动边变化标题栏的透明度
    //标题栏原始透明，当offset=mOffsetDistance时完全不透明
    private int mOffsetDistance;
    boolean lock = false;//不变换透明度（减少不必要的透明度变换）
    private OnScrollYChangeForAlphaListener mListener;

    private int oldX;
    private int oldY;
    private int deltaY;
    private int deltaX;


    public ListenOffsetYNestedScrollView(Context context) {
        super(context);
    }

    public ListenOffsetYNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListenOffsetYNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollYChangeForAlphaListener(OnScrollYChangeForAlphaListener listener){
        mListener = listener;
    }

    /**
     * 指定偏移量，ScrollView在这一偏移量内滑动会计算一个透明度值给TopLayout使用
     * @param offsetDistance 此处为ScrollView.getFirstChild()即Banner的高度-TopLayout的高度
     *                       ,单位px
     */
    public void setOffsetDistance(int offsetDistance){
        mOffsetDistance = offsetDistance;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = t*1f / mOffsetDistance;
        if (scale >= 0 && scale <= 1){
            lock = false;
            if (mListener != null){
                mListener.changeTopLayoutTransport((int) (scale*255));
            }
        }else {
            //净化scale（scale范围为[0,1]）
            if (scale > 1){
                scale = 1;
            }else {
                scale = 0;
            }
            if (!lock){
                if (mListener != null){
                    mListener.changeTopLayoutTransport((int) (scale*255));
                }
            }
            lock = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean intercepted = false;
//        int newX = (int) ev.getX();
//        int newY = (int) ev.getY();
//        switch (ev.getAction() & MotionEvent.ACTION_MASK){
//            case MotionEvent.ACTION_DOWN:
//                super.onInterceptTouchEvent(ev);
//                intercepted = false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                deltaY = Math.abs(newY-oldY);
//                deltaX = Math.abs(newX-oldX);
//                Log.d(TAG, "dispatchTouchEvent: "+ deltaY *1f/ deltaX);
//                if (deltaX > deltaY){
//                    //滑动的轨迹和水平方向切角小于45°则交给子View处理
//                    intercepted = false;
//                }else {
//                    intercepted = true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                intercepted = false;
//                break;
//        }
//        oldX = newX;
//        oldY = newY;
//        return intercepted;
//    }

}
