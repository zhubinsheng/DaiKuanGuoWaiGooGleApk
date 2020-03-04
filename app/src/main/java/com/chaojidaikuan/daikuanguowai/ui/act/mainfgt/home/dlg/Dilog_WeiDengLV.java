package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.dlg;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.chaojidaikuan.daikuanguowai.R;
import com.lykj.aextreme.afinal.common.BaseDialog;

/**
 * 提示未登录
 */
public class Dilog_WeiDengLV extends BaseDialog {
    public OnBackCenter onBackTime;
    public Dilog_WeiDengLV(Context context) {
        super(context);
    }

    public void setOnBackTime(OnBackCenter onBackTime) {
        this.onBackTime = onBackTime;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.dilog_weidenglv;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.Konfirmasi);
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.Konfirmasi:
                dismiss();
                onBackTime.onKonfirmasi();
                break;
        }
    }

    public interface OnBackCenter {
        void onKonfirmasi();
    }
}
