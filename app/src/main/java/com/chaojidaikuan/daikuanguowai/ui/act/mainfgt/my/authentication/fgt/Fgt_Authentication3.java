package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.MyApplication;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.Act_MyBank;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.userExtendInfoBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.userSaveExtendInfoBean;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.lykj.aextreme.afinal.common.BaseFragment;
import com.lykj.aextreme.afinal.utils.MyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 认证页面3
 */
public class Fgt_Authentication3 extends BaseFragment {
    @BindView(R.id.spinner1)
    MaterialSpinner spinner1;
    @BindView(R.id.spinner2)
    MaterialSpinner spinner2;
    @BindView(R.id.spinner3)
    MaterialSpinner spinner3;
    @BindView(R.id.spinner4)
    TextView spinner4;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    Unbinder unbinder1;
    @BindView(R.id.et_inputBanck)
    EditText etInputBanck;
    @BindView(R.id.et_inputBanck1)
    EditText etInputBanck1;

    @Override
    public int initLayoutId() {
        return R.layout.fgt_authentication3;
    }

    Unbinder unbinder;

    @Override
    public void initView() {
        hideHeader();
        unbinder = ButterKnife.bind(Fgt_Authentication3.this, v);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();
        degree = "Sarjana dan Lebih Tinggi";
        //学历
        spinner1.setItems("Sarjana dan Lebih Tinggi", "Mahasiswa/Diploma", "SMA/Sederajat", "SMP/Iebih rendah");
        spinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                degree = item;
                edStatus();
            }
        });
        marriage = "Belum Menikah";
        //婚姻状况
        spinner2.setItems("Belum Menikah", "Mennikah", "Cerai");
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                marriage = item;
                edStatus();
            }
        });
        children = "1 Anak";
        //孩子人数
        spinner3.setItems("1 Anak", "2 Anak", "3 Anak", "Lebih dari 3 Anak","Belum Punya");
        spinner3.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                children = item;
                edStatus();
            }
        });
//        bank_name = "Bank Rakyat Indonesia";
//        //银行名称/钱包名称
//        spinner4.setItems("Bank Rakyat Indonesia", "Bank Mandiri", "Bank Negara Indonesia", "Bank Danamon",
//                "Bank Permata", "BANK BCA", "BANK BII MAYBANK", "BANK PANIN", "BANK CIMB NIAGA", "BANK UOB INDONESIA",
//                "CITIBANK", "BANK CCB INDONESIA", "BANK ARTHA GRAHA", "THE BANK OF TOKYO MITSUBISHI UFJ LTD",
//                "BANK DBS INDONESIA", "STANDARD CHARTERED BANK", "ANZ PANIN BANK", "DEUTSCHE BANK AG", "BANK OF CHINA LIMITED",
//                "BANK BUMI ARTA", "BANK HSBC", "BANK RABOBANK", "BANK MAYAPADA", "BANK BJB", "BANK DKI", "BPD DIY", "");
//        spinner4.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//            @Override
//            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                bank_name = item;
//                edStatus();
//            }
//        });
        etInputBanck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                bank_no = etInputBanck.getText().toString();
                edStatus();
            }
        });
        etInputBanck1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                bank_no = etInputBanck.getText().toString();
                edStatus();
            }
        });
        spinner4.setOnClickListener(view -> {
            startActivityForResult(Act_MyBank.class, 10);
        });
    }

    @Override
    public void initData() {
        if (MyApplication.lognStatus) {
            userExtendInfo();
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

    private String degree = "", marriage = "", children = "", bank_name = "", bank_no = "";

    /**
     * 保存其他信息
     * degree 学历
     * marriage  婚姻
     * children 孩子人数
     * bank_name 银行名称
     * bank_no 银行账号
     */
    public void userSaveExtendInfo() {
        HttpHelper.userSaveExtendInfo(getContext(), degree, marriage, children, bank_name, bank_no, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                userSaveExtendInfoBean loangetBean = gson.fromJson(succeed, userSaveExtendInfoBean.class);
                if (loangetBean.getStatus() == 1) {
                    MyToast.show(getContext(), "Berhasil dilestarikan！");
                    getActivity().setResult(10);
                    getActivity().finish();
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

    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
        if (tvCommit.isSelected()) {
            loding.show();
            userSaveExtendInfo();
        }
    }

    /**
     * 状态显示
     */
    public void edStatus() {
        if (TextUtils.isEmpty(degree)) {
            return;
        }
        if (TextUtils.isEmpty(marriage)) {
            return;
        }
        if (TextUtils.isEmpty(children)) {
            return;
        }
        if (TextUtils.isEmpty(bank_name)) {
            return;
        }
        if (TextUtils.isEmpty(bank_no)) {
            return;
        }
        if (!etInputBanck.getText().toString().equals(etInputBanck1.getText().toString())) {
            return;
        }
        tvCommit.setSelected(true);
    }

    /**
     * 获取其它信息
     */
    public void userExtendInfo() {
        HttpHelper.userExtendInfo(getContext(), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                userExtendInfoBean loangetBean = gson.fromJson(succeed, userExtendInfoBean.class);
                if (loangetBean.getStatus() == 1) {
                    if (loangetBean.getData().getData() != null) {
                        etInputBanck.setText(loangetBean.getData().getData().getBank_no());
                        etInputBanck1.setText(loangetBean.getData().getData().getBank_no());
                    } else {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 10) {
            String bankName = data.getStringExtra("bankName");
            spinner4.setText(bankName);
            bank_name = bankName;
            edStatus();
        }

    }
}
