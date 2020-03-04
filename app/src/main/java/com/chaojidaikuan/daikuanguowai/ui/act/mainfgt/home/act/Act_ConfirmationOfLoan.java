package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.act;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.BaseAct;
import com.chaojidaikuan.daikuanguowai.commt.MyApplication;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.PostBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.loangetBean;
import com.chaojidaikuan.daikuanguowai.ui.http.ApiConstant;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.chaojidaikuan.daikuanguowai.ui.utils.FormatTosepara;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 借款确认
 */
public class Act_ConfirmationOfLoan extends BaseAct {
    @BindView(R.id.logon_back)
    ImageView logonBack;
    @BindView(R.id.tv_damount)
    TextView tvDamount;
    @BindView(R.id.tv_days)
    TextView tvDays;
    @BindView(R.id.item_selected)
    ImageView itemSelected;
    @BindView(R.id.tv_daozhang)
    TextView tvDaozhang;
    @BindView(R.id.tv_damount1)
    TextView tvDamount1;
    @BindView(R.id.tv_service_fee)
    TextView tvServiceFee;
    @BindView(R.id.tv_interest)
    TextView tvInterest;
    @BindView(R.id.xieyiRadio)
    TextView xieyiRadio;

    @Override
    public int initLayoutId() {
        return R.layout.act_confirmationofloan;
    }

    @Override
    public void initView() {
        hideHeader();
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();
        xieyiRadio.setSelected(true);
    }

    private String loan_id = "", orderno = "";

    @Override
    public void initData() {
        loan_id = getIntent().getStringExtra("loan_id");
        loanget();
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onNoInterNet() {

    }

    public void loanget() {
        HttpHelper.loanget(this, loan_id, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                loangetBean loangetBean = gson.fromJson(succeed, loangetBean.class);
                if (loangetBean.getStatus() == 1) {
                    orderno = loangetBean.getData().getLoan().getOrderno();
                    tvDamount.setText("Rp " + FormatTosepara.formatTosepara(Float.valueOf(loangetBean.getData().getLoan().getDamount())) + "");
                    tvDays.setText(loangetBean.getData().getLoan().getDays() + "Hari");
                    tvDaozhang.setText("Rp " + FormatTosepara.formatTosepara(Float.valueOf(loangetBean.getData().getLoan().getDaozhang())));
                    tvDamount1.setText("Rp " + FormatTosepara.formatTosepara(Float.valueOf(loangetBean.getData().getLoan().getDamount())));
                    tvServiceFee.setText("Rp " + FormatTosepara.formatTosepara(Float.valueOf(loangetBean.getData().getLoan().getService_fee())));
                    tvInterest.setText("Rp " + FormatTosepara.formatTosepara(Float.valueOf(loangetBean.getData().getLoan().getInterest())));
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.logon_back, R.id.home_btCommit, R.id.userXieyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.logon_back:
                finish();
                break;
            case R.id.home_btCommit:

                btCommit();
                break;
            case R.id.userXieyi:
                startAct(Act_XieYI.class);
                break;
        }
    }

    /**
     * 提交数据
     */
    public void btCommit() {
        if (xieyiRadio.isSelected() == false) {
            MyToast.show(this, "Perjanjian Pinjaman");
            return;
        }
        loding.show();
        OkHttpUtils
                .post()
                .url(ApiConstant.ROOT_URL + ApiConstant.userapplyConfirm)
                .addHeader("token", MyApplication.getLognBean().getMember().getToken())
                .addParams("orderno", orderno)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        loding.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        loding.dismiss();
                        Gson gson = new Gson();
                        PostBean appLyLoan = gson.fromJson(response, PostBean.class);
                        if (appLyLoan.getStatus() == 1) {
                            setResult(10);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), appLyLoan.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}
