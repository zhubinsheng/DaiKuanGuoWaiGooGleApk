package com.lykj.aextreme.afinal.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import com.lykj.aextreme.R;
import com.lykj.aextreme.afinal.utils.MyUtil;

public abstract class BaseDialog extends Dialog implements View.OnClickListener {

    public BaseDialog(Context context) {
        super(context, R.style.DialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        initWindow();
        initView();
        initData();
    }

    protected abstract int initLayoutId();

    /**
     * 初始化Window
     */
    protected abstract void initWindow();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 控件点击事件
     */
    protected abstract void onViewClick(View v);

    @Override
    public void onClick(View v) {
        if (MyUtil.isFastClick())
            return;
        onViewClick(v);
    }

    /**
     * @param width   宽度
     * @param height  高度
     * @param gravity 相对于整个屏幕的位置
     */
    protected void windowDeploy(float width, float height, int gravity) {
        Window window = getWindow();
        LayoutParams params = getWindow().getAttributes(); // 获取对话框当前的参数值
        if (width == 0) {
            params.width = LayoutParams.WRAP_CONTENT;
        } else if (width > 0 && width <= 1) {
            params.width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * width); // 宽度设置为屏幕的0.x
        } else {
            params.width = (int) width;
        }
        if (height == 0) {
            params.height = LayoutParams.WRAP_CONTENT;
        } else if (height > 0 && height <= 1) {
            params.height = (int) (getContext().getResources().getDisplayMetrics().heightPixels * height); // 高度设置为屏幕的0.x
        } else {
            params.height = (int) height;
        }
        params.verticalMargin = -0.1f;
        window.setAttributes(params); // 设置生效
        getWindow().setGravity(gravity);
//         getWindow().setGravity(Gravity.LEFT | Gravity.TOP);
        setCanceledOnTouchOutside(touchCancle);// 设置触摸对话框意外的地方取消对话框
        setCancelable(touchCancle);
        // window.setWindowAnimations(R.style.winAnimFadeInFadeOut);
    }

    /**
     * 设置点击屏幕外是否可以点击消失
     *
     * @param resId
     * @param <T>
     * @return
     */
    public boolean touchCancle = false;

    public void setTouchCancle(boolean touchCancle) {
        this.touchCancle = touchCancle;
    }

    @SuppressWarnings({"unchecked"})
    protected <T> T getView(int resId) {
        return (T) findViewById(resId);
    }

    /**
     * 获取并绑定点击
     *
     * @param id id
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T getViewAndClick(@IdRes int id) {
        T v = getView(id);
        v.setOnClickListener(this);
        return v;
    }

    protected void setOnClickListener(int resId) {
        findViewById(resId).setOnClickListener(this);
    }

    private Object tag;

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
