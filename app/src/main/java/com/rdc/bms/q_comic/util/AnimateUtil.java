package com.rdc.bms.q_comic.util;

import android.widget.ImageView;

import com.rdc.bms.q_comic.R;

public class AnimateUtil {
    /**
     * 点击喜欢时的动画
     * @param view
     * @param isLike 目前的状态（没点击前）
     */
    public static void likeAnimate(final ImageView view, boolean isLike) {
        if (isLike){
            //取消喜欢
            view.setImageResource(R.drawable.svg_red_like_normal);
        }else {
            //喜欢
            view.setImageResource(R.drawable.svg_red_like_pressed);
        }
        view.animate().scaleX(1.5f).scaleY(1.5f).withEndAction(new Runnable() {
            @Override
            public void run() {
                view.animate().scaleX(1f).scaleY(1f);
            }
        });
    }
}
