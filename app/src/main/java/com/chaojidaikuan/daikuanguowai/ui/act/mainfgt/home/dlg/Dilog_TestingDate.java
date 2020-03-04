package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.dlg;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import com.chaojidaikuan.daikuanguowai.R;
import com.lykj.aextreme.afinal.common.BaseDialog;
/**
 * 提示还款和延期
 */
public class Dilog_TestingDate extends BaseDialog {
    public OnBackCenter onBackTime;

    public Dilog_TestingDate(Context context) {
        super(context);
    }

    public void setOnBackTime(OnBackCenter onBackTime) {
        this.onBackTime = onBackTime;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.dilog_testing;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.cancel);
        setOnClickListener(R.id.center);
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                dismiss();
                onBackTime.onYanqi();
                break;
            case R.id.center://确认
                dismiss();
                onBackTime.onHuanKuan();
                break;
        }
    }

    public interface OnBackCenter {
        void onHuanKuan();

        void onYanqi();
    }
}
