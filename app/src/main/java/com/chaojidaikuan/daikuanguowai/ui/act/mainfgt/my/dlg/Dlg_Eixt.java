package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.dlg;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.chaojidaikuan.daikuanguowai.R;
import com.lykj.aextreme.afinal.common.BaseDialog;


/**
 * Created by lishan on 2017/12/22.
 */

public class Dlg_Eixt extends BaseDialog {
    OnClick onClick;

    public Dlg_Eixt(Context context, OnClick click) {
        super(context);
        this.onClick = click;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.dlg_eixt;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.jixu);
        setOnClickListener(R.id.fangqi);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.jixu://继续
                dismiss();
                break;
            case R.id.fangqi://返回
                onClick.onClickBackFinsh();
                break;
        }
    }

    public interface OnClick {
        void onClickBackFinsh();//返回
    }
}
