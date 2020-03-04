package com.lykj.aextreme.afinal.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.SimpleImmersionFragment;
import com.lykj.aextreme.R;
import com.lykj.aextreme.afinal.utils.Constant;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.MyUtil;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

/**
 * 父类Fragment
 */
public abstract class BaseFragment extends SimpleImmersionFragment implements IFragmentListener, IBaseActivity, View.OnClickListener {
    private BaseApplication app;
    protected Context context;
    private RelativeLayout toolbar;
    private TextView txtTitle;
    private FrameLayout flyMain;
    private int showCount;
    private boolean init;
    public ZLoadingDialog loding;

    @Override
    public void onStart() {
        super.onStart();
        if (!init) {
            init = true;
            initView();
            initData();
        }
    }

    public View v = null;
  public   Bundle savedInstanceState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState1) {
        loding = new ZLoadingDialog(getContext());
        loding.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.DKGRAY)//颜色
                .setHintText("数据加载中...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.DKGRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CCffffff")); // 设置背景色，默认白色
        showCount++;
        savedInstanceState = savedInstanceState1;
        context = getActivity();
        app = (BaseApplication) getActivity().getApplication();
        boolean custumHeader = true;
        int layout = initLayoutId();
        if (layout > 0) {
            v = inflater.inflate(layout, container, false);
            toolbar = getView(v, R.id.head_toolBar);
        }
        if (toolbar == null) {
            custumHeader = false;
            v = inflater.inflate(R.layout.act_base, container, false);
            toolbar = getView(v, R.id.head_toolBar);
        }
        txtTitle = getView(v, R.id.head_vTitle);
        flyMain = getView(v, R.id.base_flyMain);
        if (!custumHeader && flyMain != null && layout > 0) {
            View.inflate(context, layout, flyMain);
        }
        return v;
    }

    /**
     * 隐藏界面
     */
    protected void showNotInterNetLoading() {
        if (initLoadView()) {
            int count = flyMain.getChildCount();
            for (int i = 0; i < count; i++) {
                View v = flyMain.getChildAt(i);
                if (i > 0) v.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置标题
     */
    public void setTitle(@StringRes int titleId) {
        setTitle(getString(titleId));
    }

    /**
     * 设置标题
     */
    public void setTitle(CharSequence title) {
        if (txtTitle != null) txtTitle.setText(title);
    }

    public BaseApplication getApp() {
        return app;
    }

    /**
     * @see #startAct(Intent, Class)
     */
    protected void startAct(Class<?> cls) {
        startAct(null, cls);
    }

    /**
     * 加载布局
     */
    private boolean initLoadView() {
        if (flyMain == null) {
            Debug.e(getClass(), "Your layout must include 'FrameLayout',the ID must be 'base_flyMain'!");
            return false;
        }
        View loadView = getView(R.id.view_notinternet_view);
        if (loadView == null) {
            if (flyMain.getChildCount() > 0) {
                loadView = View.inflate(context, R.layout.view_nointernet, null);
                flyMain.addView(loadView, 0);
                loadView.findViewById(R.id.view_notinternet_view).setOnClickListener(this);
            } else
                View.inflate(context, R.layout.view_nointernet, flyMain);
            loadView.findViewById(R.id.view_notinternet_view).setOnClickListener(this);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (MyUtil.isFastClick())
            return;
        if (v.getId() == R.id.view_notinternet_view)
            onNoInterNet();
    }

    /**
     * 显示内容View
     */
    protected void showCView() {
        if (initLoadView()) {
            int count = flyMain.getChildCount();
            for (int i = 0; i < count; i++) {
                View v = flyMain.getChildAt(i);
                if (i == 0) v.setVisibility(View.GONE);
                else v.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 启动Activity
     */
    protected void startAct(Intent intent, Class<?> cls) {
        if (intent == null)
            intent = new Intent();
        intent.putExtra(Constant.LAST_ACT, this.getClass().getSimpleName());
        intent.setClass(getActivity(), cls);
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtra(Constant.LAST_ACT, this.getClass().getSimpleName());
        startActivityForResult(intent, requestCode);
    }

    /**
     * 获取 控件
     *
     * @param v  布局
     * @param id 行布局中某个组件的id
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(View v, @IdRes int id) {
        return (T) v.findViewById(id);
    }

    /**
     * 获取 控件
     *
     * @param id 行布局中某个组件的id
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int id) {
        return getView(getView(), id);
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).keyboardEnable(true).init();
    }

    protected void hideHeader() {
        if (toolbar != null) toolbar.setVisibility(View.GONE);
    }

}
