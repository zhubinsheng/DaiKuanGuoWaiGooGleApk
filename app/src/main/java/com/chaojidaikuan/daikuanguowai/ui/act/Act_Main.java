package com.chaojidaikuan.daikuanguowai.ui.act;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.BaseAct;
import com.chaojidaikuan.daikuanguowai.commt.MyApplication;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.Fgt_Home1;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.Fgt_My;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.dlg.Dilog_WeiDengLV;
import com.lykj.aextreme.afinal.common.BaseFragment;
import com.lykj.aextreme.afinal.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Act_Main extends BaseAct {

    @Override
    public int initLayoutId() {
        return R.layout.act_main;
    }

    private TextView[] title = new TextView[3];
    List<BaseFragment> fgtData = new ArrayList<>();

    @Override
    public void initView() {
        hideHeader();
        ButterKnife.bind(this);
        title[0] = getView(R.id.main_tab_home);
        title[1] = getView(R.id.main_tab_my);
    }

    int page = 0;

    @Override
    public void initData() {
        fgtData.add(new Fgt_Home1());
        fgtData.add(new Fgt_My());
        getSupportFragmentManager().beginTransaction().add(R.id.myFrame, fgtData.get(0)).add(R.id.myFrame, fgtData.get(1)).hide(fgtData.get(1)).show(fgtData.get(0)).commit();
        setCurrent(page);

    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onNoInterNet() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.main_tab_home, R.id.main_tab_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_tab_home:
                setCurrent(0);
                break;
            case R.id.main_tab_my:
                if (MyApplication.getLognBean() == null) {
                    Dilog_WeiDengLV weiDengLV = new Dilog_WeiDengLV(this);
                    weiDengLV.setOnBackTime(() -> {
                        startAct(Act_LogOn.class);
                    });
                    weiDengLV.setTouchCancle(true);
                    weiDengLV.show();
                    return;
                }
                setCurrent(1);
                break;
        }
    }


    public int currentTabIndex = 0;

    public void setCurrent(int indext) {
        if (currentTabIndex != indext) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fgtData.get(currentTabIndex));
            if (!fgtData.get(indext).isAdded()) {
                trx.add(R.id.myFrame, fgtData.get(indext));
            }
            trx.show(fgtData.get(indext)).commit();
        }
        title[currentTabIndex].setSelected(false);
        title[indext].setSelected(true);
        currentTabIndex = indext;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (MyApplication.onBackStatus) {
                finish();
                MyApplication.onBackStatus = false;
            } else {
                MyToast.show(this, "在按一次退出APP");
                MyApplication.onBackStatus = true;
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

}
