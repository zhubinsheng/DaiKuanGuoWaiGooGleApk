package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.BaseAct;
import com.chaojidaikuan.daikuanguowai.commt.MyApplication;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.HomeDashbordBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.postapplyLoanBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean.userGetBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.dlg.Dlg_Eixt;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.utils.MyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 认证首页
 */
public class Act_AuthenticationHome extends BaseAct {
    @BindView(R.id.tv_tongxunlu)
    TextView tvTongxunlu;
    @BindView(R.id.im_tongxunlu)
    ImageView imTongxunlu;
    @BindView(R.id.tv_bt_tongxunlu)
    TextView tvBtTongxunlu;
    @BindView(R.id.tv_jingjilianxiren)
    TextView tvJingjilianxiren;
    @BindView(R.id.im_jingjilianxiren)
    ImageView imJingjilianxiren;
    @BindView(R.id.tv_bt_jingjilianxiren)
    TextView tvBtJingjilianxiren;
    @BindView(R.id.tv_qitaxingxi)
    TextView tvQitaxingxi;
    @BindView(R.id.im_qitaxingxi)
    ImageView imQitaxingxi;
    @BindView(R.id.tv_bt_qitaxingxi)
    TextView tvBtQitaxingxi;
    @BindView(R.id.tv_shenfenxingxi)
    TextView tvShenfenxingxi;
    @BindView(R.id.im_shenfenxingxi)
    ImageView imShenfenxingxi;
    @BindView(R.id.tv_bt_shenfenxingxi)
    TextView tvBtShenfenxingxi;
    @BindView(R.id.bt_tvtv)
    TextView btTvtv;
    private Dlg_Eixt eixt;

    @Override
    public int initLayoutId() {
        return R.layout.act_authenticationhome;
    }

    @Override
    public void initView() {
        hideHeader();
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();
    }

    @Override
    public void initData() {
        if (MyApplication.lognStatus == false) {
            loding.show();
            userGet();
        } else {
            tvTongxunlu.setSelected(true);
            imTongxunlu.setVisibility(View.GONE);
            tvBtTongxunlu.setVisibility(View.VISIBLE);
            tvJingjilianxiren.setSelected(true);
            imJingjilianxiren.setVisibility(View.GONE);
            tvBtJingjilianxiren.setVisibility(View.VISIBLE);
            tvQitaxingxi.setSelected(true);
            imQitaxingxi.setVisibility(View.GONE);
            tvBtQitaxingxi.setVisibility(View.VISIBLE);
            tvShenfenxingxi.setSelected(true);
            imShenfenxingxi.setVisibility(View.GONE);
            tvBtShenfenxingxi.setVisibility(View.VISIBLE);
        }
        homeDashbord(MyApplication.getLognBean().getMember().getToken());
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
        ButterKnife.bind(this);
    }

    @OnClick({R.id.logon_back, R.id.tv_bt_tongxunlu, R.id.tv_bt_jingjilianxiren,
            R.id.tv_bt_qitaxingxi, R.id.tv_bt_shenfenxingxi, R.id.bt_tvtv})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        intent.setClass(this, Act_Authentication.class);
        switch (view.getId()) {
            case R.id.logon_back:
                eixt = new Dlg_Eixt(Act_AuthenticationHome.this, () -> {
                    finish();
                });
                eixt.show();
                break;
            case R.id.tv_bt_tongxunlu://通讯录
                intent.putExtra("indext", "0");
                startActivityForResult(intent, 10);
                break;
            case R.id.tv_bt_jingjilianxiren://紧急联系人
                intent.putExtra("indext", "1");
                startActivityForResult(intent, 10);
                break;
            case R.id.tv_bt_qitaxingxi://其他信息
                intent.putExtra("indext", "2");
                startActivityForResult(intent, 10);
                break;
            case R.id.tv_bt_shenfenxingxi://身份信息
                intent.putExtra("indext", "3");
                startActivityForResult(intent, 10);
                break;
            case R.id.bt_tvtv://立即提交
                postapplyLoan(); //获取额度  postapplyLoan(); //获取额度
                break;
        }

    }

    /**
     * 获取认证状态
     */
    public void userGet() {
        HttpHelper.userGet(this, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                userGetBean loangetBean = gson.fromJson(succeed, userGetBean.class);
                if (loangetBean.getStatus() == 1) {
                    if (loangetBean.getData().getData().getWork() != null) {//工作信息
                        tvTongxunlu.setSelected(false);
                        imTongxunlu.setVisibility(View.VISIBLE);
                        tvBtTongxunlu.setVisibility(View.GONE);
                    } else {
                    tvTongxunlu.setSelected(true);
                    imTongxunlu.setVisibility(View.GONE);
                    tvBtTongxunlu.setVisibility(View.VISIBLE);
                    }
                    if (loangetBean.getData().getData().getContactinfo() != null) {//紧急联系人
                        tvJingjilianxiren.setSelected(false);
                        imJingjilianxiren.setVisibility(View.VISIBLE);
                        tvBtJingjilianxiren.setVisibility(View.GONE);
                    } else {
                        tvJingjilianxiren.setSelected(true);
                        imJingjilianxiren.setVisibility(View.GONE);
                        tvBtJingjilianxiren.setVisibility(View.VISIBLE);
                    }
                    if (loangetBean.getData().getData().getExtendinfo() != null) {//其它信息
                        tvQitaxingxi.setSelected(false);
                        imQitaxingxi.setVisibility(View.VISIBLE);
                        tvBtQitaxingxi.setVisibility(View.GONE);
                    } else {
                        tvQitaxingxi.setSelected(true);
                        imQitaxingxi.setVisibility(View.GONE);
                        tvBtQitaxingxi.setVisibility(View.VISIBLE);
                    }
                    if (loangetBean.getData().getData().getCominfo().equals("1")) {//身份信息
                        tvShenfenxingxi.setSelected(false);
                        imShenfenxingxi.setVisibility(View.VISIBLE);
                        tvBtShenfenxingxi.setVisibility(View.GONE);
                    } else {
                        tvShenfenxingxi.setSelected(true);
                        imShenfenxingxi.setVisibility(View.GONE);
                        tvBtShenfenxingxi.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 10) {
            userGet();
            homeDashbord(MyApplication.getLognBean().getMember().getToken());
        }
    }

    /**
     * 获取额度
     */
    private com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.postapplyLoanBean postapplyLoanBean;

    public void postapplyLoan() {
        HttpHelper.loanapplyLoan(this, "1", entity.getData().getData().getAmount_limit(), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                postapplyLoanBean = gson.fromJson(succeed, postapplyLoanBean.class);
                if (entity.getStatus() == 1) {
                    Toast.makeText(getApplicationContext(), postapplyLoanBean.getMsg(), Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });

    }

    private HomeDashbordBean entity;

    /**
     * 首页数据
     */
    public void homeDashbord(String token) {
        HttpHelper.homeDashbord(this, token, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                entity = gson.fromJson(succeed, HomeDashbordBean.class);
                if (entity.getData().getData().getAuthstatus() == 1) {//已认证
                    if (entity.getData().getData().getLoan() == null || entity.getData().getData().getLoan().equals("null")) {//走获取额度
                        btTvtv.setVisibility(View.VISIBLE);
                    } else {
                        btTvtv.setVisibility(View.GONE);
                    }
                } else {
                    btTvtv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });
    }

}
