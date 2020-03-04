package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.MyApplication;
import com.chaojidaikuan.daikuanguowai.ui.act.Act_ChangePassword;
import com.chaojidaikuan.daikuanguowai.ui.act.Act_LogOn;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.Act_AboutUs;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.Act_AuthenticationHome;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.Act_Bill;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.Act_MyBankCard;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.Act_MyKeFu;
import com.chaojidaikuan.daikuanguowai.ui.http.ApiConstant;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.common.BaseFragment;
import com.lykj.aextreme.afinal.utils.ACache;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.aextreme.afinal.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 */
public class Fgt_My extends BaseFragment {
    Unbinder unbinder1;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    private ACache aCache;

    @Override
    public int initLayoutId() {
        return R.layout.fgt_my;
    }

    Unbinder unbinder;

    @Override
    public void initView() {
        hideHeader();
        unbinder = ButterKnife.bind(Fgt_My.this, v);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();

    }


    @Override
    public void initData() {
        aCache = ACache.get(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (aCache.getAsString("phone") != null) {
            tvUsername.setText(aCache.getAsString("phone"));
        }
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onNoInterNet() {

    }

    @Override
    public void sendMsg(int flag, Object obj) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @OnClick({R.id.ll_bankcard, R.id.ll_renzheng, R.id.ll_aboutus,
            R.id.ll_changepassword, R.id.ll_youhuiquan, R.id.bt_exit,
            R.id.ll_kefu, R.id.ll_myoder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_myoder://订单
                if (MyApplication.getLognBean() == null) {
                    startAct(Act_LogOn.class);
                    return;
                }
                startAct(Act_Bill.class);
                break;
            case R.id.ll_bankcard://银行卡
                if (MyApplication.getLognBean() == null) {
                    startAct(Act_LogOn.class);
                    return;
                }
                startAct(Act_MyBankCard.class);
                break;
            case R.id.ll_renzheng://认证
                if (MyApplication.getLognBean() == null) {
                    startAct(Act_LogOn.class);
                    return;
                }
                startAct(Act_AuthenticationHome.class);
                break;
            case R.id.ll_kefu://客服
                if (MyApplication.getLognBean() == null) {
                    startAct(Act_LogOn.class);
                    return;
                }
                if (MyApplication.getLognBean().getMember().getContact_url() == null && TextUtils.isEmpty(MyApplication.getLognBean().getMember().getContact_url())) {
                    Toast.makeText(getContext(), "unggulah sebentar lagi", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(getContext(), Act_MyKeFu.class);
                intent.putExtra("url", ApiConstant.ROOT_URL + "public/contactus");
                startActivity(intent);
                break;
            case R.id.ll_aboutus://关于我们
                startAct(Act_AboutUs.class);
                break;
            case R.id.ll_changepassword://密码
                if (MyApplication.getLognBean() == null) {
                    startAct(Act_LogOn.class);
                    return;
                }
                startAct(Act_ChangePassword.class);
                break;
            case R.id.bt_exit://退出
                if (MyApplication.getLognBean() == null) {
                    startAct(Act_LogOn.class);
                    return;
                }
                if (Utils.isFastClick() == false) {//防点击过快
                    return;
                }
                aCache.put("lognbean", "");
                MyApplication.setLognBean(null);
                MyToast.show(getContext(), "Keluar dari sukses");
                startAct(Act_LogOn.class);
                getActivity().finish();
                break;
        }
    }
}
