package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.BaseAct;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean.bankListsBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean.bankUpdateBean;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.utils.MyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加银行卡
 *
 * @author zlz
 */
public class Act_AddBankCard extends BaseAct {
    @BindView(R.id.logon_back)
    ImageView logonBack;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_telephone)
    EditText etTelephone;
    @BindView(R.id.et_idcard)
    EditText etIdcard;
    @BindView(R.id.et_bankid)
    EditText etBankid;
    @BindView(R.id.et_bank)
    Spinner spinner;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    private String card = "";
    private ArrayAdapter<String> mArrayAdapter;

    @Override
    public int initLayoutId() {
        return R.layout.act_addbankcard;
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
        bankValidBanks();
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

    @OnClick({R.id.logon_back, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.logon_back:
                finish();
                break;
            case R.id.tv_sure:
                if (card.equals("") || etUsername.getText().toString().equals("") ||
                        etTelephone.getText().toString().equals("") || etIdcard.getText().toString().equals("") ||
                        etBankid.getText().toString().equals("")) {
                    Toast.makeText(this, "资料填写不完整", Toast.LENGTH_LONG).show();
                } else {
                    bankUpdate(etTelephone.getText().toString(),
                            card,
                            etIdcard.getText().toString(),
                            etUsername.getText().toString(),
                            etBankid.getText().toString()
                    );
                }
                break;
        }
    }

    String bank_key = "";

    /**
     * 添加我的银行卡
     */
    public void bankUpdate(String telephone, String username, String idcard, String bankno, String bankname) {
        if (bank_key.equals("")) {
            return;
        }
        HttpHelper.bankUpdate(this, telephone, username, idcard, bankno, bankname, bank_key, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                bankUpdateBean entity = gson.fromJson(succeed, bankUpdateBean.class);
                if (entity.getStatus() == 1) {
                    MyToast.show(getApplicationContext(), entity.getInfo());
                    finish();
                } else {
                    MyToast.show(getApplicationContext(), entity.getInfo());
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });
    }

    private String[] spinnerString = null;

    /**
     * 我的银行卡选择
     */
    public void bankValidBanks() {
        HttpHelper.bankValidBanks(this, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();

                Gson gson = new Gson();
                bankListsBean entity = gson.fromJson(succeed, bankListsBean.class);
                if (entity.getStatus() == 1) {
                    spinnerString = new String[entity.getData().getBanks().size()];
                    for (int i = 0; i < entity.getData().getBanks().size(); i++) {
                        spinnerString[i] = entity.getData().getBanks().get(i).getVal();
                    }
                    bank_key = entity.getData().getBanks().get(0).getKey();
                    mArrayAdapter = new ArrayAdapter<String>(Act_AddBankCard.this, R.layout.bg_spinner, spinnerString) {
                        @Override
                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                            if (convertView == null) {
//                    设置spinner展开的Item布局
                                convertView = getLayoutInflater().inflate(R.layout.item_spinner, parent, false);
                            }
                            TextView spinnerText = (TextView) convertView.findViewById(R.id.spinner_textView);
                            spinnerText.setText(getItem(position));
                            bank_key = entity.getData().getBanks().get(position).getKey();
                            return convertView;
                        }
                    };
                    spinner.setAdapter(mArrayAdapter);
//        spinner设置监听
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            card = spinnerString[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
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
