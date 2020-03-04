package com.lykj.aextreme.afinal.utils;

import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class AnimationUtils {
    /**
     * 按钮点击动画效果--缩放
     * @param view
     */
    public static void setButtonEffect(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.02f, 1.01f, 1.02f, 1.01f);
        scaleAnimation.setDuration(300);
        animationSet.addAnimation(scaleAnimation);
        view.startAnimation(animationSet);
    }
}
