package com.lykj.aextreme.afinal.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OSUtils;
import com.lykj.aextreme.R;
import com.lykj.aextreme.afinal.utils.Constant;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.MyUtil;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;


public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity, View.OnClickListener {
    public ZLoadingDialog loding;
    public BaseApplication app;
    protected Context context = this;
    private Class<?> lastAct;// 上一级 Activity
    private String lastSkipAct;// 跳转过来的Activity
    public RelativeLayout toolbar;
    private TextView txtTitle;
    private FrameLayout flyMain;
    private boolean translucentStatus;
    public boolean statusBar = false;
    private InputMethodManager mInputMethodManager;
    public void setTranslucentStatus(boolean translucentStatus) {
        this.translucentStatus = translucentStatus;
    }
    public  Bundle savedInstanceState;
    @Override
    protected void onCreate(Bundle savedInstanceState1) {
        super.onCreate(savedInstanceState1);
        savedInstanceState=savedInstanceState1;
        init();
        initView();
        initData();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //显示状态栏
        // 非必加
        // 如果你的app可以横竖屏切换，适配了华为emui3系列系统手机，并且navigationBarWithEMUI3Enable为true，
        // 请在onResume方法里添加这句代码（同时满足这三个条件才需要加上代码哦：1、横竖屏可以切换；2、华为emui3系列系统手机；3、navigationBarWithEMUI3Enable为true）
        // 否则请忽略
        if (OSUtils.isEMUI3_x() && isImmersionBarEnabled()) {
            ImmersionBar.with(this).init();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mInputMethodManager = null;
        if (isImmersionBarEnabled()) {
            ImmersionBar.with(this).destroy();
        }
    }

    private void init() {
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        loding = new ZLoadingDialog(this);
        loding.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.DKGRAY)//颜色
                .setHintText("Pemuatan data...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.DKGRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CCffffff")); // 设置背景色，默认白色
        lastSkipAct = getIntent().getStringExtra(Constant.LAST_ACT);// 获取上一级Activity的Name
        try {
            app = (BaseApplication) getApplication();
        } catch (Exception e) {
            Debug.e(getClass(), "BaseApplication Exception");
            System.exit(0);
            return;
        }
        app.addActivity(this);
        int layout = initLayoutId();
        if (layout < 1) return;
        setContentView(layout);
        boolean custumHeader = true;
        if (toolbar == null) {
            custumHeader = false;
            setContentView(R.layout.act_base);
            toolbar = getView(R.id.head_toolBar);
        }
        txtTitle = getView(R.id.head_vTitle);
        flyMain = getView(R.id.base_flyMain);
        if (!custumHeader && flyMain != null) {
            View.inflate(context, layout, flyMain);
        }
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
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        ImmersionBar.with(this).navigationBarColor(R.color.white).init();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (isImmersionBarEnabled()) {
            // 非必加
            // 如果你的app可以横竖屏切换，适配了4.4或者华为emui3.1系统手机，并且navigationBarWithKitkatEnable为true，
            // 请务必在onConfigurationChanged方法里添加如下代码（同时满足这三个条件才需要加上代码哦：1、横竖屏可以切换；2、android4.4或者华为emui3.1系统手机；3、navigationBarWithKitkatEnable为true）
            // 否则请忽略
            ImmersionBar.with(this).init();
        }
    }

    /**
     * @see #startAct(Intent, Class)
     */
    protected void startAct(Class<?> cls) {
        startAct(null, cls);
    }

    /**
     * 启动Activity
     */
    protected void startAct(Intent intent, Class<?> cls) {
        if (intent == null)
            intent = new Intent();
        intent.putExtra(Constant.LAST_ACT, this.getClass().getSimpleName());
        intent.setClass(this, cls);
        startActivity(intent);
    }

    /**
     * @see #startActClear(Intent, Class)
     */
    @Deprecated
    public void startActClear(Class<?> cls) {
        startActClear(null, cls);
    }

    /**
     * 启动Activity，清空栈 并添加到栈顶，慎用
     */
    @Deprecated
    protected void startActClear(Intent intent, Class<?> cls) {
        if (app != null) app.clear();
        startAct(intent, cls);
    }

    public void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(Constant.LAST_ACT, this.getClass().getSimpleName());
        startActivityForResult(intent, requestCode);
    }


    protected String getStrings(Integer... ids) {
        if (ids.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int id : ids) {
                sb.append(getString(id));
            }
            return sb.toString();
        }
        return "";
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
        return (T) findViewById(id);
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    protected void hideHeader() {
        if (toolbar != null) toolbar.setVisibility(View.GONE);
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.mInputMethodManager == null) {
            this.mInputMethodManager = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.mInputMethodManager != null)) {
            this.mInputMethodManager.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    /**
     * 设置标题
     */
    @Override
    public void setTitle(@StringRes int titleId) {
        setTitle(getString(titleId));

    }

    /**
     * 设置标题
     */
    @Override
    public void setTitle(CharSequence title) {
        if (txtTitle != null)
            txtTitle.setText(title);
        else
            super.setTitle(title);
    }

    @SuppressLint("ResourceType")
    protected void setHeaderLeft(@DrawableRes int left) {
        if (left > 0) {
            if (toolbar.findViewById(R.id.head_vLeft) == null) {
                View v = View.inflate(context, R.layout.in_head_left, toolbar);
                ImageView img = getView(v, R.id.head_vLeft);
                img.setOnClickListener(this);
                img.setImageResource(left);
            } else {
                ImageView img = getView(toolbar, R.id.head_vLeft);
                img.setImageResource(left);
            }
        }
    }

    @SuppressLint("ResourceType")
    protected void setHeaderLeftTxt(int s, @StringRes int left) {
        if (left > 0) {
            if (toolbar.findViewById(R.id.head_vLeft) == null) {
                View v = View.inflate(context, R.layout.in_head_tleft, toolbar);
                TextView txt = getView(v, R.id.head_vLeft);
                txt.setOnClickListener(this);
                txt.setText(left);
                if (txtTitle != null)
                    txt.setTextColor(txtTitle.getTextColors());
            } else {
                TextView txt = getView(toolbar, R.id.head_vLeft);
                txt.setText(left);
            }
        }
    }

    @SuppressLint("ResourceType")
    protected void setHeaderRight(@DrawableRes int right) {
        if (right > 0) {
            if (toolbar.findViewById(R.id.head_vRight) == null) {
                View v = View.inflate(context, R.layout.in_head_right, toolbar);
                ImageView img = getView(v, R.id.head_vRight);
                img.setOnClickListener(this);
                img.setImageResource(right);
            } else {
                ImageView img = getView(toolbar, R.id.head_vRight);
                img.setImageResource(right);
            }
        }
    }

    @SuppressLint("ResourceType")
    protected void setHeaderRightTxt(@StringRes int right) {
        if (right > 0) {
            if (toolbar.findViewById(R.id.head_vRight) == null) {
                View v = View.inflate(context, R.layout.in_head_tright, toolbar);
                TextView txt = getView(v, R.id.head_vRight);
                txt.setOnClickListener(this);
                txt.setText(right);
                if (txtTitle != null)
                    txt.setTextColor(txtTitle.getTextColors());
            } else {
                TextView txt = getView(toolbar, R.id.head_vRight);
                txt.setText(right);
            }
        }
    }
}
