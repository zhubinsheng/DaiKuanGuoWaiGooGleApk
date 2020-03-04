package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.BaseAct;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.adapter.MybackAdapter;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean.BankBeen;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.utils.MyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的银行卡
 */
public class Act_MyBankCard extends BaseAct {
    @BindView(R.id.lv_back)
    ListView lv_back;

    @Override
    public int initLayoutId() {
        return R.layout.act_mybankcard;
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
        bankLists();
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

    private List<BankBeen> bankBeens = new ArrayList<>();
    MybackAdapter mybackAdapter;

    /**
     * 我的银行卡
     */
    public void bankLists() {
        HttpHelper.bankLists(this, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                try {
                    bankBeens.clear();
                    JSONObject response = new JSONObject(succeed);
                    String resultCode = response.getString("status");
                    if (resultCode.equals("1")) {
                        JSONObject data = response.getJSONObject("data");
                        JSONArray bankinfo = data.getJSONArray("bankLists");
                        for (int i = 0; i < bankinfo.length(); i++) {
                            JSONObject back = (JSONObject) bankinfo.get(i);
                            BankBeen bankBeen = new BankBeen();
                            bankBeen.setBankname(back.getString("bankname"));
                            bankBeen.setBankcard(back.getString("bankno"));
                            bankBeen.setImageurl(back.getString("image"));
                            bankBeen.setId(back.getString("id"));
                            bankBeens.add(bankBeen);
                        }
                        BankBeen bankBeen = new BankBeen();
                        bankBeens.add(bankBeen);
                        mybackAdapter = new MybackAdapter(Act_MyBankCard.this, bankBeens);
                        lv_back.setAdapter(mybackAdapter);
                        mybackAdapter.setOnArtClick(new MybackAdapter.setOnDeleteLisener() {
                            @Override
                            public void Click(String cardid, int i) {
                                if (i == 1) {
                                    startActivityForResult(new Intent(Act_MyBankCard.this, Act_AddBankCard.class), 1);
                                } else {

                                }
                            }
                        });

                    } else {
                        Toast.makeText(Act_MyBankCard.this, response.getString("msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
        switch (resultCode) {
            case 3:
                bankBeens.clear();
                bankLists();
                break;

            default:
                break;
        }
    }

    @OnClick(R.id.logon_back)
    public void onViewClicked() {
        finish();
    }
}
